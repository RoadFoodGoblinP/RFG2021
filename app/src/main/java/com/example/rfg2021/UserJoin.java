package com.example.rfg2021;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


/*import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;*/
import com.bumptech.glide.Glide;
import com.kakao.sdk.user.model.User;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

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
        int MemberNo = (int)intent.getExtras().getLong("id");
        String Password = MemberNo + "0";
        String Nickname = intent.getExtras().getString("nickname");
        String Name = intent.getExtras().getString("nickname");
        String OneInfo = userJoin_info.getText().toString();
        String ProfileImg = intent.getExtras().getString("profileImgUrl");


        userJoin_nickname.setText(Nickname);
        Glide.with(this).load(ProfileImg).into(userJoin_profileImg);

        userJoin_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { finish(); }});

        userJoin_success.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getApplication(), MainActivity.class);
                intent.putExtra("nickname", Nickname);
                intent.putExtra("profileImgUrl", ProfileImg);
                startActivity(intent);
            }
        });
    }

}