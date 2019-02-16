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

public class Login extends AppCompatActivity {

    // Declaration of variables
    private Button create_a_new_account;
    private Button login_to_app;
    private Button forgot_pass;
    private EditText enter_email;
    private EditText enter_password;

    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Firebase
        firebaseAuth = FirebaseAuth.getInstance();

        // Button Initialization
        create_a_new_account = (Button) findViewById(R.id.go_to_create_account);
        login_to_app = (Button) findViewById(R.id.login);
        forgot_pass = (Button) findViewById(R.id.forgot_password);

        // EditText initialization
        enter_email = (EditText) findViewById(R.id.email);
        enter_password = (EditText) findViewById(R.id.password);

        create_a_new_account.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Login.this, Registration.class);
                startActivity(intent);

            }
        });

        // Button log in, pri cemu se provjerava jesu li sva polja popunjena i imali kakvih problema sa samim logiranjem
        login_to_app.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String check_email = enter_email.getText().toString().trim();
                String check_password = enter_password.getText().toString().trim();

                // Ako polje email i password nisu popunjena upozori korisnika s toast message u protivnom probaj se ulogirati
                if (!(TextUtils.isEmpty(check_email) && TextUtils.isEmpty(check_password))) {

                    firebaseAuth.signInWithEmailAndPassword(check_email, check_password).addOnCompleteListener(Login.this, new OnCompleteListener<AuthResult>() {


                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()) {
                                Intent intent = new Intent(Login.this, MainScreen.class);
                                startActivity(intent);
                                finish();
                            }
                            else {
                                Toast.makeText(Login.this, task.getException().getMessage(), Toast.LENGTH_LONG).show();
                            }
                        }
                    });

                }
                else {
                    Toast.makeText(Login.this, "Enter email or password", Toast.LENGTH_LONG).show();
                }

            }
        });

        // Button koji ce korisnik koristiti u slucaju da ne moze se sjetiti lozinke te ce ga odvesti na activity ForgotPassword
        forgot_pass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Login.this, FogotPassword.class);
                startActivity(intent);

            }
        });
    }
}
