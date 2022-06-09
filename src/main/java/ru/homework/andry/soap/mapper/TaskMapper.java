package ru.homework.andry.soap.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.NullValuePropertyMappingStrategy;
import ru.homework.andry.soap.element.task.TaskRequestCreateElement;
import ru.homework.andry.soap.element.task.TaskRequestUpdateElement;
import ru.homework.andry.soap.element.task.TaskResponseElement;
import ru.homework.andry.soap.entity.TaskEntity;

import java.util.List;

@Mapper(componentModel = "spring",
        uses = TaskEntity.class,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface TaskMapper {

    List<TaskResponseElement> entitiesToResponseElements(List<TaskEntity> taskEntities);

    List<TaskEntity> requestCreateToEntity(List<TaskRequestCreateElement> taskRequestElement);

    List<TaskEntity> requestUpdateToEntity(List<TaskRequestUpdateElement> taskUpdateElement);
}
