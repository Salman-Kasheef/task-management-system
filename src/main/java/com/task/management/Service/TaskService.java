package com.task.management.Service;

import com.task.management.Model.Task;
import com.task.management.Repositories.TaskRepository;
import com.task.management.Repositories.UserRepository;
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
    private UserRepository userRepository;
    @Autowired
    private KafkaProducerService kafkaProducerService;

    public Task createTask(Task task) {
        if (task.getAssignedTo() != null && userRepository.findById(task.getAssignedTo()).isEmpty()) {
            throw new IllegalArgumentException("Assigned user does not exist.");
        }
        task.setCreatedAt(LocalDateTime.now());
        Task savedTask = taskRepository.save(task);
        kafkaProducerService.sendMessage("task-updates", "Task Created: " + savedTask.getTitle());
        return savedTask;
    }

    public List<Task> getAllTasks(int page, int size) {
        return taskRepository.findAll();
    }

    public Task updateTask(Long id, Task task) {
        if (!taskRepository.existsById(id)) {
            throw new IllegalArgumentException("Task not found.");
        }
        task.setUpdatedAt(LocalDateTime.now());
        Task updatedTask = taskRepository.save(task);
        kafkaProducerService.sendMessage("task-updates", "Task Updated: " + updatedTask.getTitle());
        return updatedTask;
    }

    public void deleteTask(Long id) {
        if (!taskRepository.existsById(id)) {
            throw new IllegalArgumentException("Task not found.");
        }
        taskRepository.deleteById(id);
        kafkaProducerService.sendMessage("task-updates", "Task Deleted with ID: " + id);
    }
}
