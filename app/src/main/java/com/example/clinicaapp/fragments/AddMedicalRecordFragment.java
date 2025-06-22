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
import com.example.clinicaapp.model.MedicalRecord;

public class AddMedicalRecordFragment extends Fragment {

    private EditText etPatientId, etDiagnosis, etPrescription, etDate;
    private Button btnSave;
    private AppDatabase db;

    public AddMedicalRecordFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add_medical_record, container, false);

        etPatientId = view.findViewById(R.id.etPatientId);
        etDiagnosis = view.findViewById(R.id.etDiagnosis);
        etPrescription = view.findViewById(R.id.etPrescription);
        etDate = view.findViewById(R.id.etDate);
        btnSave = view.findViewById(R.id.btnSaveRecord);

        db = AppDatabase.getInstance(getContext());

        btnSave.setOnClickListener(v -> saveMedicalRecord());

        return view;
    }

    private void saveMedicalRecord() {
        String patientIdStr = etPatientId.getText().toString().trim();
        String diagnosis = etDiagnosis.getText().toString().trim();
        String prescription = etPrescription.getText().toString().trim();
        String date = etDate.getText().toString().trim();

        if (TextUtils.isEmpty(patientIdStr) || TextUtils.isEmpty(diagnosis)
                || TextUtils.isEmpty(prescription) || TextUtils.isEmpty(date)) {
            Toast.makeText(getContext(), "Por favor completa todos los campos", Toast.LENGTH_SHORT).show();
            return;
        }

        try {
            int patientId = Integer.parseInt(patientIdStr);

            MedicalRecord record = new MedicalRecord();
            record.patientId = patientId;
            record.diagnosis = diagnosis;
            record.prescription = prescription;
            record.date = date;

            db.medicalRecordDao().insert(record);

            Toast.makeText(getContext(), "Expediente guardado exitosamente", Toast.LENGTH_SHORT).show();

            // Limpiar campos
            etPatientId.setText("");
            etDiagnosis.setText("");
            etPrescription.setText("");
            etDate.setText("");

        } catch (NumberFormatException e) {
            Toast.makeText(getContext(), "El ID del paciente debe ser un número válido", Toast.LENGTH_SHORT).show();
        }
    }
}