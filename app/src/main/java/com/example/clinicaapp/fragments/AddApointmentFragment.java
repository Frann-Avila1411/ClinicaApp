package com.example.clinicaapp.fragments;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.clinicaapp.R;
import com.example.clinicaapp.database.AppDatabase;
import com.example.clinicaapp.model.Appointment;

public class AddApointmentFragment extends Fragment {

    private EditText etPatientId, etDoctorId, etDate, etTime, etStatus;
    private Button btnSave;
    private AppDatabase db;
    private Appointment appointmentToEdit;  // Para edición

    public AddApointmentFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add_appointment, container, false);

        etPatientId = view.findViewById(R.id.etPatientId);
        etDoctorId = view.findViewById(R.id.etDoctorId);
        etDate = view.findViewById(R.id.etDate);
        etTime = view.findViewById(R.id.etTime);
        etStatus = view.findViewById(R.id.etStatus);
        btnSave = view.findViewById(R.id.btnSaveAppointment);

        db = AppDatabase.getInstance(getContext());

        // Revisar si hay ID para editar
        Bundle args = getArguments();
        if (args != null && args.containsKey("appointment_id")) {
            int appointmentId = args.getInt("appointment_id");
            appointmentToEdit = db.appointmentDao().getById(appointmentId);

            if (appointmentToEdit != null) {
                etPatientId.setText(String.valueOf(appointmentToEdit.getPatientId()));
                etDoctorId.setText(String.valueOf(appointmentToEdit.getDoctorId()));
                etDate.setText(appointmentToEdit.getDate());
                etTime.setText(appointmentToEdit.getTime());
                etStatus.setText(appointmentToEdit.getStatus());
                btnSave.setText("Actualizar Cita");
            }
        }

        btnSave.setOnClickListener(v -> saveAppointment());

        return view;
    }

    private void saveAppointment() {
        String patientIdStr = etPatientId.getText().toString().trim();
        String doctorIdStr = etDoctorId.getText().toString().trim();
        String date = etDate.getText().toString().trim();
        String time = etTime.getText().toString().trim();
        String status = etStatus.getText().toString().trim();

        if (TextUtils.isEmpty(patientIdStr) || TextUtils.isEmpty(doctorIdStr)
                || TextUtils.isEmpty(date) || TextUtils.isEmpty(time) || TextUtils.isEmpty(status)) {
            Toast.makeText(getContext(), "Por favor completa todos los campos", Toast.LENGTH_SHORT).show();
            return;
        }

        try {
            int patientId = Integer.parseInt(patientIdStr);
            int doctorId = Integer.parseInt(doctorIdStr);

            if (appointmentToEdit != null) {
                // Editar cita existente
                appointmentToEdit.setPatientId(patientId);
                appointmentToEdit.setDoctorId(doctorId);
                appointmentToEdit.setDate(date);
                appointmentToEdit.setTime(time);
                appointmentToEdit.setStatus(status);

                db.appointmentDao().update(appointmentToEdit);
                Toast.makeText(getContext(), "Cita actualizada exitosamente", Toast.LENGTH_SHORT).show();
            } else {
                // Nueva cita
                Appointment newAppointment = new Appointment();
                newAppointment.setPatientId(patientId);
                newAppointment.setDoctorId(doctorId);
                newAppointment.setDate(date);
                newAppointment.setTime(time);
                newAppointment.setStatus(status);

                db.appointmentDao().insert(newAppointment);
                Toast.makeText(getContext(), "Cita guardada exitosamente", Toast.LENGTH_SHORT).show();
            }

            // Volver atrás (a lista)
            requireActivity().getSupportFragmentManager().popBackStack();

        } catch (NumberFormatException e) {
            Toast.makeText(getContext(), "ID de paciente y doctor deben ser números válidos", Toast.LENGTH_SHORT).show();
        }
    }
}
