package com.example.clinicaapp.database;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.clinicaapp.model.Patient;

import java.util.List;

@Dao
public interface PatientDao {

    @Insert
    void insert(Patient patient);

    @Query("SELECT * FROM Patient ORDER BY name ASC")
    List<Patient> getAll();

    @Query("SELECT * FROM Patient WHERE id = :id")
    Patient getById(int id);

    @Query("SELECT name FROM Patient")
    List<String> getAllNames();

    @Query("SELECT id FROM Patient WHERE name = :name LIMIT 1")
    int getIdByName(String name);

    @Query("SELECT id FROM Patient WHERE name = :patientName LIMIT 1")
    Integer getPatientIdByName(String patientName);
}
