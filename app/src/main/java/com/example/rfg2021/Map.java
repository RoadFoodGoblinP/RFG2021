package com.example.rfg2021;

import android.Manifest;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.naver.maps.map.LocationTrackingMode;
import com.naver.maps.map.MapFragment;
import com.naver.maps.map.NaverMap;
import com.naver.maps.map.NaverMapOptions;
import com.naver.maps.map.OnMapReadyCallback;
import com.naver.maps.map.UiSettings;
import com.naver.maps.map.util.FusedLocationSource;

public class Map extends Fragment implements OnMapReadyCallback {
    private static final String TAG = "activity_map";
    private View view;
    private ImageView map_profileImg;
    private FusedLocationSource mLocationSource;
    private NaverMap mNaverMap;
    private TextView toolbarText;
    private static final int PERMISSION_REQUEST_CODE = 1000;
    private static final String[] PERMISSIONS = {Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION};

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.activity_map, container, false);
        //map_profileImg = view.findViewById(R.id.map_profileImg);

        /*map_profileImg.setBackground(new ShapeDrawable(new OvalShape()));
        map_profileImg.setClipToOutline(true);*/

        // 지도 객체 생성
        FragmentManager fm = getActivity().getSupportFragmentManager();
        MapFragment mapFragment = (MapFragment) fm.findFragmentById(R.id.map);
        if (mapFragment == null) {
            mapFragment = MapFragment.newInstance();
            fm.beginTransaction().add(R.id.map, mapFragment).commit();
        }

        // getMapAsync를 호출하여 비동기로 onMapReady 콜백 메서드 호출
        // onMapReady에서 NaverMap 객체를 받음
        mapFragment.getMapAsync(this);

        // 위치를 반환하는 구현체인 FusedLocationSource 생성
        mLocationSource = new FusedLocationSource(this, PERMISSION_REQUEST_CODE);

        return view;
    }

    @Override
    public void onMapReady(@NonNull NaverMap naverMap) {
        Log.d(TAG, "onMapReady");

        // NaverMap 객체 받아서 NaverMap 객체에 위치 소스 지정
        mNaverMap = naverMap;
        mNaverMap.setLocationSource(mLocationSource);

        // 네이버 맵에서 제공하는 UI 요소들 사용
        UiSettings uiSettings = mNaverMap.getUiSettings();
        uiSettings.setLocationButtonEnabled(true);

        // 네이버 맵에서 제공하는 UI 요소들 사용
        NaverMapOptions options = new NaverMapOptions();
        options.locationButtonEnabled(true);

        // 현재 위치로 화면이 따라오게 만듦
        naverMap.setLocationTrackingMode(LocationTrackingMode.Follow);

        /*// 툴바 텍스트뷰에 현재 주소 찍기
        toolbarText = view.findViewById(R.id.toolbar);
//        toolbarText.setText(mNaverMap.getLocationSource().toString());        // 여기까지 툴바 텍스트뷰에 주소 찍기
        toolbarText.setText("서울특별시 마포구 연남동");*/

        // 권한확인. 결과는 onRequestPermissionsResult 콜백 매서드 호출
        ActivityCompat.requestPermissions(this.getActivity(), PERMISSIONS, PERMISSION_REQUEST_CODE);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (mLocationSource.onRequestPermissionsResult(requestCode, permissions, grantResults)) {
            return;
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }
}