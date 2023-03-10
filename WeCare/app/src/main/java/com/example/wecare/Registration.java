//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.example.wecare;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Registration extends AppCompatActivity {
    EditText userRegEmail;
    EditText userRegPassword;
    EditText userRegUsername;

    ImageButton signupbtn;

    FirebaseAuth mAuth;

    DatabaseReference databaseReference = FirebaseDatabase.getInstance("https://wecaredbsystem-default-rtdb.firebaseio.com/").getReference();

    @SuppressLint("WrongViewCast")
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.activity_registration);

        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();

        // connecting to button with button's ID
        userRegEmail = findViewById(R.id.email);
        userRegPassword = findViewById(R.id.registerpassword);
        userRegUsername = findViewById(R.id.registerusername);

        // initiate createUser function when sign up button pressed
        signupbtn = (ImageButton) findViewById(R.id.signupbutton);
        signupbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createUser();
            }
        });
    }

    private void createUser() {

        // get strings from user inputs
        String email = userRegEmail.getText().toString();
        String password = userRegPassword.getText().toString();
        String username = userRegUsername.getText().toString();

        // if a field is empty show a error
        if (TextUtils.isEmpty(email) || TextUtils.isEmpty(password) || TextUtils.isEmpty(username)){
            userRegEmail.setError("Please fill out all fields!");
            userRegEmail.requestFocus();
        }else if (password.length() < 4) {
            Toast.makeText(Registration.this, "Password must be greater than 4 characters", Toast.LENGTH_SHORT).show();
        } else {

            databaseReference.child("users").addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    // if database has string (account already exists) show error
                    if (snapshot.hasChild(email)) {

                        Toast.makeText(Registration.this, "Email in use", Toast.LENGTH_SHORT).show();

                    } else { // if email not in use

                        // Send users sign up data to database
                        databaseReference.child("users").child(email).child("full name").setValue(username);
                        databaseReference.child("users").child(email).child("password").setValue(password);

                        Toast.makeText(Registration.this, "User registered successfully", Toast.LENGTH_SHORT).show();
                        finish();

                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        }

    }
}
