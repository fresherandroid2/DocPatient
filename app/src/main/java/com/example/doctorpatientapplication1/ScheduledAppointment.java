package com.example.doctorpatientapplication1;

public class ScheduledAppointment {

    public String name;
    public String date;
    public String requestedByUser;
    public String timeStampTask;
    public String appointmentID;
    public String createdByUser;
    public String assignedToUser;
    public String appointmentStatus;



    public ScheduledAppointment(){

    }
    public ScheduledAppointment(String name, String createdByUser, String timeStampTask) {
        this.name = name;
        this.createdByUser = createdByUser ;
        this.timeStampTask = timeStampTask;
        this.requestedByUser = requestedByUser;
        this.assignedToUser = "";
        this.appointmentStatus = "Pending";
    }
    public void setAppointmentID(String appointmentID) {
        this.appointmentID = appointmentID;
    }
}