package edu.yms.sensors202020;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

public class MainActivity extends AppCompatActivity {
    TextView txtx,txty,txtz;
    SensorManager sensorManager;
    Sensor sensor;

   // elLienzo miLienzo;

    //sensores generan datos, para recibir los datos generados por los sensores
    SensorEventListener sensorEventListener;
    boolean ejecutar=false;
    int movs=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       // miLienzo = new elLienzo(this);
        //setContentView(miLienzo);

        txtx=findViewById(R.id.txtx);
        txty=findViewById(R.id.txty);
        txtz=findViewById(R.id.txtz);
//        Toolbar myToolbar = (Menu) findViewById(R.id.men_principal);
//        setSupportActionBar(myToolbar);
        sensorManager=(SensorManager)getSystemService(SENSOR_SERVICE);
        sensor=sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        //implicitamente se le dice que traiga los datos desde el puerto de memoria del acelerómetro
        if (sensor==null) {
            Toast.makeText(this, "Acelerómetro no disponible", Toast.LENGTH_SHORT).show();
            finish();}
        sensorEventListener = new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent valorensor) {
                float x=valorensor.values[0];
                float y=valorensor.values[1];
                float z=valorensor.values[2];

                txtx.setText(String.valueOf(x));
                txty.setText(String.valueOf(y));
                txtz.setText(String.valueOf(z));
                if (x < 0) {
                    getWindow().getDecorView().setBackgroundColor(Color.YELLOW);

                }
                if (x>0) { getWindow().getDecorView().setBackgroundColor(Color.RED);
                    movs++;
                    ejecutar=true;
                       }
                if (movs==3 && ejecutar){
                    movs=0;
                    ejecutar=false;
                    playsound();

                    }
                }

            @Override
            public void onAccuracyChanged(Sensor sensor, int i) {

            }
        };
        iniciar();
    }

    private void playsound() {
        MediaPlayer  mediaPlayer= MediaPlayer.create(this, R.raw.grit);
        mediaPlayer.start();

    }

    private void iniciar() {
        sensorManager.registerListener(sensorEventListener, sensor, SensorManager.SENSOR_STATUS_ACCURACY_LOW);
    }
    private void stop(){
        sensorManager.unregisterListener(sensorEventListener);
    }

    @Override
    protected void onPause() {
        stop();
        super.onPause();
    }

    @Override
    protected void onResume() {
        iniciar();
        super.onResume();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case  R.id.men_principal:{
                Intent i = new Intent(this, MainActivity.class);
                startActivity(i);
                break;
            }
            case  R.id.men_listasensores:{
                Intent i = new Intent(this, ListaSensores.class);
                startActivity(i);
                break;
            }
//            case  R.id.men_lienzo:{
//                Intent i = new Intent(this, elLienzo.class);
//                startActivity(i);
//                break;
//            }

            case  R.id.men_nivel:{
                Intent i = new Intent(this, sensorgrafico.class);
                startActivity(i);
                break;
            }
            }
        return true;
        }

}
