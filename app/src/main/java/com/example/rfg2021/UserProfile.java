package com.example.rfg2021;

import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class UserProfile extends AppCompatActivity {
    private ImageView userProfile_ProfilePic, userProfile_BackBtn, feed_profile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);
        userProfile_ProfilePic = findViewById(R.id.userProfile_ProfilePic);
        userProfile_BackBtn = findViewById(R.id.userProfile_BackBtn);
        feed_profile = findViewById(R.id.feed_profile);

        userProfile_ProfilePic.setBackground(new ShapeDrawable(new OvalShape()));
        userProfile_ProfilePic.setClipToOutline(true);
        feed_profile.setBackground(new ShapeDrawable(new OvalShape()));
        feed_profile.setClipToOutline(true);

        userProfile_BackBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}