package com.project.nomaste;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import androidx.annotation.NonNull;

public class Register extends Activity {
    TextInputEditText mail, password;
    Button registerButton;
    ProgressBar pb;
    FirebaseAuth auth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);

        auth = FirebaseAuth.getInstance();

        mail = findViewById(R.id.email_field);
        password = findViewById(R.id.password_field);
        registerButton = findViewById(R.id.register_button);
        pb = findViewById(R.id.progressBar_register);



        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String str_mail = mail.getText().toString().trim();
                String str_pwd = password.getText().toString().trim();

                //check the fields
                if(TextUtils.isEmpty(str_mail)){
                    mail.setError("Email is required");
                    return;
                }
                if(TextUtils.isEmpty(str_pwd)){
                    password.setError("Password is required");
                    return;
                }
                if (str_pwd.length() < 6){
                    password.setError("Password must have minimum of 6 characters");
                    return;
                }
                pb.setVisibility(View.VISIBLE);

                //Register the user
                auth.createUserWithEmailAndPassword(str_mail,str_pwd).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            pb.setVisibility(View.INVISIBLE);
                            Toast.makeText(Register.this,"User Created",Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(),MainActivity.class));
                        }else {
                            Toast.makeText(Register.this,"Error: "+task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                        }
                    }
                });

            }
        });
    }
}
