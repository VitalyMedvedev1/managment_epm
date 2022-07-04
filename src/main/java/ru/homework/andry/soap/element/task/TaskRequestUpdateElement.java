package ru.homework.andry.soap.element.task;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TaskRequestUpdateElement {

    UUID id;
    String description;
}