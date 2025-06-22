package com.example.clinicaapp.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.clinicaapp.model.MedicalRecord;

import java.util.List;

@Dao
public interface MedicalRecordDao {

    @Insert
    void insert(MedicalRecord record);

    @Update
    void update(MedicalRecord record);

    @Delete
    void delete(MedicalRecord record);

    @Query("SELECT * FROM medical_records WHERE patientId = :patientId")
    List<MedicalRecord> getRecordsByPatientId(int patientId);

    @Query("SELECT * FROM medical_records")
    List<MedicalRecord> getAll();
}
