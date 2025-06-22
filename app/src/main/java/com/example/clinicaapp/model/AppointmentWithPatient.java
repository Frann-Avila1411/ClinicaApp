package com.example.clinicaapp.model;

import androidx.room.Embedded;
import androidx.room.ColumnInfo;

public class AppointmentWithPatient {

    @Embedded
    public Appointment appointment;

    @ColumnInfo(name = "name")
    public String patientName;
}
