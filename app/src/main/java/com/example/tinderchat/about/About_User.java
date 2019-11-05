package com.example.tinderchat.about;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.app.DatePickerDialog;
import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.tinderchat.R;
import com.example.tinderchat.Start_tab;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class About_User extends AppCompatActivity {
    TextView mDisplayDate;
    private static final int PICK_IMAGE_REQUEST = 1;
    private ProgressBar mProgressBar;
    private String TAG = "Aboutuser";
    private ImageView mButtonChooseImage;
    private Uri mImageUrl;
    EditText city1, hobies,name ,about;
    private StorageReference mStorageRef;
    private DatabaseReference mDatabaseRef,acc;
    private DatePickerDialog.OnDateSetListener mDateSetListener;
    RadioGroup radioGroup;
    RadioButton radioButton;
    FirebaseAuth mAuth;
    String userId;
    Button submit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about__user);
        mDisplayDate = findViewById(R.id.mDisplayDate);
        city1 = findViewById(R.id.city1);
        hobies = findViewById(R.id.hobies);
        name = findViewById(R.id.textView2);
        mProgressBar = findViewById(R.id.mProgressBar);
        mProgressBar.setVisibility(View.INVISIBLE);
        mDisplayDate = findViewById(R.id.mDisplayDate);
        about = findViewById(R.id.About);
        radioGroup = findViewById(R.id.radioGroup);
        submit = findViewById(R.id.submit);
        mButtonChooseImage = findViewById(R.id.mButtonChoose);

        mAuth = FirebaseAuth.getInstance();
        //userId = mAuth.getCurrentUser().getUid();
        userId = mAuth.getInstance().getCurrentUser().getUid();
        acc = FirebaseDatabase.getInstance().getReference("Users").child(userId);

        Intent iin= getIntent();
        Bundle b = iin.getExtras();

        if(b!=null) {
            String j = (String) b.get("com.example.tinderchat.activity_chat");
            acc.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    if (dataSnapshot.exists()) {
                        Map<String, Object> map = (Map<String, Object>) dataSnapshot.getValue();
                        if (map.get("city") != null) {
                            Intent intent = new Intent(About_User.this, Start_tab.class);
                            startActivity(intent);

                        }
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                }}); }

        //String usersex = getIntent().getExtras().getString("usersex");
        mStorageRef = FirebaseStorage.getInstance().getReference().child("Users").child(userId);
        mDatabaseRef = FirebaseDatabase.getInstance().getReference().child("Users").child(userId);


        //////////////////////////////////////////////////////////////////////////////////////////
        mButtonChooseImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openFileChooser();
            }
        });
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String c=city1.getText().toString();
                String im=mButtonChooseImage.toString();
                String username=name.getText().toString();
                String h=hobies.getText().toString();
                String d=mDisplayDate.getText().toString();
                String da=mButtonChooseImage.toString();
         if(d == "Select Date")
                {
                   Toast.makeText(About_User.this, " Please select your Birth Date", Toast.LENGTH_SHORT).show();
              }
       else {
       if (TextUtils.isEmpty(c)) {
        Toast.makeText(About_User.this, " Please Enter city name", Toast.LENGTH_SHORT).show();
        return;
     }
       else if (h.equals(""))
     {
        Toast.makeText(About_User.this, "Please Add Hobbies", Toast.LENGTH_SHORT).show();
        return;
    } else if (im.isEmpty() && im==null)
     {
        Toast.makeText(About_User.this, "Please Add profile Image", Toast.LENGTH_SHORT).show();
        return;
    }
       else if (username.equals(""))
     {
        Toast.makeText(About_User.this, "Enter Name", Toast.LENGTH_SHORT).show();
        return;
    }
       else if (d.equals("Select Date"))
     {
        Toast.makeText(About_User.this, "Enter Your BirthDate", Toast.LENGTH_SHORT).show();
        return;
    }
       else if (da == null)
       {
        Toast.makeText(About_User.this, "Please Add your Profile Image", Toast.LENGTH_SHORT).show();
        return;
    }
       else {
           saveUserInformation();
        uploadFile();
        Intent intent=new Intent(About_User.this,Start_tab.class);
        startActivity(intent);
         }
       }
       } });
        ///////////////////////////////////////////////////////////////////////////////////////////////////
        //DATA LA KR AANE KA LIYA
        mDatabaseRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    Map<String, Object> map = (Map<String, Object>) dataSnapshot.getValue();
                    if (map.get("city") != null) {
                        String cit = map.get("city").toString();
                        city1.setText(cit);
                    }
                    if (map.get("hobies") != null) {
                        String h = map.get("hobies").toString();
                        hobies.setText(h);
                    }
                    if (map.get("about") != null) {
                        String a = map.get("about").toString();
                        about.setText(a);
                    }
                    if (map.get("date") != null) {
                        String d = map.get("date").toString();
                        mDisplayDate.setText(d);
                    }
                    if (map.get("name") != null) {
                        String n = map.get("name").toString();
                        name.setText(n);

                    }
                    if (map.get("Image") != null) {
                        String image = map.get("Image").toString();
                        Glide.with(getApplicationContext()).load(image).into(mButtonChooseImage);
                    } } }@Override public void onCancelled(@NonNull DatabaseError databaseError) { }});
