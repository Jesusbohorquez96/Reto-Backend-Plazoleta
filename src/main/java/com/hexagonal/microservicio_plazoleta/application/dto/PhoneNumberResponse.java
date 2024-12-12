package com.hexagonal.microservicio_plazoleta.application.dto;

public class PhoneNumberResponse {

    private String channel;
    private String status;

    public PhoneNumberResponse(String channel, String status) {
        this.channel = channel;
        this.status = status;
    }

    public PhoneNumberResponse() {
    }

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "PhoneNumberResponse{" +
                "channel='" + channel + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
