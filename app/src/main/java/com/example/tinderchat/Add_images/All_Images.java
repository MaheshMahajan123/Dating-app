package com.example.tinderchat.Add_images;

import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.example.tinderchat.about.More_About_Users;
import com.example.tinderchat.R;
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
import com.google.firebase.storage.StorageTask;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;

//https://codinginflow.com/tutorials/android/firebase-storage-upload-and-retrieve-images/part-8-delete-uploads
public class All_Images extends AppCompatActivity {

    private static final int PICK_IMAGE_REQUEST = 1;

    private Uri mImageUri;
    private StorageReference mStorageRef;
    private DatabaseReference mDatabaseRef,acc;
    private StorageTask mUploadTask;
    CardView mButtonChoose;
String currentuserId;
int a;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_allimage);
        currentuserId = FirebaseAuth.getInstance().getCurrentUser().getUid();
        mDatabaseRef = FirebaseDatabase.getInstance().getReference("Users").child(currentuserId).child("All_Images");
        acc = FirebaseDatabase.getInstance().getReference("Users").child(currentuserId).child("All_Images");
        mStorageRef = FirebaseStorage.getInstance().getReference("All_Images").child(currentuserId);


        mDatabaseRef.child("1").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                         acc.child("2").addListenerForSingleValueEvent(new ValueEventListener() {
                             @Override
                             public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                 if (dataSnapshot.exists()) {

                                     acc.child("3").addListenerForSingleValueEvent(new ValueEventListener() {
                                         @Override
                                         public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                             if (dataSnapshot.exists()) {

                                                 acc.child("4").addListenerForSingleValueEvent(new ValueEventListener() {
                                                     @Override
                                                     public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                                         if (dataSnapshot.exists()) {

                                                             acc.child("5").addListenerForSingleValueEvent(new ValueEventListener() {
                                                                 @Override
                                                                 public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                                                     if (dataSnapshot.exists()) {
                                                                         acc.child("6").addListenerForSingleValueEvent(new ValueEventListener() {
                                                                             @Override
                                                                             public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                                                                 if (dataSnapshot.exists()) {

                                                                                     acc.child("7").addListenerForSingleValueEvent(new ValueEventListener() {
                                                                                         @Override
                                                                                         public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                                                                             if (dataSnapshot.exists()) {

                                                                                                 acc.child("8").addListenerForSingleValueEvent(new ValueEventListener() {
                                                                                                     @Override
                                                                                                     public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                                                                                         if (dataSnapshot.exists()) {

                                                                                                             acc.child("9").addListenerForSingleValueEvent(new ValueEventListener() {
                                                                                                                 @Override
                                                                                                                 public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                                                                                                     if (dataSnapshot.exists()) {
       ///////////        ///////////////////                   ////////////////////////////////////////
                                                                                                                         acc.child("10").addListenerForSingleValueEvent(new ValueEventListener() {
                                                                                                                             @Override
                                                                                                                             public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                                                                                                                 if (dataSnapshot.exists()) {



                                                                                                                                 } else {
                                                                                                                                     Toast.makeText(All_Images.this, "You Have Alredy Uploaded 9 pictures Delete any one To upload new one", Toast.LENGTH_LONG).show();
                                                                                                                                         }}
                                                                                                                             @Override public void onCancelled(@NonNull DatabaseError databaseError) { }});

                                                                                                                     } else {
                                                                                                                         mButtonChoose.setOnClickListener(new View.OnClickListener() {
                                                                                                                             @Override
                                                                                                                             public void onClick(View v) {
                                                                                                                                 openFileChooser();
                                                                                                                                 a = 9;
                                                                                                                             }});}}
                                                                                                                 @Override public void onCancelled(@NonNull DatabaseError databaseError) { }});
/////////////////////////////////////////////////////////
                                                                                                         } else {
                                                                                                             mButtonChoose.setOnClickListener(new View.OnClickListener() {
                                                                                                                 @Override
                                                                                                                 public void onClick(View v) {
                                                                                                                     openFileChooser();
                                                                                                                     a = 8;
                                                                                                                 }});}}
                                                                                                     @Override public void onCancelled(@NonNull DatabaseError databaseError) { }});

                                                                                             } else {
                                                                                                 mButtonChoose.setOnClickListener(new View.OnClickListener() {
                                                                                                     @Override
                                                                                                     public void onClick(View v) {
                                                                                                         openFileChooser();
                                                                                                         a = 7;
                                                                                                     }});}}
                                                                                         @Override public void onCancelled(@NonNull DatabaseError databaseError) { }});

                                                                                 } else {
                                                                                     mButtonChoose.setOnClickListener(new View.OnClickListener() {
                                                                                         @Override
                                                                                         public void onClick(View v) {
                                                                                             openFileChooser();
                                                                                             a = 6;
                                                                                         }});}}
                                                                             @Override public void onCancelled(@NonNull DatabaseError databaseError) { }});


                                                                     } else {
                                                                         mButtonChoose.setOnClickListener(new View.OnClickListener() {
                                                                             @Override
                                                                             public void onClick(View v) {
                                                                                 openFileChooser();
                                                                                 a = 5;
                                                                             }});}}
                                                                 @Override public void onCancelled(@NonNull DatabaseError databaseError) { }});

                                                         } else {
                                                             mButtonChoose.setOnClickListener(new View.OnClickListener() {
                                                                 @Override
                                                                 public void onClick(View v) {
                                                                     openFileChooser();
                                                                     a = 4;
                                                                 }});}}
                                                     @Override public void onCancelled(@NonNull DatabaseError databaseError) { }});

                                             } else {
                                                 mButtonChoose.setOnClickListener(new View.OnClickListener() {
                                                     @Override
                                                     public void onClick(View v) {
                                                         openFileChooser();
                                                         a = 3;
                                                     }});}}
                                         @Override public void onCancelled(@NonNull DatabaseError databaseError) { }});

                                 } else {
                                     mButtonChoose.setOnClickListener(new View.OnClickListener() {
                                         @Override
                                         public void onClick(View v) {
                                             openFileChooser();
                                             a = 2;
                                         }});}}
                             @Override public void onCancelled(@NonNull DatabaseError databaseError) { }});
                            }
                        else {
                    mButtonChoose.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            openFileChooser();

//                   Toast.makeText(All_Images.this, "Upload in progress", Toast.LENGTH_SHORT).show();
                        }
                    });
                        a=1; }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });



        mButtonChoose = findViewById(R.id.gallery);
        currentuserId = FirebaseAuth.getInstance().getCurrentUser().getUid();
       // mRecyclerView = findViewById(R.id.recycler_vieww);


    }
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
                Picasso.with(this).load(resultUri);
                mImageUri=resultUri;
                uploadFile();
                if (mUploadTask != null && mUploadTask.isInProgress()) {
                } } } }

    private String getFileExtension(Uri uri) {
        ContentResolver cR = getContentResolver();
        MimeTypeMap mime = MimeTypeMap.getSingleton();
        return mime.getExtensionFromMimeType(cR.getType(uri));
    }

    private void uploadFile() {
        Toast.makeText(All_Images.this, "Please wait Internet May slow", Toast.LENGTH_LONG).show();
        if (mImageUri != null) {
            final StorageReference fileReference = mStorageRef.child(System.currentTimeMillis()
                    + "." + getFileExtension(mImageUri));

            fileReference.putFile(mImageUri)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            Toast.makeText(All_Images.this, "Upload successful", Toast.LENGTH_LONG).show();
                            fileReference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                @Override
                                public void onSuccess(Uri uri) {
                                    //Toast.makeText(All_Images.this, "uploading to db", Toast.LENGTH_SHORT).show();
                                    Image_Upload upload = new Image_Upload( uri.toString());
                                    mDatabaseRef.child(String.valueOf(a)).setValue(upload);
                                    Intent i=new Intent(All_Images.this, More_About_Users.class);
                                    startActivity(i);
                                }
                            });

                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(All_Images.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    })
                    .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                           // double progress = (100.0 * taskSnapshot.getBytesTransferred() / taskSnapshot.getTotalByteCount());
                        }
                    });
        } else {
            Toast.makeText(this, "No file selected", Toast.LENGTH_SHORT).show();
        }
    }



    @Override
    protected void onDestroy() {
        super.onDestroy();
//        mDatabaseRef.removeEventListener(mDBListener);
    }
}

