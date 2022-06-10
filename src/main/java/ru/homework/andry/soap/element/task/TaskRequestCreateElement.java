package ru.homework.andry.soap.element.task;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TaskRequestCreateElement {

    @NotNull(message = "Description is required")
    String description;
}