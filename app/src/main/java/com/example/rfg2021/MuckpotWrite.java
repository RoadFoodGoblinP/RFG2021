package com.example.rfg2021;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class MuckpotWrite extends AppCompatActivity {
    private ImageView muckWrite_CloseBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_muckpot_write);

        muckWrite_CloseBtn = findViewById(R.id.muckWrite_CloseBtn);

        muckWrite_CloseBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}