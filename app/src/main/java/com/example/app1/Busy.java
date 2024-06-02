package com.example.app1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Busy extends AppCompatActivity {

    ImageView imageView;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_busy);
        imageView=findViewById(R.id.chair);
        textView=findViewById(R.id.status);

        DatabaseReference reference= FirebaseDatabase.getInstance().getReference().child("variables");

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                data d=snapshot.getValue(data.class);
                if(d.getA().equalsIgnoreCase("0"))
                {
                    textView.setText("Cabin Empty");
                    textView.setTextColor(Color.RED);
                    imageView.setColorFilter(Color.RED);
                }
                else if(d.getA().equalsIgnoreCase("1") && d.getB().equalsIgnoreCase("1"))
                {
                    textView.setText("Present in cabin but busy");
                    textView.setTextColor(Color.RED);
                    imageView.setColorFilter(Color.RED);
                }
                else if(d.getA().equalsIgnoreCase("1") && d.getB().equalsIgnoreCase("0"))
                {
                    textView.setText("Present in cabin and free to visit");
                    textView.setTextColor(Color.GREEN);
                    imageView.setColorFilter(Color.GREEN);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getApplicationContext(),"Database error",Toast.LENGTH_SHORT).show();
            }
        });

    }
}