package com.example.clinicaapp.database;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Delete;
import androidx.room.Update;

import com.example.clinicaapp.model.Appointment;

import java.util.List;

@Dao
public interface AppointmentDao {

    @Insert
    void insert(Appointment appointment);

    @Query("SELECT * FROM appointments WHERE doctorId = :doctorId")
    List<Appointment> getAppointmentsByDoctor(int doctorId);

    @Query("SELECT * FROM appointments WHERE patientId = :patientId")
    List<Appointment> getAppointmentsByPatient(int patientId);

    @Delete
    void delete(Appointment appointment);

    @Update
    void update(Appointment appointment);
}