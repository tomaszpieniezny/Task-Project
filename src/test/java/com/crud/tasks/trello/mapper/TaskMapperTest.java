package com.crud.tasks.trello.mapper;

import com.crud.tasks.controller.TaskController;
import com.crud.tasks.domain.Task;
import com.crud.tasks.domain.TaskDto;
import com.crud.tasks.service.DbService;
import com.crud.tasks.mapper.TaskMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class TaskMapperTest {

    @InjectMocks
    TaskMapper taskMapper;

    @Mock
    TaskController taskController;

    @Mock
    DbService dbService;

    @Test
    public void mapToTaskTest() {
        //Given
        TaskDto taskDto = new TaskDto(1L, "Test_title", "Test_Content");
        Task task = new Task(1L, "Test_title", "Test_Content");

        //When
        Task theTask = taskMapper.mapToTask(taskDto);

        //Then
        assertEquals("Test_title", theTask.getTitle());
    }

    @Test
    public void mapToTaskDtoTest() {
        //Given
        TaskDto taskDto = new TaskDto(1L, "Test_title", "Test_Content");
        Task task = new Task(1L, "Test_title", "Test_Content");

        //When
        TaskDto theTaskDto = taskMapper.mapToTaskDto(task);

        //Then
        assertEquals("Test_title", theTaskDto.getTitle());
    }

    @Test
    public void mapToTaskDtoListTest() {
        //Given
        List<TaskDto> dtoList = new ArrayList<>();
        dtoList.add(new TaskDto(1L, "Test_title", "Test_Content"));
        List<Task> taskList = new ArrayList<>();
        taskList.add(new Task(1L, "Test_title", "Test_Content"));

        //When
        List<TaskDto> theTaskDto = taskMapper.mapToTaskDtoList(taskList);

        //Then
        assertEquals(1, theTaskDto.size());
    }
}