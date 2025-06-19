package com.example.clinicaapp.activities;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.example.clinicaapp.R;

public class HomeActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);  // Aún debes crear este XML

        // Aquí puedes mostrar un mensaje de bienvenida o cargar el fragmento de inicio
        Toast.makeText(this, "Bienvenido", Toast.LENGTH_SHORT).show();
    }
}
