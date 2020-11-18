package com.example.rfg2021;

import android.content.Intent;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;

public class UserJoin extends AppCompatActivity {

    EditText userJoin_nickname, userJoin_info;
    ImageView userJoin_back, userJoin_profileImg;
    Button userJoin_success;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_join);

        userJoin_info = findViewById(R.id.userJoin_info);
        userJoin_nickname = findViewById(R.id.userJoin_nickname);
        userJoin_back = findViewById(R.id.userJoin_back);
        userJoin_profileImg = findViewById(R.id.userJoin_profileImg);
        userJoin_success = findViewById(R.id.userJoin_success);

        //이미지 테두리 둥글게
        userJoin_profileImg.setBackground(new ShapeDrawable(new OvalShape()));
        userJoin_profileImg.setClipToOutline(true);

        Intent intent = getIntent();
        int MemberNo = (int) intent.getExtras().getLong("id");
        // String Password = MemberNo + "0";
        String Nickname = intent.getExtras().getString("nickname");
        // String Name = intent.getExtras().getString("nickname");
        String OneInfo = userJoin_info.getText().toString();
        String ProfileImg = intent.getExtras().getString("profileImgUrl");

        userJoin_nickname.setText(Nickname);
        Glide.with(this).load(ProfileImg).into(userJoin_profileImg);

        userJoin_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        userJoin_success.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getApplication(), MainActivity.class);
                intent.putExtra("nickname", Nickname);
                intent.putExtra("profileImgUrl", ProfileImg);
                intent.putExtra("oneInfo", OneInfo);
                startActivity(intent);
            }
        });
    }

}