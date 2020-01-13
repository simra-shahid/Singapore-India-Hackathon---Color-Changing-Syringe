package com.example.swasthya;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class counter_inc extends AppCompatActivity {
    String first_s, second_s;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_counter);
        Button first = findViewById(R.id.button4);
        Button second = findViewById(R.id.button5);

        final DatabaseReference db= FirebaseDatabase.getInstance().getReference("doctor").child("t_id_1");

        final DatabaseReference db2 = FirebaseDatabase.getInstance().getReference("doctor").child("t_id_2");

        db.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                try {
//                    Toast.makeText(getApplicationContext(), dataSnapshot.getValue().toString(), Toast.LENGTH_LONG).show();
//                    syringes_taken.setText(dataSnapshot.child("syringes_taken").getValue().toString());
                    first_s = dataSnapshot.child("syringe_crushed").getValue().toString();
//                    d_id.setText(dataSnapshot.child("dustbin_id").getValue().toString());
                }
                catch (NullPointerException e){
                    e.printStackTrace();}

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        db2.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                try {
//                    Toast.makeText(getApplicationContext(), dataSnapshot.getValue().toString(), Toast.LENGTH_LONG).show();
//                    syringes_taken.setText(dataSnapshot.child("syringes_taken").getValue().toString());
                    second_s = (dataSnapshot.child("syringe_crushed").getValue().toString());
//                    d_id.setText(dataSnapshot.child("dustbin_id").getValue().toString());
                }
                catch (NullPointerException e){
                    e.printStackTrace();}

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        first.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                 int first_c = Integer.parseInt(first_s) + 1;
                db.child("syringe_crushed").setValue(first_c);
            }
        });

        second.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int second_c = Integer.parseInt(second_s) + 1;
                db2.child("syringe_crushed").setValue(second_c);
            }
        });

    }
}
