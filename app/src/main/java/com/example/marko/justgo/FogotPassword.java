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
import com.google.firebase.auth.FirebaseAuth;

public class FogotPassword extends AppCompatActivity {

    // Declaration of variables
    Button send_link_to_me;
    EditText my_email;

    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fogot_password);

        // Firebase
        firebaseAuth = FirebaseAuth.getInstance();

        // Button Initialization
        send_link_to_me = (Button) findViewById(R.id.forgot_pass_btn);

        // EditText Initialization
        my_email = (EditText)  findViewById(R.id.forgot_pass_email);

        // Posalji link za promjenu password-a na upisani email, pri cemu prije provjeri jeli polje prazno
        send_link_to_me.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = my_email.getText().toString();

                // Jeli unesen tekst (email) na koji ce se poslati link za generiranje novog password-a
                if(!(TextUtils.isEmpty(email))) {
                    firebaseAuth.sendPasswordResetEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {

                            // Jeli uspjesno poslan email, ako je vrati korisnika na login, a ako nije ispisi gresku preko toast-a
                            if(task.isSuccessful()){
                                Toast.makeText(FogotPassword.this, "Link send to your email successfuly.", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(FogotPassword.this, Login.class);
                                startActivity(intent);
                                finish();
                            }
                            else {
                                Toast.makeText(FogotPassword.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            }

                        }
                    });
                }
                else {
                    Toast.makeText(FogotPassword.this, "Enter email!", Toast.LENGTH_SHORT).show();
                }

            }
        });

    }
}
