package com.hansung.android.teamproject2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class AddScheduleActivity extends AppCompatActivity implements OnMapReadyCallback {

    GoogleMap mGoogleMap = null;
    private DBHelper mDBHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_schedule);

        //DBHelper
        mDBHelper = new DBHelper(this);
        //

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        final Geocoder geocoder = new Geocoder(this);

        //버튼 객체 생성
        Button findbtn = findViewById(R.id.findbtn);
        Button savebtn = findViewById(R.id.svaebtn);
        Button cancelbtn = findViewById(R.id.cancelbtn);
        Button deletebtn = findViewById(R.id.deletebtn);
        final EditText edit = (EditText)findViewById(R.id.gps_edit);

        //시작, 종료시간 스크롤뷰 객체 생성
        ListView start_hour = findViewById(R.id.start_hour);
        ListView start_minute = findViewById(R.id.start_minute);
        ListView start_AMPM = findViewById(R.id.start_AMPM);

        ListView finish_hour = findViewById(R.id.finish_hour);
        ListView finish_minute = findViewById(R.id.finish_minute);
        ListView finish_AMPM = findViewById(R.id.finish_AMPM);

        String[] hours = {"1","2","3","4","5","6","7","8","9","10","11","12"};
        String[] minutes = {"00","01","02","03","04","05","06","07","08","09","10","11","12","13","14","15","16","17","18","19","20",
                "21","22","23","24","25","26","27","28","29","30","31","32","33","34","35","36","37","38","39","40",
                "41","42","43","44","45","46","47","48","49","50","51","52","53","54","55","56","57","58","59"};
        String[] AMPM = {"AM", "PM"};

        EditText show_date = findViewById(R.id.show_date);
        //MonthCalendarFragment에서 받아온 intent객체
        int array[] = new int[2];
        Intent intent = getIntent();
        array = intent.getIntArrayExtra("key");
        String string = Integer.toString(array[0]) +"."+ Integer.toString(array[1]);
        show_date.setText(string);

        //스크롤뷰에 시간표시
        ArrayAdapter<String> start_hoursAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, hours);
        start_hour.setAdapter(start_hoursAdapter);

        ArrayAdapter<String> start_minutesAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, minutes);
        start_minute.setAdapter(start_minutesAdapter);

        ArrayAdapter<String> start_AMPMAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, AMPM);
        start_AMPM.setAdapter(start_AMPMAdapter);

        ArrayAdapter<String> finish_hoursAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, hours);
        finish_hour.setAdapter(finish_hoursAdapter);

        ArrayAdapter<String> finish_minutesAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, minutes);
        finish_minute.setAdapter(finish_minutesAdapter);

        ArrayAdapter<String> finish_AMPMAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, AMPM);
        finish_AMPM.setAdapter(finish_AMPMAdapter);

        //SQLite(06.12) 저장버튼이 눌리면 insertRecord메소드 실행, db저장 기능 구현하려는 부분
        savebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                insertRecord();
            }
        });
        
        //AddSchedule Activity 취소버튼 구현
        cancelbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });



        //주소 찾기 버튼 클릭 시
        findbtn.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                List<Address> list=null;

                String str = edit.getText().toString();
                //입력한 문자열 저장
                try{
                    list=geocoder.getFromLocationName(str, 2);//최대 2개까지 list에 저장함
                } catch (IOException e) {
                    e.printStackTrace();
                }

                if(list!=null){
                    if(list.size()==0)
                    {
                        Toast.makeText(getApplicationContext(), "주소를 입력해주세요", Toast.LENGTH_SHORT).show();
                    }else{
                        Address addr = list.get(0);
                        double lat = addr.getLatitude();//위도
                        double lon = addr.getLongitude();//경도

                        LatLng location = new LatLng(lat, lon);//위도와 경도 이용해 주소 만들기
                        mGoogleMap.addMarker(new MarkerOptions().position(location).
                                title(str).alpha(0.8f).icon(BitmapDescriptorFactory.
                                fromResource(R.drawable.arrow)));
                        //검색한 주소에 arrow이미지 올려서 마커에 추가
                        mGoogleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(location, 15));
                        //검색한 주소로 카메라 옮기기

                    }
                }
            }
        });


    }

    //savebtn 기능 구현 메소드(06.12)
    private void insertRecord() {
        ListView start_hour = (ListView)findViewById(R.id.start_hour);
        ListView start_minute = (ListView)findViewById(R.id.start_minute);
        ListView start_AMPM = (ListView)findViewById(R.id.start_AMPM);
        ArrayList<Integer> arraylist1 = new ArrayList<Integer>(); //hour,minute을 저장하기위해
        ArrayList<String> arraylist2 = new ArrayList<String>(); //AMPM을 저장하기위해

        start_hour.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
               arraylist1.add(position+1);
            }
        });

        start_minute.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                arraylist1.add(position+1);
            }
        });

        start_AMPM.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(position == 0)
                    arraylist2.add("AM");
                else
                    arraylist2.add("PM");
            }
        });

    }
    //

    private void deleteRecord() {

    }

    private void updateRecord() {

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {

        mGoogleMap = googleMap;
        //초기 위치는 한성대학교로 설정
        LatLng hansung = new LatLng(37.5817891, 127.009854);
        googleMap.addMarker(new MarkerOptions().position(hansung).title("한성대학교"));

        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(hansung, 15));
    }
}