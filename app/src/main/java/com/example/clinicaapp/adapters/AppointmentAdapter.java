package com.example.clinicaapp.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.clinicaapp.R;
import com.example.clinicaapp.database.AppDatabase;
import com.example.clinicaapp.model.Appointment;

import java.util.List;

public class AppointmentAdapter extends RecyclerView.Adapter<AppointmentAdapter.AppointmentViewHolder> {

    private List<Appointment> appointmentList;
    private AppDatabase db;

    public interface OnAppointmentClickListener {
        void onEditClick(Appointment appointment);
        void onDeleteClick(Appointment appointment);
    }

    private OnAppointmentClickListener listener;

    public void setOnAppointmentClickListener(OnAppointmentClickListener listener) {
        this.listener = listener;
    }

    public AppointmentAdapter(List<Appointment> appointmentList, AppDatabase db) {
        this.appointmentList = appointmentList;
        this.db = db;
    }

    public void setAppointments(List<Appointment> citas) {
        this.appointmentList = citas;
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
        Appointment appointment = appointmentList.get(position);

        String patientName = db.userDao().getUserNameById(appointment.getPatientId());
        String doctorName = db.userDao().getUserNameById(appointment.getDoctorId());

        holder.tvPatientName.setText("Paciente: " + patientName);
        holder.tvDoctorName.setText("Doctor: " + doctorName);
        holder.tvDate.setText("Fecha: " + appointment.getDate());
        holder.tvTime.setText("Hora: " + appointment.getTime());
        holder.tvStatus.setText("Estado: " + appointment.getStatus());

        // Enlazar acciones de botones
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