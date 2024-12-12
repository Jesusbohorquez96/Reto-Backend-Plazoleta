package com.hexagonal.microservicio_plazoleta.application.dto;

public class VerifyCodeResponse {

    private String to;
    private String status;
    private String dateCreated;
    private String dateUpdated;

    public VerifyCodeResponse(String to, String status, String dateCreated, String dateUpdated) {
        this.to = to;
        this.status = status;
        this.dateCreated = dateCreated;
        this.dateUpdated = dateUpdated;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(String dateCreated) {
        this.dateCreated = dateCreated;
    }

    public String getDateUpdated() {
        return dateUpdated;
    }

    public void setDateUpdated(String dateUpdated) {
        this.dateUpdated = dateUpdated;
    }

    @Override
    public String toString() {
        return "VerifyCodeResponse{" +
                "to='" + to + '\'' +
                ", status='" + status + '\'' +
                ", dateCreated='" + dateCreated + '\'' +
                ", dateUpdated='" + dateUpdated + '\'' +
                '}';
    }
}
