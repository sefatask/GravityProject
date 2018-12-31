package com.example.sefa.finalcalisma;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements SensorEventListener{

    TextView tvacilar,tvkutu,tvdegerler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SensorManager sensorManager = (SensorManager)getSystemService(SENSOR_SERVICE);
        Sensor s = sensorManager.getDefaultSensor(Sensor.TYPE_GRAVITY);
        sensorManager.registerListener(this,s,100);

        tvacilar = (TextView)findViewById(R.id.tvAcilar);
        tvdegerler = (TextView)findViewById(R.id.tvDegerler);
        tvkutu = (TextView)findViewById(R.id.tvkutu);

    }

    float eskiX = 0;
    float eskiY = 0;
    float eskiZ = 0;
    float hassasiyet = 0;

    @Override
    public void onSensorChanged(SensorEvent event) {
        float x = event.values[0];
        float y = event.values[1];
        float z = event.values[2];

        if(Math.abs(x-eskiX)<hassasiyet && Math.abs(y-eskiY)<hassasiyet && Math.abs(z-eskiZ)<hassasiyet){
            return;
        }

        eskiX = x; eskiY = y; eskiZ = z;

        tvdegerler.setText("x : " + x + "y : " + y + "z : " + z);
        float aci1 = (float)(180.0f*Math.atan(x/y)/Math.PI);
        float aci2 = (float)(180.0f*Math.atan(y/z)/Math.PI);
        float aci3 = (float)(180.0f*Math.atan(z/x)/Math.PI);

        tvkutu.setRotation(aci1);
        tvkutu.setRotation(aci2);
        tvkutu.setRotation(aci3);

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}
