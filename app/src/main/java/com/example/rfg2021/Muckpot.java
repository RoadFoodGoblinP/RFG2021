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

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class Muckpot extends Fragment {
    private View view;
    private FloatingActionButton muck_WriteBtn;

    ImageView muckImg, muck_Img;
    TextView muckTitle, muckTime, muckTextPreview, muckLocation, muckTotalMember;
    LinearLayout muck_list;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.activity_muckpot, container, false);

        muckImg = view.findViewById(R.id.muck_Img);
        muckTitle = view.findViewById(R.id.muck_Title);
        muckTime = view.findViewById(R.id.muck_Time);
        muckTextPreview = view.findViewById(R.id.muck_TextPreview);
        muckLocation = view.findViewById(R.id.muck_Location);
        muckTotalMember = view.findViewById(R.id.muck_TotalMember);
        muck_list = view.findViewById(R.id.muck_list);
        muck_WriteBtn = view.findViewById(R.id.muck_WriteBtn);
        muck_Img = view.findViewById(R.id.muck_Img);

        // 먹팟 대표사진 둥글게
        muck_Img.setBackground(new ShapeDrawable(new OvalShape()));
        muck_Img.setClipToOutline(true);

        // 하나의 먹팟 화면으로 이동 (Muckpot_Detail)
        muck_list.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), MuckpotDetail.class);
                startActivity(intent);
            }
        });

        //피드 작성 버튼
        muck_WriteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), MuckpotWrite.class);
                startActivity(intent);
            }
        });

        return view;
    }
}