package com.mad.assessment4;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SettingsActivity extends AppCompatActivity {

    Button update;
    EditText name;
    EditText address;
    FirebaseUser firebaseUser;
    DatabaseReference mDatabase;
    User userObj;
    TextView userName;
    TextView userAddress;
    TextView userEmail;


    String nameStr;
    String addressStr;
    String email;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        update = findViewById(R.id.buttonUpdateAccount);
        name = findViewById(R.id.editTextUserName);
        address = findViewById(R.id.editTextUserAddress);
        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        mDatabase =  FirebaseDatabase.getInstance().getReference();
        email = firebaseUser.getEmail();
        userName = findViewById(R.id.textViewUserName);
        userAddress = findViewById(R.id.textViewuserAddress);
        userEmail = findViewById(R.id.textViewUserEmail);


        final String userUid = firebaseUser.getUid().toString();



        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {



                nameStr = name.getText().toString();
                addressStr = address.getText().toString();


                userObj = new User(nameStr,addressStr,email);
                mDatabase = FirebaseDatabase.getInstance().getReference("UserProfile").child(userUid);
                String key = mDatabase.push().getKey();
                mDatabase.child(key).setValue(userObj).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(SettingsActivity.this, "User details Saved successfully", Toast.LENGTH_LONG).show();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(SettingsActivity.this, "Round failed to Save", Toast.LENGTH_LONG).show();
                    }
                });




            }
        });


    }
}