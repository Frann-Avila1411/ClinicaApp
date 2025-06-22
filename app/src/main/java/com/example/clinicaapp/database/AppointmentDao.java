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

    @Query("SELECT * FROM appointments")
    List<Appointment> getAll();

    @Insert
    void insert(Appointment appointment);

    @Update
    void update(Appointment appointment);

    @Delete
    void delete(Appointment appointment);

    @Query("SELECT * FROM appointments WHERE doctorId = :doctorId")
    List<Appointment> getAppointmentsByDoctor(int doctorId);

    @Query("SELECT * FROM appointments WHERE patientId = :patientId")
    List<Appointment> getAppointmentsByPatient(int patientId);

    @Query("SELECT * FROM appointments WHERE id = :appointmentId LIMIT 1")
    Appointment getById(int appointmentId);
}