package com.example.clinicaapp.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.clinicaapp.R;
import com.example.clinicaapp.model.MedicalRecord;

import java.util.List;

public class MedicalRecordAdapter extends RecyclerView.Adapter<MedicalRecordAdapter.ViewHolder> {

    private List<MedicalRecord> medicalRecords;

    public MedicalRecordAdapter(List<MedicalRecord> medicalRecords) {
        this.medicalRecords = medicalRecords;
    }

    public void setMedicalRecords(List<MedicalRecord> medicalRecords) {
        this.medicalRecords = medicalRecords;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MedicalRecordAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_medical_record, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MedicalRecordAdapter.ViewHolder holder, int position) {
        MedicalRecord record = medicalRecords.get(position);

        holder.tvDiagnosis.setText(record.diagnosis != null ? record.diagnosis : "No diagnóstico");
        holder.tvPrescription.setText(record.prescription != null ? record.prescription : "No prescripción");
        holder.tvDate.setText(record.date != null ? record.date : "No fecha");

        if (record.patientName != null && !record.patientName.isEmpty()) {
            holder.tvPatientName.setText("Paciente: " + record.patientName);
            holder.tvPatientName.setVisibility(View.VISIBLE);
        } else {
            holder.tvPatientName.setVisibility(View.GONE);
        }
    }

    @Override
    public int getItemCount() {
        return medicalRecords != null ? medicalRecords.size() : 0;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvDiagnosis, tvPrescription, tvDate, tvPatientName;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvDiagnosis = itemView.findViewById(R.id.tvDiagnosis);
            tvPrescription = itemView.findViewById(R.id.tvPrescription);
            tvDate = itemView.findViewById(R.id.tvDate);
            tvPatientName = itemView.findViewById(R.id.tvPatientName);
        }
    }
}
