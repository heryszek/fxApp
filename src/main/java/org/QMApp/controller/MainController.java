package org.QMApp.controller;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import org.QMApp.model.Incident;
import org.QMApp.model.Task;
import org.QMApp.service.IncidentService;
import org.QMApp.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.annotation.PostConstruct;

@Controller
public class MainController {

    @FXML
    private TableView<Task> taskTable;

    @FXML
    private TableColumn<Task, String> taskDescriptionColumn;

    @FXML
    private TableColumn<Task, String> taskRiskLevelColumn;

    @FXML
    private TextField taskDescriptionField;

    @FXML
    private TextField taskRiskLevelField;

    @FXML
    private TableView<Incident> incidentTable;

    @FXML
    private TableColumn<Incident, String> incidentDescriptionColumn;

    @FXML
    private TableColumn<Incident, String> incidentRiskLevelColumn;

    @FXML
    private TextField incidentDescriptionField;

    @FXML
    private TextField incidentRiskLevelField;

    private final ObservableList<Task> taskData = FXCollections.observableArrayList();
    private final ObservableList<Incident> incidentData = FXCollections.observableArrayList();

    @Autowired
    private TaskService taskService;

    @Autowired
    private IncidentService incidentService;

    @PostConstruct
    public void init() {
        // Initialize the columns using lambdas
        taskDescriptionColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getDescription()));
        taskRiskLevelColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getRiskLevel()));
        taskTable.setItems(taskData);

        incidentDescriptionColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getDescription()));
        incidentRiskLevelColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getRiskLevel()));
        incidentTable.setItems(incidentData);

        // Load data from service
        taskData.addAll(taskService.getAllTasks());
        incidentData.addAll(incidentService.getAllIncidents());
    }

    @FXML
    private void handleAddTask() {
        String description = taskDescriptionField.getText();
        String riskLevel = taskRiskLevelField.getText();

        if (!description.isEmpty() && !riskLevel.isEmpty()) {
            Task newTask = new Task();
            newTask.setDescription(description);
            newTask.setRiskLevel(riskLevel);
            taskService.saveTask(newTask); // Save to service
            taskData.add(newTask);
            taskDescriptionField.clear();
            taskRiskLevelField.clear();
        }
    }

    @FXML
    private void handleDeleteTask() {
        int selectedIndex = taskTable.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            Task task = taskTable.getItems().get(selectedIndex);
            taskService.deleteTask(task); // Delete from service
            taskData.remove(selectedIndex);
        }
    }

    @FXML
    private void handleAddIncident() {
        String description = incidentDescriptionField.getText();
        String riskLevel = incidentRiskLevelField.getText();

        if (!description.isEmpty() && !riskLevel.isEmpty()) {
            Incident newIncident = new Incident();
            newIncident.setDescription(description);
            newIncident.setRiskLevel(riskLevel);
            incidentService.saveIncident(newIncident); // Save to service
            incidentData.add(newIncident);
            incidentDescriptionField.clear();
            incidentRiskLevelField.clear();
        }
    }

    @FXML
    private void handleDeleteIncident() {
        int selectedIndex = incidentTable.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            Incident incident = incidentTable.getItems().get(selectedIndex);
            incidentService.deleteIncident(incident); // Delete from service
            incidentData.remove(selectedIndex);
        }
    }
}
