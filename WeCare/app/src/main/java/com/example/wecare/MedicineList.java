package com.example.wecare;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class MedicineList extends AppCompatActivity {

    Button addMedicineButton;

    FirebaseAuth mAuth;
    DatabaseReference databaseReference = FirebaseDatabase.getInstance("https://wecaredbsystem-default-rtdb.firebaseio.com/").getReference();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medicine_list);

        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();

        String strEmail = getIntent().getStringExtra("email");

        Bundle bundle = new Bundle();
        bundle.putString("email", strEmail);
        // set Fragment class Arguments
        recfragment fragobj = new recfragment();
        fragobj.setArguments(bundle);


        getSupportFragmentManager().beginTransaction().replace(R.id.wrapper,fragobj).commit();


        // initiate createUser function when add button pressed
        addMedicineButton = (Button) findViewById(R.id.addMedicine);
        addMedicineButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addMed();
            }
        });
    }

    private void addMed() {
        String strEmail = getIntent().getStringExtra("email");

        Intent intent1 = new Intent(this, MedicineSearch.class);
        intent1.putExtra("email", strEmail);
        startActivity(intent1);
    }

    public void goToReminder(View view) {
        String strEmail = getIntent().getStringExtra("email");
        Intent intent1 = new Intent(this, Reminder.class);
        intent1.putExtra("email", strEmail);
        startActivity(intent1);
    }
//    public void onBackPressed() {
//        Intent intent=new Intent(MedicineList.this, LoginSys.class);
//        startActivity(intent);
//
//    }

}
