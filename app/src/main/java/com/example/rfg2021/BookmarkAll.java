package com.example.rfg2021;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class BookmarkAll extends AppCompatActivity {
    private ImageView bookmark_BackBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bookmark_all);

        bookmark_BackBtn = findViewById(R.id.bookmark_BackBtn);

        // 뒤로가기 버튼
        bookmark_BackBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}