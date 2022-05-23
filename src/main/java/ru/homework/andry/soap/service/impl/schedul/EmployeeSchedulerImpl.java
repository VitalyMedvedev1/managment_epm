package ru.homework.andry.soap.service.impl.schedul;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import ru.homework.andry.soap.repository.EmployeeRepository;
import ru.homework.andry.soap.repository.entity.EmployeeEntity;
import ru.homework.andry.soap.service.EmployeeScheduler;

import java.util.Collections;

@Slf4j
@Service
@RequiredArgsConstructor
public class EmployeeSchedulerImpl implements EmployeeScheduler {

    private final EmployeeRepository employeeRepository;

    @Override
    @Async("empExecutor")
    @Scheduled(cron = "${cron.delete.emp.expression}")
    public void delete() {
        log.info("Start find async emp row");
        Page<EmployeeEntity> entity = employeeRepository.findAll(PageRequest.of(0, 1));

        entity.stream().findFirst().ifPresent(employeeEntity -> {
            Long id = employeeEntity.getId();
            log.info("Delete emp row by id: {}", id);
            employeeRepository.deleteAllById(Collections.singleton(id));
        });
    }
}