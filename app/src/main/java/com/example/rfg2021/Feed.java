package com.example.rfg2021;

import android.content.Intent;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class Feed extends Fragment {
    private View view;
    private ImageView feed_profile, feed_profileW;
    private TextView feed_nicknameW, refresh;
    private FloatingActionButton feed_WriteBtn;
    private LinearLayout feed_AllFeed, feed_AllFeed2;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.activity_feed, container, false);

        feed_profile = view.findViewById(R.id.feed_profile);
        feed_profileW = view.findViewById(R.id.feed_profileW);
        feed_nicknameW = view.findViewById(R.id.feed_nicknameW);
        feed_AllFeed2 = view.findViewById(R.id.feed_AllFeed2);
        refresh = view.findViewById(R.id.refresh);

        feed_profile.setBackground(new ShapeDrawable(new OvalShape()));
        feed_profile.setClipToOutline(true);
        feed_profileW.setBackground(new ShapeDrawable(new OvalShape()));
        feed_profileW.setClipToOutline(true);

        /*Intent intent = getActivity().getIntent();
        String nickname = intent.getExtras().getString("nickname");
        String profileImgUrl = intent.getExtras().getString("profileImgUrl");

        intent.putExtra("nickname", nickname);
        intent.putExtra("profileImgUrl", profileImgUrl);

        Glide.with(this).load(profileImgUrl).into(feed_profileW);
        feed_nicknameW.setText(nickname);*/

        refresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                feed_AllFeed2.setVisibility(View.VISIBLE);
            }
        });

/*        // 회원 프로필 화면으로 이동 (UserProfile)
        feed_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), UserProfile.class);
                startActivity(intent);
            }
        });*/

        feed_AllFeed = view.findViewById(R.id.feed_AllFeed);
        feed_WriteBtn = view.findViewById(R.id.feed_WriteBtn);

        // 하나의 피드 화면으로 이동 (FeedDetail)
        feed_AllFeed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), FeedDetail.class);
                startActivity(intent);
            }
        });

        //피드 작성 버튼
        feed_WriteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), FeedWrite.class);
                startActivity(intent);
            }
        });

        return view;
    }


}