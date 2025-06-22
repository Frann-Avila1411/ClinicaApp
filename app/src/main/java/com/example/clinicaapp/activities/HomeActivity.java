package com.example.clinicaapp.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import com.example.clinicaapp.database.AppDatabase;
import com.example.clinicaapp.fragments.AddApointmentFragment;
import com.example.clinicaapp.fragments.AddMedicalRecordFragment;
import com.example.clinicaapp.fragments.AddPatientFragment;
import com.example.clinicaapp.fragments.AppointmentsFragment;
import com.example.clinicaapp.fragments.RecordFragment;
import com.example.clinicaapp.utils.SessionManager;
import com.google.android.material.navigation.NavigationView;
import com.example.clinicaapp.R;

public class HomeActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    DrawerLayout drawerLayout;
    NavigationView navigationView;
    AppDatabase db;
    SessionManager sessionManager;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Cargar la vista antes de acceder a sus elementos
        setContentView(R.layout.activity_home);

        // Inicializar componentes
        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        toolbar = findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawerLayout, toolbar,
                R.string.navigation_drawer_open,
                R.string.navigation_drawer_close
        );
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        navigationView.setNavigationItemSelectedListener(this);

        // Validar sesión
        sessionManager = new SessionManager(this);
        int userId = sessionManager.getUserId();
        if (userId == -1) {
            startActivity(new Intent(this, LoginActivity.class));
            finish();
            return;
        }

        db = AppDatabase.getInstance(this);

        // Mostrar fragmento por defecto
        if (savedInstanceState == null) {
            loadFragment(new AppointmentsFragment());
            navigationView.setCheckedItem(R.id.nav_appointments);
        }

        // Mostrar nombre del usuario en el encabezado
        View headerView = navigationView.getHeaderView(0);
        TextView tvHeaderUsername = headerView.findViewById(R.id.tvHeaderUsername);
        String userName = db.userDao().getUserNameById(userId);
        tvHeaderUsername.setText("Bienvenido, " + userName);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.nav_appointments) {
            loadFragment(new AppointmentsFragment());
        } else if (id == R.id.nav_add_appointment) {
            loadFragment(new AddApointmentFragment());
        }
            else if (id == R.id.nav_add_patient){
                loadFragment(new AddPatientFragment());
        }
          else if (id == R.id.nav_add_medical_record) {
              loadFragment(new AddMedicalRecordFragment());
            }
         else if (id == R.id.nav_medical_records) {
            loadFragment(new RecordFragment());
        } else if (id == R.id.nav_logout) {
            sessionManager.clearSession();
            Intent intent = new Intent(this, LoginActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
            finish();
            return true;
        }

        drawerLayout.closeDrawers();
        return true;
    }

    private void loadFragment(Fragment fragment) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container, fragment)
                .commit();
    }
}