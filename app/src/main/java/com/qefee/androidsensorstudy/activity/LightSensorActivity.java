package com.qefee.androidsensorstudy.activity;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.qefee.androidsensorstudy.R;

public class LightSensorActivity extends AppCompatActivity {
    /**
     * log tag for LightSensorActivity
     */
    private static final String TAG = "LightSensorAct";

    private SensorManager sensorManager;
    private Sensor lightSensor;
    private TextView tv_info;
    private ProgressBar pb_light;
    private SensorEventListener sensorEventListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_light_sensor);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(this::onClickFab);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        tv_info = findViewById(R.id.tv_info);
        pb_light = findViewById(R.id.pb_light);

        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        if (sensorManager != null) {
            lightSensor = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);
        }
        sensorEventListener = new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent event) {
                // 当传感器的值变化回调
                float[] values = event.values;
                int progress = (int) values[0];
                if (pb_light.getMax() < progress) {
                    pb_light.setMax(progress);
                }
                pb_light.setProgress(progress);
                Log.i(TAG, String.format("onSensorChanged: values[0] = %f, timestamp = ", values[0]));
            }

            @Override
            public void onAccuracyChanged(Sensor sensor, int accuracy) {
                // 当传感器的精度变化回调
                Log.i(TAG, String.format("onAccuracyChanged: accuracy = %d", accuracy));
            }
        };
    }

    private boolean isSensorRegistered;

    private void onClickFab(View view) {
        if (isSensorRegistered) {
            sensorManager.unregisterListener(sensorEventListener);
        } else {
            tv_info.setText(lightSensor.toString());
            sensorManager.registerListener(sensorEventListener, lightSensor, SensorManager.SENSOR_DELAY_GAME);
        }
        isSensorRegistered = !isSensorRegistered;
    }

}