///////////////////////////////////////////////////////////////////////////////////////////////////
        mDisplayDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog dialog = new DatePickerDialog(
                        About_User.this,
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        mDateSetListener,
                        year, month, day);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });
        mDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month = month + 1;
                String date = month + "/" + day + "/" + year;
                mDisplayDate.setText(date);
            }
        }; }
        /////////////////////////////////////////////////////////////////////////////////
    private void openFileChooser() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, PICK_IMAGE_REQUEST);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK
                && data != null && data.getData() != null) {
            CropImage.activity()
                    .setGuidelines(CropImageView.Guidelines.ON)
                    .setAspectRatio(3,3)
                    .start(this);

            Uri  mImageUrl = data.getData();

        }
        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
            CropImage.ActivityResult result = CropImage.getActivityResult(data);
///https://github.com/ArthurHub/Android-Image-Cropper
            //https://www.youtube.com/watch?v=buwyfcN1pLk
            if (resultCode == RESULT_OK) {
                Uri resultUri = result.getUri();
                Picasso.with(this).load(resultUri).into(mButtonChooseImage);
                mImageUrl=resultUri;
            }
        }
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////
    private String getFileExtension(Uri uri) {
        ContentResolver cR = getContentResolver();
        MimeTypeMap mime = MimeTypeMap.getSingleton();
        return mime.getExtensionFromMimeType(cR.getType(uri));
    }
    private void uploadFile() {
      Log.d(TAG, "Uploadfile");
        mProgressBar.setVisibility(View.VISIBLE);
       // Toast.makeText(this, "jihiuhiuhuihiu", Toast.LENGTH_SHORT).show();
        if (mImageUrl != null) {
            final StorageReference fileReference = mStorageRef.child(System.currentTimeMillis()
                    + ".jpg" + getFileExtension(mImageUrl));
            fileReference.putFile(mImageUrl)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            Handler handler = new Handler();
                            handler.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    mProgressBar.setProgress(0);
                                }
                            }, 500);
                            /////////////////////////////////////////////

                            Log.d(TAG, "saveUserInformation");
                            ////////////////////////////////////
                            fileReference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                @Override
                                public void onSuccess(Uri uri) {
                                      Toast.makeText(About_User.this, "uploading SucessFull", Toast.LENGTH_SHORT).show();
//                                    Intent intent=new Intent(About_User.this,Start_tab.class);
//                                    startActivity(intent);
                                    Map userInfo=new HashMap();
                                    userInfo.put("Image",uri.toString());
                                    mDatabaseRef.updateChildren(userInfo);
                                    mProgressBar.setVisibility(View.GONE);
                                }
                            });

                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(About_User.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    })
                    .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                            double progress = (100.0 * taskSnapshot.getBytesTransferred() / taskSnapshot.getTotalByteCount());
                            mProgressBar.setProgress((int) progress);

                            // Toast.makeText(this, "No file selected", Toast.LENGTH_SHORT).show();
                        }
                    });    }
        else
        {

        }
    }
    public void checkButton(View v) {
        Log.d(TAG, "check Button");
        int radioid = radioGroup.getCheckedRadioButtonId();
        radioButton = findViewById(radioid);
        Toast.makeText(this, "you select" + radioButton, Toast.LENGTH_SHORT).show();
    }
    private void saveUserInformation()
    {
        String cityy=city1.getText().toString();
        String username=name.getText().toString();
        String hobiess=hobies.getText().toString();
        String aboutt=about.getText().toString();
        String mDisplayDatee=mDisplayDate.getText().toString();
        String imag=mButtonChooseImage.toString();
        Log.d(TAG,"city"+cityy);
        int radioid = radioGroup.getCheckedRadioButtonId();
        radioButton = findViewById(radioid);
        radioButton.getText();
        String radioButtonn=radioButton.getText().toString();
        Map userInfoo=new HashMap();
        userInfoo.put("city",cityy);
        userInfoo.put("name",username);
          userInfoo.put("hobies",hobiess);
          userInfoo.put("about",aboutt);
         userInfoo.put("date",mDisplayDatee);
        userInfoo.put("status",radioButtonn);
        Toast.makeText(this, "update sucessfull", Toast.LENGTH_SHORT).show();
                mDatabaseRef.updateChildren(userInfoo);
    }
}


//        if(mDisplayDatee.isEmpty())
//        {
//        userInfoo.put("date","Empty");
//        }  else
//        {  userInfoo.put("date",mDisplayDatee);
//
//        }


