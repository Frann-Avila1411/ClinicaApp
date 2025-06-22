package com.example.clinicaapp.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.clinicaapp.model.Patient;
import com.example.clinicaapp.model.User;
import com.example.clinicaapp.model.Appointment;
import com.example.clinicaapp.model.MedicalRecord;
import com.example.clinicaapp.database.PatientDao;

@Database(entities = {User.class, Appointment.class, MedicalRecord.class, Patient.class}, version = 5, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {

    private static AppDatabase instance;

    public abstract UserDao userDao();
    public abstract AppointmentDao appointmentDao();
    public abstract MedicalRecordDao medicalRecordDao();

    public abstract PatientDao patientDao();

    public static synchronized AppDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                            AppDatabase.class, "clinic_database")
                    .fallbackToDestructiveMigration()
                    .allowMainThreadQueries() // solo para pruebas
                    .build();
        }
        return instance;
    }

}