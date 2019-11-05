package com.example.tinderchat;


        import android.content.Intent;
        import android.graphics.Color;
        import android.os.Bundle;

        import androidx.annotation.NonNull;
        import androidx.cardview.widget.CardView;
        import androidx.fragment.app.Fragment;

        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.ImageView;
        import android.widget.TextView;

        import com.bumptech.glide.Glide;
        import com.example.tinderchat.Add_images.All_Images;
        import com.example.tinderchat.about.More_About_Users;
        import com.google.firebase.auth.FirebaseAuth;
        import com.google.firebase.database.DataSnapshot;
        import com.google.firebase.database.DatabaseError;
        import com.google.firebase.database.DatabaseReference;
        import com.google.firebase.database.FirebaseDatabase;
        import com.google.firebase.database.ValueEventListener;
        import com.smarteist.autoimageslider.IndicatorAnimations;
        import com.smarteist.autoimageslider.IndicatorView.draw.controller.DrawController;
        import com.smarteist.autoimageslider.SliderAnimations;
        import com.smarteist.autoimageslider.SliderView;

        import java.util.Map;


/**
 * A simple {@link Fragment} subclass.
 */
public class Profile extends Fragment {
    private String currentuserId;
    CardView logout;
    ImageView profileimage,edit,setting,camera;
    TextView pname;
    SliderView imageSlider;
    private String TAG = "profile";

    public Profile() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        profileimage = view.findViewById(R.id.profileimage);
        logout = view.findViewById(R.id.card242);
        camera = view.findViewById(R.id.camera);
        pname = view.findViewById(R.id.pname);
        setting = view.findViewById(R.id.setting);
        edit = view.findViewById(R.id.edit);
        currentuserId = FirebaseAuth.getInstance().getCurrentUser().getUid();
        DatabaseReference mDatabaseRef = FirebaseDatabase.getInstance().getReference().child("Users").child(currentuserId);
//        sharedPreferences =getActivity().getSharedPreferences("Blood",MODE_PRIVATE);
//        String a=(sharedPreferences.getString("my_username",null));
//        if(a==null)
//        {
//            Intent i=new Intent(getContext(),MainActivity.class);
//            startActivity(i);
//        }

        SliderView sliderView = view.findViewById(R.id.imageSlider);

        final SliderAdapterExample adapter = new SliderAdapterExample(getContext());
        adapter.setCount(6);

        sliderView.setSliderAdapter(adapter);

        sliderView.setIndicatorAnimation(IndicatorAnimations.SLIDE); //set indicator animation by using SliderLayout.IndicatorAnimations. :WORM or THIN_WORM or COLOR or DROP or FILL or NONE or SCALE or SCALE_DOWN or SLIDE and SWAP!!
        sliderView.setSliderTransformAnimation(SliderAnimations.CUBEINROTATIONTRANSFORMATION);
        sliderView.setAutoCycleDirection(SliderView.AUTO_CYCLE_DIRECTION_BACK_AND_FORTH);
        sliderView.setIndicatorSelectedColor(Color.WHITE);
        sliderView.setIndicatorUnselectedColor(Color.GRAY);
        sliderView.startAutoCycle();

        sliderView.setOnIndicatorClickListener(new DrawController.ClickListener() {
            @Override
            public void onIndicatorClicked(int position) {

            }
        });

        /**************************************************************************/
        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i =new Intent(getContext(), More_About_Users.class);
                startActivity(i);
            }
        });

        camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i =new Intent(getContext(), All_Images.class);
                startActivity(i);
            }
        });
        setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i =new Intent(getContext(), com.example.tinderchat.about.logout.class);
                startActivity(i);
            }
        });

        mDatabaseRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    Map<String, Object> map = (Map<String, Object>) dataSnapshot.getValue();
                    if (map.get("Image") != null) {
                        String image = map.get("Image").toString();
                        Glide.with(getActivity()).load(image).into(profileimage);
                        // image.(R.drawable.ic_red_eye24dp)
                    }
                    if (map.get("name") != null) {
                        String cit = map.get("name").toString();
                        pname.setText(cit);
                    }
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


        return view;
    }
}


