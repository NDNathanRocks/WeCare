package com.example.wecare;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.LinkedList;
import java.util.List;

public class Reminder extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reminder);

        List<String> items = new LinkedList<>();

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        DemoAdapter adapter = new DemoAdapter(items);
        recyclerView.setAdapter(adapter);

        Button addButton = (Button) findViewById(R.id.add);
        EditText userInput = (EditText) findViewById(R.id.textToItem);

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                items.add(userInput.getText().toString());
                adapter.notifyItemInserted(items.size()-1);
//                goToMyDatePicker(view);
            }
        });

    }

    public void goToMyDatePicker(View view) {
        Intent intent1 = new Intent(this, MyDatePicker.class);
        startActivity(intent1);
    }

    public void goToMedicineList(View view) {
        String strEmail = getIntent().getStringExtra("email");
        Intent intent1 = new Intent(this, MedicineList.class);
        intent1.putExtra("email", strEmail);
        startActivity(intent1);
    }


}
