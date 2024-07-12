package org.QMApp.controller;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import org.QMApp.model.Incident;
import org.QMApp.service.IncidentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class IncidentController {
    @Autowired
    private IncidentService incidentService;

    @FXML
    private TableView<Incident> incidentTable;

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
        loadIncidents();
    }

    private void loadIncidents() {
        incidentTable.setItems(FXCollections.observableList(incidentService.getAllIncidents()));
    }

    @FXML
    private void addIncident() {
        Incident incident = new Incident();
        incident.setDescription(descriptionField.getText());
        incident.setDate(dateField.getValue());
        incident.setRiskLevel(riskLevelField.getText());
        incident.setReportedBy(reportedByField.getText());

        incidentService.saveIncident(incident);
        loadIncidents();
    }

    @FXML
    private void deleteIncident() {
        Incident selectedIncident = incidentTable.getSelectionModel().getSelectedItem();
        if (selectedIncident != null) {
            incidentService.deleteIncident(selectedIncident.getId());
            loadIncidents();
        }
    }
}
