package org.QMApp.controller;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import org.QMApp.model.Task;
import org.QMApp.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class TaskController {
    @Autowired
    private TaskService taskService;

    @FXML
    private TableView<Task> taskTable;

    @FXML
    private TextField descriptionField;
    @FXML
    private DatePicker dateField;
    @FXML
    private TextField riskLevelField;
    @FXML
    private TextField reportedByField;

    @FXML
    public void initialize() {
        // Initialize the table and load data
        loadTasks();
    }

    private void loadTasks() {
        taskTable.setItems(FXCollections.observableList(taskService.getAllTasks()));
    }

    @FXML
    private void addTask() {
        Task task = new Task();
        task.setDescription(descriptionField.getText());
        task.setDate(dateField.getValue());
        task.setRiskLevel(riskLevelField.getText());
        task.setReportedBy(reportedByField.getText());

        taskService.saveTask(task);
        loadTasks();
    }

    @FXML
    private void deleteTask() {
        Task selectedTask = taskTable.getSelectionModel().getSelectedItem();
        if (selectedTask != null) {
            taskService.deleteTask(selectedTask.getId());
            loadTasks();
        }
    }
}