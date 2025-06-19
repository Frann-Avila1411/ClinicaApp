package com.example.clinicaapp.activities;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import com.example.clinicaapp.utils.SessionManager;
import com.example.clinicaapp.R;
import com.example.clinicaapp.database.AppDatabase;
import com.example.clinicaapp.model.User;

public class RegisterActivity extends AppCompatActivity {

    EditText etName, etEmail, etPassword;
    Spinner spinnerUserType;
    Button btnRegister;

    AppDatabase db;

    SessionManager sessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        sessionManager = new SessionManager(this);

        db = AppDatabase.getInstance(this);

        etName = findViewById(R.id.etName);
        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);
        spinnerUserType = findViewById(R.id.spinnerUserType);
        btnRegister = findViewById(R.id.btnRegister);

        btnRegister.setOnClickListener(v -> {
            User user = new User();
            user.name = etName.getText().toString();
            user.email = etEmail.getText().toString();
            user.password = etPassword.getText().toString();
            user.userType = spinnerUserType.getSelectedItem().toString();

            // Insertar usuario
            db.userDao().insert(user);

            // Recuperar usuario recién insertado (puede usar el correo para obtenerlo)
            User insertedUser = db.userDao().findByEmail(user.email);

            if (insertedUser != null) {
                // Guardar sesión
                sessionManager.saveSession(insertedUser.id, insertedUser.userType);

                // Redirigir al Home
                Toast.makeText(this, "Registro exitoso", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(this, HomeActivity.class));
                finish();
            } else {
                Toast.makeText(this, "Error al registrar", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
