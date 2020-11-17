package com.example.rfg2021;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class LikeAll extends AppCompatActivity {
    private ImageView setting_BackBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_like_all);

        setting_BackBtn = findViewById(R.id.setting_BackBtn);

        // 뒤로가기 버튼
        setting_BackBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}