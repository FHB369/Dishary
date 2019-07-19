package com.nrick.dishary;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class QRScan extends AppCompatActivity {

    private TextView status, Name, Population, Male, Female, Location, Date;
    private EditText textName, textPopulation, textMale, textFemale, textLocation, textDate, textBP1, textBP2, textBP3;
    private Button update;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qrscan);

        Intent intent = getIntent();
        final String cd = intent.getStringExtra("id");

        status = findViewById(R.id.textStatus);
        Name = findViewById(R.id.Name);
        Population = findViewById(R.id.Population);
        Male = findViewById(R.id.Male);
        Female = findViewById(R.id.Female);
        Location = findViewById(R.id.Location);
        Date = findViewById(R.id.Date);

        textName = findViewById(R.id.textName);
        textPopulation = findViewById(R.id.textPopulation);
        textMale = findViewById(R.id.textMale);
        textFemale = findViewById(R.id.textFemale);
        textLocation = findViewById(R.id.textLocation);
        textDate = findViewById(R.id.textDate);
        textBP1 = findViewById(R.id.textBP1);
        textBP2 = findViewById(R.id.textBP2);
        textBP3 = findViewById(R.id.textBP3);

        update = findViewById(R.id.submitBtn);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("patients");
        myRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.hasChild(cd)){
                    status.setText("রোগীর তথ্য পাওয়া গেছে");
                    Name.setVisibility(View.VISIBLE);
                    Population.setVisibility(View.VISIBLE);
                    Male.setVisibility(View.VISIBLE);
                    Female.setVisibility(View.VISIBLE);
                    Location.setVisibility(View.VISIBLE);
                    Date.setVisibility(View.VISIBLE);
                    textBP1.setVisibility(View.VISIBLE);
                    textBP2.setVisibility(View.VISIBLE);
                    textBP3.setVisibility(View.VISIBLE);
                    Name.setText("রোগীর নামঃ "+dataSnapshot.child(cd).child("name").getValue());
                    Population.setText("পরিবারের লোকসংখ্যাঃ "+dataSnapshot.child(cd).child("family_members").getValue());
                    Male.setText("পুরুষ সদস্যঃ "+dataSnapshot.child(cd).child("male_child").getValue());
                    Female.setText("নারী সদস্যঃ "+dataSnapshot.child(cd).child("female_child").getValue());
                    Location.setText("এলাকার নামঃ "+dataSnapshot.child(cd).child("location").getValue());
                    Date.setText("সন্তান জন্মের তারিখঃ "+dataSnapshot.child(cd).child("date").getValue());
                    textBP1.setText(""+ dataSnapshot.child(cd).child("bp1").getValue());
                    textBP2.setText(""+ dataSnapshot.child(cd).child("bp2").getValue());
                    textBP3.setText(""+ dataSnapshot.child(cd).child("bp3").getValue());

                    update.setVisibility(View.VISIBLE);
                    update.setText("রোগীর তথ্য আপডেট করুন");

                    update.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {

                            if (textBP1.getText().toString().isEmpty() ||
                                    textBP2.getText().toString().isEmpty() ||
                                    textBP3.getText().toString().isEmpty()) {
                                Toast.makeText(getApplicationContext(), "Please Fill all the fields", Toast.LENGTH_SHORT).show();
                            } else {
                                DatabaseReference myRef = FirebaseDatabase.getInstance().getReference("patients").child(cd);
                                myRef.child("bp1").setValue(textBP1.getText().toString());
                                myRef.child("bp2").setValue(textBP2.getText().toString());
                                myRef.child("bp3").setValue(textBP3.getText().toString());

                                Intent intent = new Intent(QRScan.this, BPSuggestion.class);
                                intent.putExtra("BP1", textBP1.getText().toString());
                                intent.putExtra("BP2", textBP2.getText().toString());
                                intent.putExtra("BP3", textBP3.getText().toString());
                                startActivity(intent);
                                finish();
                            }
                        }
                    });

                }else{
                    status.setText("রোগীর তথ্য পাওয়া যায়নি");
                    textName.setVisibility(View.VISIBLE);
                    textPopulation.setVisibility(View.VISIBLE);
                    textMale.setVisibility(View.VISIBLE);
                    textFemale.setVisibility(View.VISIBLE);
                    textLocation.setVisibility(View.VISIBLE);
                    textDate.setVisibility(View.VISIBLE);
                    textBP1.setVisibility(View.VISIBLE);
                    textBP2.setVisibility(View.VISIBLE);
                    textBP3.setVisibility(View.VISIBLE);

                    update.setVisibility(View.VISIBLE);
                    update.setText("রোগীর তথ্য যোগ করুন");

                    update.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {

                            if(textName.getText().toString().isEmpty() ||
                                    textPopulation.getText().toString().isEmpty() ||
                                    textMale.getText().toString().isEmpty() ||
                                    textFemale.getText().toString().isEmpty() ||
                                    textLocation.getText().toString().isEmpty() ||
                                    textDate.getText().toString().isEmpty() ||
                                    textBP1.getText().toString().isEmpty() ||
                                    textBP2.getText().toString().isEmpty() ||
                                    textBP3.getText().toString().isEmpty()) {
                                Toast.makeText(getApplicationContext(), "Please Fill all the fields", Toast.LENGTH_SHORT).show();
                            }
                            else{
                                DatabaseReference myRef = FirebaseDatabase.getInstance().getReference("patients").child(cd);
                                myRef.child("name").setValue(textName.getText().toString());
                                myRef.child("family_members").setValue(textPopulation.getText().toString());
                                myRef.child("male_child").setValue(textMale.getText().toString());
                                myRef.child("female_child").setValue(textFemale.getText().toString());
                                myRef.child("location").setValue(textLocation.getText().toString());
                                myRef.child("date").setValue(textDate.getText().toString());
                                myRef.child("bp1").setValue(textBP1.getText().toString());
                                myRef.child("bp2").setValue(textBP2.getText().toString());
                                myRef.child("bp3").setValue(textBP3.getText().toString());

                                Intent intent = new Intent(QRScan.this, BPSuggestion.class);
                                startActivity(intent);
                                finish();
                            }
                        }
                    });
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }
}
