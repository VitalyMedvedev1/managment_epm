package ru.homework.andry.soap.element.task;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TaskRequestUpdateElement {

    @NotNull(message = "Id is required")
    UUID id;
    String description;
}