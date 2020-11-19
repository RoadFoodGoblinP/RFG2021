package com.example.rfg2021;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

public class MuckpotWrite extends AppCompatActivity {
    private ImageView muckWrite_CloseBtn;
    private Button muckpotWrite_WriteBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_muckpot_write);

        muckWrite_CloseBtn = findViewById(R.id.muckWrite_CloseBtn);
        muckpotWrite_WriteBtn = findViewById(R.id.muckpotWrite_WriteBtn);

        muckWrite_CloseBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}