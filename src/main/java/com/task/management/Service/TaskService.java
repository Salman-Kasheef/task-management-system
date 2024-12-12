package com.task.management.Service;

import com.task.management.Model.Task;
import com.task.management.Repositories.TaskRepository;
import com.task.management.kafka.KafkaProducerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class TaskService {
    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private KafkaProducerService kafkaProducerService;

    public Task createTask(Task task) {
        task.setCreatedAt(LocalDateTime.now());
        Task savedTask = taskRepository.save(task);
        kafkaProducerService.sendMessage("task-updates", "Task Created: " + savedTask.getTitle());
        return savedTask;
    }

    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    public Task updateTask(Long id, Task task) {
        task.setUpdatedAt(LocalDateTime.now());
        Task updatedTask = taskRepository.save(task);
        kafkaProducerService.sendMessage("task-updates", "Task Updated: " + updatedTask.getTitle());
        return updatedTask;
    }

    public void deleteTask(Long id) {
        taskRepository.deleteById(id);
        kafkaProducerService.sendMessage("task-updates", "Task Deleted with ID: " + id);
    }
}
