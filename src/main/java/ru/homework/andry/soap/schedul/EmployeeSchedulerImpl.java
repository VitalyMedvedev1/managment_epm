package ru.homework.andry.soap.schedul;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.homework.andry.soap.api.schedul.EmployeeScheduler;
import ru.homework.andry.soap.entity.EmployeeEntity;
import ru.homework.andry.soap.repository.EmployeeRepository;

import javax.annotation.PostConstruct;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

import static ru.homework.andry.soap.constant.PropertiesValue.QUEUE_SIZE_FOR_DELETE_EMP;

@Slf4j
@Service
@RequiredArgsConstructor
public class EmployeeSchedulerImpl implements EmployeeScheduler {
    //todo лучше пометить, как Configuration -> убрать интерфейс, назвать EmployeeSchedulerConfig или просто SchedulerConfig и отправить в пакет config

    private final EmployeeRepository employeeRepository;
    private final Queue<EmployeeEntity> employees = new ConcurrentLinkedQueue<>();

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
        //TODO просто закоментил чтоб не удалял записи , а то в бд их мало
//        employeeRepository.delete(employeeEntity);
    }

    private void addEmployeeEntities() {
        Page<EmployeeEntity> employeeEntityPage = employeeRepository.findAll(PageRequest.of(0,
                                                                                            QUEUE_SIZE_FOR_DELETE_EMP));

        if (employeeEntityPage.stream().findFirst().isPresent()) {
            employees.addAll(employeeEntityPage.getContent());
        } else {
            log.info("There are no employees in the database");
        }
    }
}