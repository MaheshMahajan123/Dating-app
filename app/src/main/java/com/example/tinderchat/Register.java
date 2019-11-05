package com.example.tinderchat;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputType;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.ProviderQueryResult;
import com.google.firebase.database.FirebaseDatabase;

public class Register extends AppCompatActivity {
EditText email,password,cpass,phone,User_name;

Button signup;
private TextView eye;
TextView textViewSignup;
RadioButton male,female;
    private String TAG = "adapterTAG";
    String sexx;
FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        firebaseAuth=FirebaseAuth.getInstance();
        email=findViewById(R.id.email);
        password=findViewById(R.id.password);
        cpass=findViewById(R.id.cpass);
        phone=findViewById(R.id.phone);
        signup=findViewById(R.id.signup);
        male=findViewById(R.id.male);
        female=findViewById(R.id.female);
        eye=findViewById(R.id.eye);

        textViewSignup=findViewById(R.id.textViewSignup);
        eye.setVisibility(View.GONE);


        password.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD );
password.addTextChangedListener(new TextWatcher() {
    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        if (password.getText().length() > 0) {
            eye.setVisibility(View.VISIBLE);
        } else {
            eye.setVisibility(View.GONE);
        }
    }

    @Override
    public void afterTextChanged(Editable editable) {

    }
});
        eye.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(eye.getText()=="SHOW")
                {
                    eye.setText("HIDE");
                    password.setInputType(InputType.TYPE_TEXT_VARIATION_PASSWORD);
                    password.setSelection(password.length());
                }
                else
                { eye.setText("SHOW");
                    password.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD );
                    password.setSelection(password.length());
                }
            }
        });
        textViewSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(Register.this, MainActivity.class);
                startActivity(i);
            }
        });

        //////////////////////////////////////////////////////////////////////////////////////////////////////
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    final String eemail,ppassword,cpasss,pphone,name;
                    eemail=email.getText().toString();
                    ppassword=password.getText().toString();
                    cpasss=cpass.getText().toString();
                    pphone=phone.getText().toString();
                if(male.isChecked())
                {
                    sexx="Male";
                }
                else if (female.isChecked())
                {
                    sexx="Female";
                }
                else {
                    Toast.makeText(Register.this, "pleas Fill are you Mail or Female", Toast.LENGTH_SHORT).show();
                }

                    if(TextUtils.isEmpty(eemail))
                    {
                        Toast.makeText(Register.this, "enter email", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    if(TextUtils.isEmpty(ppassword))
                    {
                        Toast.makeText(Register.this, "enter ppassword", Toast.LENGTH_SHORT).show();
                        return;

                    }
                    if(TextUtils.isEmpty(cpasss))
                    {
                        Toast.makeText(Register.this, "enter conform password", Toast.LENGTH_SHORT).show();
                        return;

                    }
                    if(pphone.length()==10)
                    {

                    }
                        else {
                        Toast.makeText(Register.this, "please enter 10 digit phonNo ", Toast.LENGTH_SHORT).show();
                        return;
                    }
firebaseAuth.fetchProvidersForEmail(email.getText().toString()).addOnCompleteListener(new OnCompleteListener<ProviderQueryResult>() {
    @Override
    public void onComplete(@NonNull Task<ProviderQueryResult> task) {
        boolean check =! task.getResult().getProviders().isEmpty();
        if(!check)
        {
            final String abv=sexx;
            firebaseAuth.createUserWithEmailAndPassword(eemail,ppassword).addOnCompleteListener(Register.this,
                    new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful())
                            {
                                // Toast.makeText(Register.this, "sucessfull", Toast.LENGTH_SHORT).show();
                                Upload upload=new Upload(eemail,pphone,ppassword,abv);
                                // Auth user name ki id get krvane ka liya;
                                FirebaseDatabase.getInstance().getReference("Users").child
                                        (FirebaseAuth.getInstance().getCurrentUser().getUid()).setValue(upload).addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        Toast.makeText(Register.this, "Registeration  sucessfull", Toast.LENGTH_SHORT).show();
                                    }
                                });
                                Intent intent=new Intent(Register.this,MainActivity.class);
                                startActivity(intent);
                            }
                            else
                            {
                                Toast.makeText(Register.this, "write correct email  eg:-abc@gmail.com", Toast.LENGTH_SHORT).show();
                            } }});

            //Toast.makeText(Register.this, "", Toast.LENGTH_SHORT).show();
        }
        else
        {
            Toast.makeText(Register.this, "Email is Alredy exist", Toast.LENGTH_SHORT).show();
        }
    }
});


            }}); }
}
