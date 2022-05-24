package ru.homework.andry.soap.service.impl.schedul;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.homework.andry.soap.repository.EmployeeRepository;
import ru.homework.andry.soap.repository.entity.EmployeeEntity;
import ru.homework.andry.soap.service.EmployeeScheduler;

import javax.annotation.PostConstruct;
import java.util.LinkedList;
import java.util.Queue;
import java.util.stream.Collectors;

import static ru.homework.andry.soap.constant.Values.QUEUE_SIZE_FOR_DELETE_EMP;

@Slf4j
@Service
@RequiredArgsConstructor
public class EmployeeSchedulerImpl implements EmployeeScheduler {

    private final EmployeeRepository employeeRepository;
    private final Queue<EmployeeEntity> employees = new LinkedList<>();

    @PostConstruct
    private void add() {
        addEmployeeEntities();
    }

    @Override
    @Async("empExecutor")
    @Scheduled(cron = "${cron.delete.emp.expression}")
    public void execute() {
        log.info("Get employee for delete");
        EmployeeEntity employeeEntity = employees.poll();

        if (employeeEntity == null) {
            addEmployeeEntities();
        } else {
            delete(employeeEntity);
        }
    }

    @Transactional
    void delete(EmployeeEntity employeeEntity) {
        log.info("Get employee with id {}", employeeEntity.getId());
        employeeRepository.delete(employeeEntity);
    }

    private void addEmployeeEntities() {
        employees.addAll(
                (employeeRepository.findAll(PageRequest.of(0, QUEUE_SIZE_FOR_DELETE_EMP))
                        .stream()
                        .collect(Collectors.toList())));
    }
}