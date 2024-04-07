package com.example.sensors;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements SensorEventListener {

    private TextView accelerometerData;
    private TextView gyroscopeData;
    private TextView orientationData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        accelerometerData=findViewById(R.id.accelerometer_data);
        gyroscopeData=findViewById(R.id.gyroscope_data);
        orientationData=findViewById(R.id.orientation_data);

        SensorManager sensorManager=(SensorManager) getSystemService(SENSOR_SERVICE);

        if(sensorManager!=null){
            Sensor accleroSensor= sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
            Sensor gyroscopeSensor= sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE);
            Sensor orientSensor= sensorManager.getDefaultSensor(Sensor.TYPE_ORIENTATION);

            if(accleroSensor!=null){
                sensorManager.registerListener(this,accleroSensor,SensorManager.SENSOR_DELAY_NORMAL);
            }
            if(gyroscopeSensor!=null){
                sensorManager.registerListener(this,gyroscopeSensor,SensorManager.SENSOR_DELAY_NORMAL);
            }
            if(orientSensor!=null){
                sensorManager.registerListener(this,orientSensor,SensorManager.SENSOR_DELAY_NORMAL);
            }
        }else {
            Toast.makeText(this,"Sensor service not detected",Toast.LENGTH_SHORT).show();
        }

    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onSensorChanged(SensorEvent event) {
        if(event.sensor.getType()==Sensor.TYPE_ACCELEROMETER){
            accelerometerData.setText("Accelerometer Data: \n\n"+ "X: "+event.values[0]+"\n"+ "Y: "+event.values[1]+"\n"+ "Z: "+event.values[2]+"\n");
        } else if (event.sensor.getType()==Sensor.TYPE_GYROSCOPE) {
            gyroscopeData.setText("Gyroscope Data: \n\n"+ "X: "+event.values[0]+"\n"+ "Y: "+event.values[1]+"\n"+ "Z: "+event.values[2]+"\n");
        } else if (event.sensor.getType()==Sensor.TYPE_ORIENTATION) {
            orientationData.setText("Orientation Data: \n\n"+"Direction: "+event.values[0]+"\n"+ "TiltFrontBack: "+event.values[1]+"\n"+ "TiltLeftRight: "+event.values[2]+"\n");
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}