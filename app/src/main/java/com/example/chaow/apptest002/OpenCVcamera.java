package com.example.chaow.apptest002;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class OpenCVcamera extends AppCompatActivity {


    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_opencv_camera);

    button = (Button) findViewById(R.id.bt_opencv);
    button.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            OpenActivity2();
        }
    });

    }


    public void OpenActivity2() {
        Intent OpenActivity2camara = new Intent(getApplicationContext(), Opencamera2.class);
        startActivity(OpenActivity2camara);
    }

}
