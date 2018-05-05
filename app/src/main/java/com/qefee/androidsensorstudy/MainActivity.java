package com.qefee.androidsensorstudy;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.qefee.androidsensorstudy.activity.LightSensorActivity;
import com.qefee.androidsensorstudy.activity.SensorListActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btn_sensor_list = findViewById(R.id.btn_sensor_list);
        btn_sensor_list.setOnClickListener(this::onClickSensorList);

        Button btn_light = findViewById(R.id.btn_light);
        btn_light.setOnClickListener(this::onClickLight);
    }

    private void onClickSensorList(View view) {
        Intent intent = new Intent(this, SensorListActivity.class);
        startActivity(intent);
    }

    private void onClickLight(View view) {
        Intent intent = new Intent(this, LightSensorActivity.class);
        startActivity(intent);
    }
}
