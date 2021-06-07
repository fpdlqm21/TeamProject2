package com.hansung.android.teamproject2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.ScrollView;

public class AddScheduleActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_schedule);

        //MonthCalendarFragment에서 받아온 intent객체
//        int array[] = {getIntent().getIntExtra("key", -1)};
//        String string = Integer.toString(array[0]) + Integer.toString(array[1]);

        //버튼 객체 생성
        Button findbtn = findViewById(R.id.findbtn);
        Button savebtn = findViewById(R.id.svaebtn);
        Button cancelbtn = findViewById(R.id.cancelbtn);
        Button deletebtn = findViewById(R.id.deletebtn);

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
        //MonthViewFragment에서 받은 날짜를 show_date가 표시하도록 해야함
        //show_date.setText(string);

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
        //이벤트 처리
        cancelbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }
}