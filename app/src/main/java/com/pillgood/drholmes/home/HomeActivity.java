package com.pillgood.drholmes.home;

import android.content.Intent;
import android.media.MediaParser;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;

import com.google.firebase.auth.FirebaseAuth;
import com.pillgood.drholmes.MainActivity;
import com.pillgood.drholmes.R;
import com.pillgood.drholmes.start.LoginActivity;

public class HomeActivity extends Fragment {

    private Button btn_logout;
//    private ImageButton btn_pilladd;
    private FirebaseAuth mFirebaseAuth;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mFirebaseAuth = FirebaseAuth.getInstance();

        // Inflate the layout for this fragment\
        View view = inflater.inflate(R.layout.activity_home, container, false);
//        btn_logout = container.findViewById(R.id.btn_logout);
        btn_logout = view.findViewById(R.id.btn_logout);
        btn_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mFirebaseAuth.signOut();
                Intent intent = new Intent(getActivity(), LoginActivity.class);
                startActivity(intent);
            }
        });


//        btn_pilladd = view.findViewById(R.id.pill_add);
//        btn_pilladd.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(getActivity(), PillAddActivity.class);
//                startActivity(intent);
//            }
//        });
        return view;
    }
}