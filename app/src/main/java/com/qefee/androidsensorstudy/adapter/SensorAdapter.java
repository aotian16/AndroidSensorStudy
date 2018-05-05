package com.qefee.androidsensorstudy.adapter;

import android.hardware.Sensor;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * SensorAdapter.
 * <ul>
 * <li>date: 2018/5/5</li>
 * </ul>
 *
 * @author tongjin
 */
public class SensorAdapter extends RecyclerView.Adapter<SensorAdapter.ViewHolder> {

    private List<Sensor> sensors;
    private ActionCallback callback;

    public SensorAdapter(@NonNull List<Sensor> sensors, @NonNull ActionCallback callback) {
        this.sensors = sensors;
        this.callback = callback;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(android.R.layout.simple_list_item_1, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        int ap = holder.getAdapterPosition();
        Sensor s = sensors.get(ap);
        String type = getTypeString(s);
        holder.text.setText(String.format("【%s】%s", type, s.toString()));
        holder.text.setOnClickListener(v -> callback.onClickItem(holder.itemView, ap));
    }

    @NonNull
    private String getTypeString(Sensor s) {
        String type;
        switch (s.getType()) {
            case Sensor.TYPE_ACCELEROMETER:
                type = "加速度传感器";
                break;
            case Sensor.TYPE_MAGNETIC_FIELD:
                type = "磁场传感器";
                break;
            case Sensor.TYPE_ORIENTATION:
                type = "方向传感器";
                break;
            case Sensor.TYPE_GYROSCOPE:
                type = "陀螺仪传感器";
                break;
            case Sensor.TYPE_LIGHT:
                type = "光线传感器";
                break;
            case Sensor.TYPE_PRESSURE:
                type = "压力传感器";
                break;
            case Sensor.TYPE_TEMPERATURE:
                type = "温度传感器";
                break;
            case Sensor.TYPE_PROXIMITY:
                type = "接近传感器";
                break;
            case Sensor.TYPE_GRAVITY:
                type = "重力传感器";
                break;
            case Sensor.TYPE_LINEAR_ACCELERATION:
                type = "线性加速度传感器";
                break;
            case Sensor.TYPE_ROTATION_VECTOR:
                type = "旋转矢量传感器";
                break;
            case Sensor.TYPE_RELATIVE_HUMIDITY:
                type = "相对湿度传感器";
                break;
            case Sensor.TYPE_AMBIENT_TEMPERATURE:
                type = "环境温度传感器";
                break;
            case Sensor.TYPE_MAGNETIC_FIELD_UNCALIBRATED:
                type = "磁场传感器(未经校准)";
                break;
            case Sensor.TYPE_GAME_ROTATION_VECTOR:
                type = "游戏旋转矢量传感器";
                break;
            case Sensor.TYPE_GYROSCOPE_UNCALIBRATED:
                type = "陀螺仪传感器(未经校准)";
                break;
            case Sensor.TYPE_SIGNIFICANT_MOTION:
                type = "特殊动作触发传感器";
                break;
            case Sensor.TYPE_STEP_DETECTOR:
                type = "步数探测传感器";
                break;
            case Sensor.TYPE_STEP_COUNTER:
                type = "步数计数传感器";
                break;
            case Sensor.TYPE_GEOMAGNETIC_ROTATION_VECTOR:
                type = "地磁旋转矢量传感器";
                break;
            case Sensor.TYPE_HEART_RATE:
                type = "心率传感器";
                break;
            case Sensor.TYPE_POSE_6DOF:
                type = "POSE_6DOF传感器";
                break;
            case Sensor.TYPE_STATIONARY_DETECT:
                type = "静止检测传感器";
                break;
            case Sensor.TYPE_MOTION_DETECT:
                type = "运动检测传感器";
                break;
            case Sensor.TYPE_HEART_BEAT:
                type = "心跳传感器";
                break;
            case Sensor.TYPE_LOW_LATENCY_OFFBODY_DETECT:
                type = "低延迟身体检测传感器";
                break;
            case Sensor.TYPE_ACCELEROMETER_UNCALIBRATED:
                type = "加速度传感器(未经校准)";
                break;
            default:
                type = "其它传感器";
                break;
        }
        return type;
    }

    @Override
    public int getItemCount() {
        return sensors.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        TextView text;

        ViewHolder(View itemView) {
            super(itemView);

            text = itemView.findViewById(android.R.id.text1);
        }
    }

    public interface ActionCallback {
        void onClickItem(View v, int position);
    }
}
