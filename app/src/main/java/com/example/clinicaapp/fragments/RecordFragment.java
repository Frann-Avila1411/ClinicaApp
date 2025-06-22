package com.example.clinicaapp.fragments;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.clinicaapp.R;
import com.example.clinicaapp.adapters.MedicalRecordAdapter;
import com.example.clinicaapp.database.AppDatabase;
import com.example.clinicaapp.model.MedicalRecord;
import com.example.clinicaapp.model.User;

import java.util.List;

public class RecordFragment extends Fragment {

    RecyclerView recyclerView;
    MedicalRecordAdapter adapter;
    AppDatabase db;

    public RecordFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_medical_records, container, false);

        recyclerView = view.findViewById(R.id.recyclerMedicalRecords);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        db = AppDatabase.getInstance(getContext());

        // Obtener expedientes médicos
        List<MedicalRecord> records = db.medicalRecordDao().getAll();

        // Por cada expediente, obtener el nombre del paciente desde la tabla de usuarios
        for (MedicalRecord record : records) {
            User patient = db.userDao().getUserById(record.patientId);
            if (patient != null) {
                record.patientName = patient.getName();
            } else {
                record.patientName = "Paciente no encontrado";
            }
        }

        adapter = new MedicalRecordAdapter(records);
        recyclerView.setAdapter(adapter);

        return view;
    }
}
