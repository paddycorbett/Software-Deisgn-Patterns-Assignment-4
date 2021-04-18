package com.mad.assessment4;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {


    EditText email;
    EditText password;
    Button login;
    Button register;
    FirebaseAuth mFirebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mFirebaseAuth = FirebaseAuth.getInstance();
        email = findViewById(R.id.editTextLoginEmail);
        password = findViewById(R.id.editTextLoginPassword);
        login = findViewById(R.id.buttonLogin);
        register = findViewById(R.id.buttonAccount);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String emailID = email.getText().toString();
                String passwordID = password.getText().toString();

                if ((emailID.isEmpty()) && (!passwordID.isEmpty()) ){
                    Toast.makeText(MainActivity.this,"Please Enter an Email",Toast.LENGTH_SHORT).show();
                }
                else if ((!emailID.isEmpty()) && (passwordID.isEmpty())){
                    Toast.makeText(MainActivity.this,"Please Enter Password",Toast.LENGTH_SHORT).show();
                }
                else if((emailID.isEmpty()) && (passwordID.isEmpty())){
                    Toast.makeText(MainActivity.this,"Please Enter Both Email & Password",Toast.LENGTH_SHORT).show();

                }
                else if((!emailID.isEmpty() ) && (! passwordID.isEmpty())){
                    mFirebaseAuth.signInWithEmailAndPassword(emailID, passwordID).addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()){
                                Toast.makeText(MainActivity.this,"Sign In was Successful",Toast.LENGTH_SHORT).show();
                                Intent i = new Intent(MainActivity.this, MenuActivity.class);
                                startActivity(i);

                            }
                            else{
                                Toast.makeText(MainActivity.this,"Sign In was Unsuccessful, Please Try Again",Toast.LENGTH_SHORT).show();

                            }
                        }
                    });
                }
                else{
                    Toast.makeText(MainActivity.this,"Unknown Error",Toast.LENGTH_SHORT).show();
                }
            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, RegisterActivity.class);
                startActivity(i);
            }
        });



    }


}