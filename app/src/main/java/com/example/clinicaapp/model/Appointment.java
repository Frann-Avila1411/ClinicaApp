package com.example.clinicaapp.model;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "appointments")
public class Appointment {
    @PrimaryKey(autoGenerate = true)
    public int id;

    public int patientId;
    public int doctorId;
    public String date;
    public String time;
    public String status; // Confirmada, Pendiente, etc.

    //Getter


    public int getId() {
        return id;
    }

    public int getPatientId() {
        return patientId;
    }

    public int getDoctorId() {
        return doctorId;
    }

    public String getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }

    public String getPatientName() {
        return patientName;
    }

    public String getStatus() {
        return status;
    }

    //Setter


    public void setId(int id) {
        this.id = id;
    }

    public void setDoctorId(int doctorId) {
        this.doctorId = doctorId;
    }

    public void setPatientId(int patientId) {
        this.patientId = patientId;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    // Campo no persistente para mostrar en RecyclerView
    @Ignore
    public String patientName;

    @Ignore
    public String doctorName;
}