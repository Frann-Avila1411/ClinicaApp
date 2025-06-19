package com.example.clinicaapp.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "medical_records")
public class MedicalRecord {
    @PrimaryKey(autoGenerate = true)
    public int id;

    public int patientId;
    public String diagnosis;
    public String prescription; // Descripcion de medicamentos
    public String date;
}
