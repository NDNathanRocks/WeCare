package com.example.wecare;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class LoginSys extends AppCompatActivity {

    private ImageButton registerbtn;
    private ImageButton loginbtn;
    FirebaseAuth mAuth;

    EditText email;
    EditText password;

    DatabaseReference databaseReference = FirebaseDatabase.getInstance("https://wecaredbsystem-default-rtdb.firebaseio.com/").getReference();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_sys);
        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();

        // connecting to button with button's ID
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);

        // running a function on click of button
        loginbtn = (ImageButton)findViewById(R.id.loginbutton);
        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkLogin();
            }
        });

        registerbtn = (ImageButton)findViewById(R.id.registerbutton);
        registerbtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                openRegistration();
            }
        });
    }
    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null){
            currentUser.reload();
        }
    }

    // open registration window
    public void openRegistration(){
        Intent intent1 = new Intent(this, Registration.class);
        startActivity(intent1);
    }

    public void checkLogin(){

        // get string of user input
        String strPassword = password.getText().toString();
        String strEmail = email.getText().toString();

        databaseReference.child("users").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                // if database has string (account exists) open window
                if (TextUtils.isEmpty(strEmail) || TextUtils.isEmpty(strPassword)){
                    Toast.makeText(LoginSys.this, "Please fill out all fields", Toast.LENGTH_SHORT).show();

                }else if (snapshot.hasChild(strEmail)) {

                    databaseReference.child("users").child(strEmail).child("password").addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            String val = snapshot.getValue().toString();
                            if (val.equals(strPassword)){

                                Intent intent1 = new Intent(LoginSys.this, MedicineList.class);
                                intent1.putExtra("email", strEmail);
                                startActivity(intent1);

                            } else {
                                Toast.makeText(LoginSys.this, "Incorrect password or username, please try again", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });
                }else{ // show error if account doesnt exist
                    Toast.makeText(LoginSys.this, "User doesnt exist", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    };

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
