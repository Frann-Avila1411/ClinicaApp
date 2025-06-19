package com.example.clinicaapp.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "appointments")
public class Appointment {
    @PrimaryKey(autoGenerate = true)
    public int id;

    public int patientId;
    public int doctorId;
    public String date;
    public String time;
    public String status; // "Confirmada", "Pendiente", etc.
}