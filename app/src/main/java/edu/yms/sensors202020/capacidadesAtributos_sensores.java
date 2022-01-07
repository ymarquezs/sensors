package edu.yms.sensors202020;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;

import java.util.List;

public class capacidadesAtributos_sensores extends AppCompatActivity {
    TextView textView;
    SensorManager sensorManager;
    Sensor sensor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_capacidades_atributos_sensores);
        textView  = findViewById(R.id.txtcap);
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        sensor = sensorManager.getDefaultSensor(Sensor.TYPE_PRESSURE);
        textView.setText(String.valueOf(sensor.getPower())+ "\n" +
                String.valueOf(sensor.getVersion())+ "\n" +
        String.valueOf(sensor.getMaximumRange()));

    }
}