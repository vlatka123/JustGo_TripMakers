package com.example.marko.justgo;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class Profile extends AppCompatActivity {

    // Declaration of variables
    Button change_profile_picture;
    Button change_user_data;
    Button logout_from_app;
    Button deactivate_account;

    FirebaseAuth firebaseAuth;
    FirebaseUser firebaseUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        // Toolbar initialization
        Toolbar toolbar = (Toolbar) findViewById(R.id.profile_toolbar);
        setSupportActionBar(toolbar);

        // Button initialization
        change_profile_picture = (Button) findViewById(R.id.btn_add_picture);
        change_user_data = (Button) findViewById(R.id.btn_change_data);
        logout_from_app = (Button) findViewById(R.id.btn_logout);
        deactivate_account = (Button) findViewById(R.id.btn_deactive);

        // Firebase
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseUser = firebaseAuth.getCurrentUser();

        // Poruka koja ce se pojaviti kada korisnik odluci deaktivirati account
        final String deactivate_message = "You'll permanently delete the account and you won't be able to retrieve your data!";

        // Log out from account, return to log in screen
        logout_from_app.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(Profile.this, Login.class));
                finish();
            }
        });

        // Permanently delete account
        deactivate_account.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final AlertDialog.Builder conform_deletion = new AlertDialog.Builder(Profile.this, R.style.MyAlertDialogTheme);
                conform_deletion.setTitle("Deactivate account");
                conform_deletion.setMessage(deactivate_message);
                conform_deletion.setCancelable(false);

                // Positive button (Delete)
                conform_deletion.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        firebaseUser.delete().addOnCompleteListener(new OnCompleteListener<Void>() {

                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if(task.isSuccessful()) {
                                    Toast.makeText(Profile.this, "Account deleted", Toast.LENGTH_LONG).show();
                                    Intent intent = new Intent(Profile.this, Login.class);
                                    startActivity(intent);
                                    finish();
                                }
                                else {
                                    Toast.makeText(Profile.this, task.getException().getMessage(), Toast.LENGTH_LONG).show();
                                }
                            }
                        });
                    }
                });

                // Negative button (Cancel)
                conform_deletion.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });

                conform_deletion.show();
            }
        });

    }

    // Omogucuje da se vidi menu na custom toolbar (tri tockice)
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    // Omogucava da se preko menu-a ide na druge activity (za sada ne postoje drugi activity)
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.profile_menu:
                Toast.makeText(getApplicationContext(), "Profile", Toast.LENGTH_SHORT).show();

                break;
            case R.id.my_trips_menu:
                Toast.makeText(getApplicationContext(), "My trips", Toast.LENGTH_SHORT).show();
                break;
            case R.id.utilities_menu:

                break;
            case R.id.bug_report_menu:
                Toast.makeText(getApplicationContext(), "Bug", Toast.LENGTH_SHORT).show();
                break;
            case R.id.check_for_updates_menu:
                Toast.makeText(getApplicationContext(), "Check for updates", Toast.LENGTH_SHORT).show();
                break;

                default:
        }
        return super.onOptionsItemSelected(item);
    }
}
