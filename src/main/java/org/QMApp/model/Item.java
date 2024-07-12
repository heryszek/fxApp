package org.QMApp.model;


public class Item {
    private String description;
    private String date;
    private String riskLevel;
    private String reportedBy;

    public Item(String description, String date, String riskLevel, String reportedBy) {
        this.description = description;
        this.date = date;
        this.riskLevel = riskLevel;
        this.reportedBy = reportedBy;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getRiskLevel() {
        return riskLevel;
    }

    public void setRiskLevel(String riskLevel) {
        this.riskLevel = riskLevel;
    }

    public String getReportedBy() {
        return reportedBy;
    }

    public void setReportedBy(String reportedBy) {
        this.reportedBy = reportedBy;
    }
}
