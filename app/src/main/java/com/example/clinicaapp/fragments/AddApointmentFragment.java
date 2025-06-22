package com.example.clinicaapp.fragments;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.clinicaapp.R;
import com.example.clinicaapp.database.AppDatabase;
import com.example.clinicaapp.model.Appointment;

import java.util.List;

public class AddApointmentFragment extends Fragment {

    private AutoCompleteTextView actvPatientName;
    private EditText etDoctorName, etDate, etTime, etStatus;
    private Button btnSave;
    private AppDatabase db;


    public AddApointmentFragment() {}

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add_appointment, container, false);

        actvPatientName = view.findViewById(R.id.actvPatientName);
        etDoctorName = view.findViewById(R.id.etDoctorId);
        etDate = view.findViewById(R.id.etDate);
        etTime = view.findViewById(R.id.etTime);
        etStatus = view.findViewById(R.id.etStatus);
        btnSave = view.findViewById(R.id.btnSaveAppointment);

        db = AppDatabase.getInstance(getContext());

        setupAutoComplete();

        btnSave.setOnClickListener(v -> saveAppointment());

        return view;
    }

    private void setupAutoComplete() {
        List<String> patientNames = db.patientDao().getAllNames();
        ArrayAdapter<String> adapter = new ArrayAdapter<>(requireContext(), android.R.layout.simple_dropdown_item_1line, patientNames);
        actvPatientName.setAdapter(adapter);
    }

    private void saveAppointment() {
        String patientName = actvPatientName.getText().toString().trim();
        String doctorName = etDoctorName.getText().toString().trim();
        String date = etDate.getText().toString().trim();
        String time = etTime.getText().toString().trim();
        String status = etStatus.getText().toString().trim();

        if (patientName.isEmpty() || doctorName.isEmpty() || date.isEmpty() || time.isEmpty() || status.isEmpty()) {
            Toast.makeText(getContext(), "Por favor completa todos los campos", Toast.LENGTH_SHORT).show();
            return;
        }

        Integer patientId = db.patientDao().getPatientIdByName(patientName);

        if (patientId == null) {
            Toast.makeText(getContext(), "Paciente no registrado", Toast.LENGTH_SHORT).show();
            return;
        }

        Appointment appointment = new Appointment();
        appointment.patientId = patientId;
        appointment.doctorName = doctorName;
        appointment.date = date;
        appointment.time = time;
        appointment.status = status;

        db.appointmentDao().insert(appointment);

        Toast.makeText(getContext(), "Cita registrada exitosamente", Toast.LENGTH_SHORT).show();

        // Limpiar campos despues de guardar existosamente los datos
        actvPatientName.setText("");
        etDoctorName.setText("");
        etDate.setText("");
        etTime.setText("");
        etStatus.setText("");
    }
}
