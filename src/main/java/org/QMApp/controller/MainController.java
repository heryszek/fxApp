package org.QMApp.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import org.QMApp.model.Incident;
import org.QMApp.model.Task;
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
    private TableView<Incident> incidentTable;

    @FXML
    private TableColumn<Incident, String> incidentDescriptionColumn;

    @FXML
    private TableColumn<Incident, String> incidentRiskLevelColumn;

    private final ObservableList<Task> taskData = FXCollections.observableArrayList();
    private final ObservableList<Incident> incidentData = FXCollections.observableArrayList();

    @PostConstruct
    public void init() {
        taskDescriptionColumn.setCellValueFactory(new PropertyValueFactory<>("description"));
        taskRiskLevelColumn.setCellValueFactory(new PropertyValueFactory<>("riskLevel"));
        taskTable.setItems(taskData);

        incidentDescriptionColumn.setCellValueFactory(new PropertyValueFactory<>("description"));
        incidentRiskLevelColumn.setCellValueFactory(new PropertyValueFactory<>("riskLevel"));
        incidentTable.setItems(incidentData);
    }

    @FXML
    private void handleAddTask() {
        Task newTask = new Task();
        newTask.setDescription("New Task");
        newTask.setRiskLevel("Low");
        taskData.add(newTask);
    }

    @FXML
    private void handleDeleteTask() {
        int selectedIndex = taskTable.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            taskData.remove(selectedIndex);
        }
    }

    @FXML
    private void handleAddIncident() {
        Incident newIncident = new Incident();
        newIncident.setDescription("New Incident");
        newIncident.setRiskLevel("High");
        incidentData.add(newIncident);
    }

    @FXML
    private void handleDeleteIncident() {
        int selectedIndex = incidentTable.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            incidentData.remove(selectedIndex);
        }
    }
}
