package org.QMApp.controller;


import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import org.QMApp.model.Task;
import org.QMApp.model.Incident;
import org.QMApp.service.TaskService;
import org.QMApp.service.IncidentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.annotation.PostConstruct;
import java.time.LocalDate;

@Controller
public class MainController {

    // Task fields
    @FXML private TextField taskDescriptionField;
    @FXML private TextField taskDateField;
    @FXML private TextField taskRiskLevelField;
    @FXML private TextField taskReportedByField;
    @FXML private TableView<Task> taskTable;

    // Incident fields
    @FXML private TextField incidentDescriptionField;
    @FXML private TextField incidentDateField;
    @FXML private TextField incidentRiskLevelField;
    @FXML private TextField incidentReportedByField;
    @FXML private TableView<Incident> incidentTable;

    @Autowired private TaskService taskService;
    @Autowired private IncidentService incidentService;

    @PostConstruct
    public void init() {
        taskTable.setItems(FXCollections.observableArrayList(taskService.getAllTasks()));
        incidentTable.setItems(FXCollections.observableArrayList(incidentService.getAllIncidents()));
    }

    @FXML
    private void handleAddTask() {
        Task task = new Task();
        task.setDescription(taskDescriptionField.getText());
        task.setDate(LocalDate.parse(taskDateField.getText()));
        task.setRiskLevel(taskRiskLevelField.getText());
        task.setReportedBy(taskReportedByField.getText());
        taskService.saveTask(task);
        taskTable.getItems().add(task);
        clearTaskFields();
    }

    @FXML
    private void handleDeleteTask() {
        Task selectedTask = taskTable.getSelectionModel().getSelectedItem();
        if (selectedTask != null) {
            taskService.deleteTask(selectedTask.getId());
            taskTable.getItems().remove(selectedTask);
        }
    }

    @FXML
    private void handleAddIncident() {
        Incident incident = new Incident();
        incident.setDescription(incidentDescriptionField.getText());
        incident.setDate(LocalDate.parse(incidentDateField.getText()));
        incident.setRiskLevel(incidentRiskLevelField.getText());
        incident.setReportedBy(incidentReportedByField.getText());
        incidentService.saveIncident(incident);
        incidentTable.getItems().add(incident);
        clearIncidentFields();
    }

    @FXML
    private void handleDeleteIncident() {
        Incident selectedIncident = incidentTable.getSelectionModel().getSelectedItem();
        if (selectedIncident != null) {
            incidentService.deleteIncident(selectedIncident.getId());
            incidentTable.getItems().remove(selectedIncident);
        }
    }

    private void clearTaskFields() {
        taskDescriptionField.clear();
        taskDateField.clear();
        taskRiskLevelField.clear();
        taskReportedByField.clear();
    }

    private void clearIncidentFields() {
        incidentDescriptionField.clear();
        incidentDateField.clear();
        incidentRiskLevelField.clear();
        incidentReportedByField.clear();
    }
}


