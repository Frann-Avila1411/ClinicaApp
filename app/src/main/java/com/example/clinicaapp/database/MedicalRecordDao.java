package com.example.clinicaapp.database;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.clinicaapp.model.MedicalRecord;

import java.util.List;

@Dao
public interface MedicalRecordDao {

    @Insert
    void insert(MedicalRecord record);

    @Query("SELECT * FROM medical_records WHERE patientId = :patientId")
    List<MedicalRecord> getRecordsByPatient(String patientId);

    @Query("SELECT * FROM medical_records")
    List<MedicalRecord> getAll();
}
