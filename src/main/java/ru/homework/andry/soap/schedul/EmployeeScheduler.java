package ru.homework.andry.soap.schedul;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ru.homework.andry.soap.constant.PropertyValues;
import ru.homework.andry.soap.entity.EmployeeEntity;
import ru.homework.andry.soap.repository.EmployeeRepository;

import javax.annotation.PostConstruct;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

@Slf4j
@Component
@RequiredArgsConstructor
public class EmployeeScheduler {

    private final EmployeeRepository employeeRepository;
    private final Queue<EmployeeEntity> employees = new ConcurrentLinkedQueue<>();
    private final PropertyValues propertyValues;

    @PostConstruct
    private void add() {
        addEmployeeEntities();
    }

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
        //TODO просто закоментил чтоб не удалял записи , а то в бд их мало
//        employeeRepository.delete(employeeEntity);
    }

    private void addEmployeeEntities() {
        Page<EmployeeEntity> employeeEntityPage =
                employeeRepository.findAll(PageRequest.of(0, propertyValues.getQUEUE_SIZE_FOR_DELETE_EMP()));

        if (employeeEntityPage.stream().findFirst().isPresent()) {
            employees.addAll(employeeEntityPage.getContent());
        } else {
            log.info("There are no employees in the database");
        }
    }
}