package com.example.clinicaapp.model;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "medical_records")
public class MedicalRecord {
    @PrimaryKey(autoGenerate = true)
    public int id;

    public int patientId;
    public String diagnosis;
    public String prescription;
    public String date;

    //Getters


    public String getDate() {
        return date;
    }

    public String getPatientName() {
        return patientName;
    }

    public String getDiagnosis() {
        return diagnosis;
    }

    // Campo auxiliar para mostrar en RecyclerView
    @Ignore
    public String patientName;
}
