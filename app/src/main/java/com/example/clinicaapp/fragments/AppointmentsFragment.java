package com.example.clinicaapp.fragments;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.clinicaapp.R;
import com.example.clinicaapp.adapters.AppointmentAdapter;
import com.example.clinicaapp.database.AppDatabase;
import com.example.clinicaapp.model.Appointment;

import java.util.List;

public class AppointmentsFragment extends Fragment {

    private RecyclerView recyclerView;
    private AppointmentAdapter adapter;
    private AppDatabase db;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_appointments, container, false);

        recyclerView = view.findViewById(R.id.recyclerAppointments);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        db = AppDatabase.getInstance(getContext());

        loadAppointments();

        return view;
    }

    private void loadAppointments() {
        List<Appointment> appointmentList = db.appointmentDao().getAll();

        adapter = new AppointmentAdapter(appointmentList, db);

        adapter.setOnAppointmentClickListener(new AppointmentAdapter.OnAppointmentClickListener() {
            @Override
            public void onEditClick(Appointment appointment) {
                // Navegar al fragmento de edición con el ID de la cita
                Bundle bundle = new Bundle();
                bundle.putInt("appointment_id", appointment.getId());

                AddApointmentFragment fragment = new AddApointmentFragment();
                fragment.setArguments(bundle);

                getParentFragmentManager().beginTransaction()
                        .replace(R.id.fragment_container, fragment)
                        .addToBackStack(null)
                        .commit();
            }

            @Override
            public void onDeleteClick(Appointment appointment) {
                new AlertDialog.Builder(getContext())
                        .setTitle("Eliminar Cita")
                        .setMessage("¿Estás seguro de eliminar esta cita?")
                        .setPositiveButton("Sí", (dialog, which) -> {
                            db.appointmentDao().delete(appointment);
                            refreshAppointments(); // Recargar citas luego de eliminar
                        })
                        .setNegativeButton("No", null)
                        .show();
            }
        });

        recyclerView.setAdapter(adapter);
    }

    private void refreshAppointments() {
        List<Appointment> updatedList = db.appointmentDao().getAll();
        adapter.setAppointments(updatedList);
        adapter.notifyDataSetChanged();
    }
}