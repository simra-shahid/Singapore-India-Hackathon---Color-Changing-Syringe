package com.example.swasthya;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import java.util.Calendar;
import java.util.Date;


public class New_transaction extends AppCompatActivity {
    EditText name, syringe_taken, dustbin_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_transaction);

        name = findViewById(R.id.editText);
        syringe_taken = findViewById(R.id.editText2);
        dustbin_id = findViewById(R.id.dustbin_id);
        Button button  = findViewById(R.id.button3);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatabaseReference db = FirebaseDatabase.getInstance().getReference("doctor").child("t_id_2");
                db.child("name").setValue(name.getText().toString());
                db.child("syringe_crushed").setValue(0);
                db.child("syringe_taken").setValue(syringe_taken.getText().toString());
                db.child("dustbin_id").setValue(dustbin_id.getText().toString());
                db.child("transaction_complete").setValue("False");

                Date currentTime = Calendar.getInstance().getTime();
                db.child("time_received").setValue(currentTime);
                final Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        startActivity(new Intent(New_transaction.this, Demo2.class));
                        //Do something after 100ms
                    }
                }, 1000);
            }
        });


    }
}
