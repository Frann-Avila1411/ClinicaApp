package com.example.clinicaapp.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.clinicaapp.R;
import com.example.clinicaapp.model.Appointment;
import com.example.clinicaapp.model.AppointmentWithPatient;

import java.util.List;

public class AppointmentAdapter extends RecyclerView.Adapter<AppointmentAdapter.AppointmentViewHolder> {

    private List<AppointmentWithPatient> appointmentList;

    public interface OnAppointmentClickListener {
        void onEditClick(Appointment appointment);
        void onDeleteClick(Appointment appointment);
    }

    private OnAppointmentClickListener listener;

    public void setOnAppointmentClickListener(OnAppointmentClickListener listener) {
        this.listener = listener;
    }

    // Constructor corregido: solo recibe la lista
    public AppointmentAdapter(List<AppointmentWithPatient> appointmentList) {
        this.appointmentList = appointmentList;
    }

    // Método para actualizar la lista y refrescar la vista
    public void setAppointments(List<AppointmentWithPatient> citas) {
        this.appointmentList = citas;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public AppointmentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_appointment, parent, false);
        return new AppointmentViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AppointmentViewHolder holder, int position) {
        AppointmentWithPatient item = appointmentList.get(position);
        Appointment appointment = item.appointment;

        holder.tvPatientName.setText("Paciente: " + item.patientName);
        holder.tvDoctorName.setText("Doctor: " + appointment.getDoctorName());
        holder.tvDate.setText("Fecha: " + appointment.getDate());
        holder.tvTime.setText("Hora: " + appointment.getTime());
        holder.tvStatus.setText("Estado: " + appointment.getStatus());

        holder.btnEdit.setOnClickListener(v -> {
            if (listener != null) listener.onEditClick(appointment);
        });

        holder.btnDelete.setOnClickListener(v -> {
            if (listener != null) listener.onDeleteClick(appointment);
        });
    }

    @Override
    public int getItemCount() {
        return appointmentList != null ? appointmentList.size() : 0;
    }

    public static class AppointmentViewHolder extends RecyclerView.ViewHolder {
        TextView tvDate, tvTime, tvPatientName, tvDoctorName, tvStatus;
        ImageButton btnEdit, btnDelete;

        public AppointmentViewHolder(@NonNull View itemView) {
            super(itemView);
            tvDate = itemView.findViewById(R.id.tvDate);
            tvTime = itemView.findViewById(R.id.tvTime);
            tvPatientName = itemView.findViewById(R.id.tvPatientName);
            tvDoctorName = itemView.findViewById(R.id.tvDoctorName);
            tvStatus = itemView.findViewById(R.id.tvStatus);
            btnEdit = itemView.findViewById(R.id.btnEdit);
            btnDelete = itemView.findViewById(R.id.btnDelete);
        }
    }
}