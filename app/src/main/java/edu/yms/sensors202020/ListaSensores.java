package edu.yms.sensors202020;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;

import java.util.List;

public class ListaSensores extends AppCompatActivity {
    TextView textView;
    SensorManager sensorManager;
    List<Sensor> deviceSensors;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_sensores);
        textView = findViewById(R.id.textview);
        sensorManager = (SensorManager)getSystemService(Context.SENSOR_SERVICE);
        deviceSensors = sensorManager.getSensorList(Sensor.TYPE_ALL);

        //textView.setText(deviceSensors.toString());
        imprimirSensores();

    }
    void imprimirSensores(){
        for (Sensor sensor: deviceSensors){
            textView.setText(textView.getText()+"\n "+sensor.getName());
        }
    }
}