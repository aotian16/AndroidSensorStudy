package com.qefee.androidsensorstudy.activity;

import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import com.qefee.androidsensorstudy.R;
import com.qefee.androidsensorstudy.adapter.SensorAdapter;

import java.util.LinkedList;
import java.util.List;

public class SensorListActivity extends AppCompatActivity {

    /**
     * log tag for SensorListActivity
     */
    private static final String TAG = "SensorListAct";

    private SensorManager sensorManager;
    private List<Sensor> sensors;
    private SensorAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sensor_list);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(this::onClickFab);

        RecyclerView rv_list = findViewById(R.id.rv_list);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        LinearLayoutManager agentLayoutManager = new LinearLayoutManager(this, OrientationHelper.VERTICAL, false);
        rv_list.setLayoutManager(agentLayoutManager);

        sensors = new LinkedList<>();
        adapter = new SensorAdapter(sensors, (v, position) -> Log.i(TAG, String.format("on click item: position = %d", position)));
        rv_list.setAdapter(adapter);

        rv_list.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));

        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
    }

    private void onClickFab(View view) {
        List<Sensor> sensorList = sensorManager.getSensorList(Sensor.TYPE_ALL);
        sensors.clear();
        sensors.addAll(sensorList);
        adapter.notifyDataSetChanged();
    }

}
