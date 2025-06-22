package com.example.clinicaapp.fragments;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.clinicaapp.R;
import com.example.clinicaapp.database.AppDatabase;
import com.example.clinicaapp.model.Patient;

public class AddPatientFragment extends Fragment {

    private EditText etName, etAge, etGender;
    private Button btnSave;
    private AppDatabase db;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add_patient, container, false);

        etName = view.findViewById(R.id.etName);
        etAge = view.findViewById(R.id.etAge);
        etGender = view.findViewById(R.id.etGender);
        btnSave = view.findViewById(R.id.btnSavePatient);
        db = AppDatabase.getInstance(getContext());

        btnSave.setOnClickListener(v -> {
            String name = etName.getText().toString().trim();
            String ageStr = etAge.getText().toString().trim();
            String gender = etGender.getText().toString().trim();

            if (TextUtils.isEmpty(name) || TextUtils.isEmpty(ageStr) || TextUtils.isEmpty(gender)) {
                Toast.makeText(getContext(), "Completa todos los campos", Toast.LENGTH_SHORT).show();
                return;
            }

            int age = Integer.parseInt(ageStr);
            Patient patient = new Patient();
            patient.name = name;
            patient.age = age;
            patient.gender = gender;

            db.patientDao().insert(patient);
            Toast.makeText(getContext(), "Paciente guardado exitosamente", Toast.LENGTH_SHORT).show();

            etName.setText("");
            etAge.setText("");
            etGender.setText("");
        });

        return view;
    }
}
