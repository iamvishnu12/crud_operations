package com.example.demo.service;




import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.demo.dto.TaskRequestDTO;
import com.example.demo.dto.TaskResponseDTO;
import com.example.demo.model.Task;
import com.example.demo.repository.TaskRepository;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

    // 🔹 Get all tasks (without pagination)
    public List<TaskResponseDTO> getAllTasks() {
        return taskRepository.findAll()
                .stream()
                .map(this::convertToDTO)
                .toList();
    }

    // 🔹 Get all tasks (with pagination + sorting)
    public Page<TaskResponseDTO> getAllTasks(Pageable pageable) {
        return taskRepository.findAll(pageable)
                             .map(this::convertToDTO);
    }

    // 🔹 Get task by ID
    public TaskResponseDTO getTaskById(Long id) {
        return taskRepository.findById(id)
                .map(this::convertToDTO)
                .orElseThrow(() -> new RuntimeException("Task not found with id " + id));
    }

    // 🔹 Create a new task
    public TaskResponseDTO createTask(TaskRequestDTO dto) {
        Task task = convertToEntity(dto);
        Task savedTask = taskRepository.save(task);
        return convertToDTO(savedTask);
    }

    // 🔹 Update existing task
    public TaskResponseDTO updateTask(Long id, TaskRequestDTO dto) {
        return taskRepository.findById(id)
                .map(task -> {
                    task.setTitle(dto.getTitle());
                    task.setDescription(dto.getDescription());
                    task.setCompleted(dto.isCompleted());
                    return convertToDTO(taskRepository.save(task));
                })
                .orElseThrow(() -> new RuntimeException("Task not found with id " + id));
    }

    // 🔹 Delete task
    public void deleteTask(Long id) {
        if (!taskRepository.existsById(id)) {
            throw new RuntimeException("Task not found with id " + id);
        }
        taskRepository.deleteById(id);
    }

    // 🔹 DTO → Entity
    private Task convertToEntity(TaskRequestDTO dto) {
        Task task = new Task();
        task.setTitle(dto.getTitle());
        task.setDescription(dto.getDescription());
        task.setCompleted(dto.isCompleted());
        return task;
    }

    // 🔹 Entity → DTO
    private TaskResponseDTO convertToDTO(Task task) {
        TaskResponseDTO dto = new TaskResponseDTO();
        dto.setId(task.getId());
        dto.setTitle(task.getTitle());
        dto.setDescription(task.getDescription());
        dto.setCompleted(task.isCompleted());
        return dto;
    }
}

