package com.example.marko.justgo;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;


public class Registration extends AppCompatActivity {

    // Declaration of variables
    private Button already_have_an_account;
    private Button create_account;
    private EditText email;
    private EditText password;
    private EditText confirm_password;


    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        // Firebase
        firebaseAuth = FirebaseAuth.getInstance();

        // Buttons
        already_have_an_account = (Button) findViewById(R.id.already_have_a_account);
        create_account = (Button) findViewById(R.id.registration_signup);

        // EditText
        email = (EditText) findViewById(R.id.set_email);
        password = (EditText) findViewById(R.id.set_password);
        confirm_password = (EditText) findViewById(R.id.set_confirm_password);

        // Button already have an account, return you to login screen
        already_have_an_account.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Registration.this, Login.class);
                startActivity(intent);
                finish();

            }
        });

        // Button create a new account, when you enter data creates a new account and return you to login screen
        create_account.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Variable za podatke (email, password i confirm password)
                String take_email_data = email.getText().toString().trim();
                String take_password_data = password.getText().toString().trim();
                String check_password_data = confirm_password.getText().toString().trim();
                String check_strong_pasword = "^.*(?=.{6,20})(?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).*$";

                // Provjeri jeli neko polje prazno ako je prijavi to korisniku
                if(TextUtils.isEmpty(take_email_data) || TextUtils.isEmpty(take_password_data) || TextUtils.isEmpty(check_password_data)) {
                    Toast.makeText(getApplicationContext(), "Please fill all fields!", Toast.LENGTH_LONG).show();
                }
                else {
                    // Jeli korisnkik unio isti string u polje password i confirm password, ako nije obavijesti ga
                    if(take_password_data.equals(check_password_data)) {

                        // Jeli password sadrzi barem 1 veliko i 1 malo slovo i 1 broj te minimalno 6 simbola pri cemu maksimalno 20
                        if(take_password_data.matches(check_strong_pasword)) {

                            firebaseAuth.createUserWithEmailAndPassword(take_email_data, take_password_data).addOnCompleteListener(Registration.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    // Jeli uspjesno obavljena registracija
                                    if (task.isSuccessful()) {
                                        Toast.makeText(Registration.this, "You are succesfully registrated", Toast.LENGTH_LONG).show();
                                        Intent intent = new Intent(Registration.this, Login.class);
                                        startActivity(intent);
                                        finish();
                                    } else {
                                        Toast.makeText(Registration.this, task.getException().getMessage(), Toast.LENGTH_LONG).show();
                                    }
                                }
                            });
                        }
                        else {
                            Toast.makeText(getApplicationContext(), "You password must contain at 6-20 characters and 1 uppercase and lowercase and 1 number", Toast.LENGTH_LONG).show();
                        }
                    }
                    else {
                        Toast.makeText(getApplicationContext(), "Passwords are different!", Toast.LENGTH_LONG).show();
                    }
                }
            }
        });

    }
}
