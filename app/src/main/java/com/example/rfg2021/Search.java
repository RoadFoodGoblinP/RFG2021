package com.example.rfg2021;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class Search extends Fragment {
    private View view;
    private FloatingActionButton search_WriteBtn;

    LinearLayout search_AllMuckpot, search_AllFeed;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.activity_search, container, false);

        search_WriteBtn = view.findViewById(R.id.search_WriteBtn);
        search_AllMuckpot = view.findViewById(R.id.search_AllMuckpot);
        search_AllFeed = view.findViewById(R.id.search_AllFeed);

        // 하나의 피드 화면으로 이동 (FeedDetail)
        search_AllFeed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), FeedDetail.class);
                startActivity(intent);
            }
        });

        // 하나의 먹팟 화면으로 이동 (Muckpot_Detail)
        search_AllMuckpot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), MuckpotDetail.class);
                startActivity(intent);
            }
        });

        //피드 작성 버튼
        search_WriteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), FeedWrite.class);
                startActivity(intent);
            }
        });

        return view;
    }
}