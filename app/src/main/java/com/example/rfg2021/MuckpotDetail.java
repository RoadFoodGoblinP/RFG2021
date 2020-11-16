package com.example.rfg2021;

import android.content.Intent;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class MuckpotDetail extends AppCompatActivity {
    private ImageView muckDetail_BackBtn, muck_profile, muck_Commentprofilepic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_muckpot_detail);

        muckDetail_BackBtn = findViewById(R.id.muckDetail_BackBtn);
        muck_profile = findViewById(R.id.muck_profile);
        muck_Commentprofilepic = findViewById(R.id.muck_Commentprofilepic);

        // 회원 프로필 둥글게
        muck_profile.setBackground(new ShapeDrawable(new OvalShape()));
        muck_profile.setClipToOutline(true);
        muck_Commentprofilepic.setBackground(new ShapeDrawable(new OvalShape()));
        muck_Commentprofilepic.setClipToOutline(true);

        // 먹팟 작성자 회원 프로필 페이지로 이동 (UserProfile)
        muck_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplication(), UserProfile.class);
                startActivity(intent);
            }
        });

        // 먹팟 회원 프로필 페이지로 이동 (UserProfile)
        muck_Commentprofilepic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplication(), UserProfile.class);
                startActivity(intent);
            }
        });

        // 뒤로가기
        muckDetail_BackBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}