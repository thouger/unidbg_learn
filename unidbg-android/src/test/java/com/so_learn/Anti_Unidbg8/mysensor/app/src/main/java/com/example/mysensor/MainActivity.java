package com.example.mysensor;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.example.mysensor.databinding.ActivityMainBinding;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    // Used to load the 'mysensor' library on application startup.
    static {
        System.loadLibrary("mysensor");
    }

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Example of a call to a native method
        TextView tv = binding.sampleText;
        tv.setText(stringFromJNI());
    }

    /**
     * A native method that is implemented by the 'mysensor' native library,
     * which is packaged with this application.
     */
    public native String stringFromJNI();
    

}