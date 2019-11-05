package com.example.tinderchat;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputType;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tinderchat.about.About_User;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

//https://github.com/SimCoderYoutube/TinderClone/blob/master/app/src/main/java/com/simcoder/tinder/Chat/ChatActivity.java
public class MainActivity extends AppCompatActivity {
    EditText email, password;
    Button login;
    TextView textViewSignup, eye;
    private String TAG = "Mainactivity";

    FirebaseAuth firebaseAuth;
    CheckBox savelogincheckbox;
    SharedPreferences sharedPreferences;
    public static final String mypreference = "Blood";
    SignInButton sign_in_button;
    GoogleSignInClient mGooglesSignInClient;
    private int RC_SIGN_IN = 1;
    ProgressBar progressBar2;
    String currentuserId;
    DatabaseReference acc;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        currentuserId = FirebaseAuth.getInstance().getCurrentUser().getUid();
        acc = FirebaseDatabase.getInstance().getReference("Users").child(currentuserId);

       sharedPreferences = getSharedPreferences("Blood", MODE_PRIVATE);
        String a = (sharedPreferences.getString("my_username", null));
        if (a != null) {
            Intent i = new Intent(MainActivity.this, Animation.class);
            startActivity(i);
        }

        email = findViewById(R.id.email);
        progressBar2 = findViewById(R.id.progressBar2);
        password = findViewById(R.id.password);
        sign_in_button = findViewById(R.id.sign_in_button);
        login = findViewById(R.id.buttonLogin);
        eye = findViewById(R.id.eye);
        textViewSignup = findViewById(R.id.textViewSignup);
        progressBar2.setVisibility(View.INVISIBLE);
        savelogincheckbox = findViewById(R.id.checkBox);
        sharedPreferences = getSharedPreferences(mypreference, MODE_PRIVATE);
        //  Configure Google Sign In
//        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
//                .requestIdToken(getString(R.string.default_web_client_id))
//                .requestEmail()
//                .build();
//        mGooglesSignInClient= GoogleSignIn.getClient(this,gso);
        firebaseAuth = FirebaseAuth.getInstance();
        eye.setVisibility(View.GONE);

        sign_in_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sign_in_button();
            }
        });

        //code for VISIBLE AND INVISIBLE EYE ON PASSWORD
        password.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
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
                if (eye.getText() == "SHOW") {
                    eye.setText("HIDE");
                    password.setInputType(InputType.TYPE_TEXT_VARIATION_PASSWORD);
                    password.setSelection(password.length());
                } else {
                    eye.setText("SHOW");
                    password.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                    password.setSelection(password.length());
                }
            }
        });
////////////////////////////////////////////////////////////////////////////////////////////////////////
      //go to regidter activity
        textViewSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Register.class);
                startActivity(intent);
            }
        });
        //
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Signin();
            }
        });
    }
//google sign code
    private void sign_in_button() {
        Intent signInIntent = mGooglesSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    private void Signin() {
        Log.d(TAG, "sign method:");
        final String eeemail, pppassword;
        eeemail = email.getText().toString();
        pppassword = password.getText().toString();
        if (TextUtils.isEmpty(eeemail)) {
            Toast.makeText(MainActivity.this, "enter email", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(pppassword)) {
            Toast.makeText(MainActivity.this, "enter ppassword", Toast.LENGTH_SHORT).show();
            return;
        }

        if (savelogincheckbox.isChecked()) {
            firebaseAuth.signInWithEmailAndPassword(eeemail, pppassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        Toast.makeText(MainActivity.this, "sucessfull", Toast.LENGTH_SHORT).show();
                        sharedPreferences = getSharedPreferences("Blood", MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putString("my_username", eeemail);
                        editor.commit();
                        Intent intent = new Intent(MainActivity.this, About_User.class);
                        String cd="abc";
                        intent.putExtra("com.example.tinderchat.activity_chat",cd);
                        startActivity(intent);
                    } else {
                        Toast.makeText(MainActivity.this, "wrong username or password", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        } else {
            Toast.makeText(MainActivity.this, "please Check your check box", Toast.LENGTH_SHORT).show();
        }
    }
}
