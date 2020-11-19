package com.example.rfg2021;

import android.content.Intent;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class FeedDetail extends AppCompatActivity {
    private ImageView feedDetail_BackBtn;
    private ImageView feed_profile, feed_profile2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed_detail);

        feedDetail_BackBtn = findViewById(R.id.feedDetail_BackBtn);
        feed_profile = findViewById(R.id.feed_profile);
        feed_profile2 = findViewById(R.id.feed_profile2);

        feed_profile.setBackground(new ShapeDrawable(new OvalShape()));
        feed_profile.setClipToOutline(true);
        feed_profile2.setBackground(new ShapeDrawable(new OvalShape()));
        feed_profile2.setClipToOutline(true);

        // 회원 프로필 화면으로 이동 (UserProfile)
        feed_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplication(), UserProfile.class);
                startActivity(intent);
            }
        });

        // 뒤로가기 버튼
        feedDetail_BackBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}