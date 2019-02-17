package com.example.marko.justgo;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class StartingScreen extends AppCompatActivity {

    // Declaration variable za provjeru jeli postoji korisnik ove app
    FirebaseUser firebaseUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_starting_screen);

        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();

        // Prikazi pocetni zaslon 1,5 sekundi i automatski prijedi na log in screen ili na profil u slucaju da se korisnik obavio log in zadnji i nije se odlogirao
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {

                // Automatski Log in ako user postoji i od zadnjeg puta je ostao ulogiran
                if (firebaseUser != null) {
                    Intent intent = new Intent(StartingScreen.this, MainScreen.class);
                    startActivity(intent);
                    finish();
                }
                else {
                    Intent intent = new Intent(StartingScreen.this, Login.class);
                    startActivity(intent);
                    finish();
                }
            }
        }, 1500);
    }
}
