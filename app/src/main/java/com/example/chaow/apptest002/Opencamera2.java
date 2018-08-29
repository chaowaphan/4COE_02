package com.example.chaow.apptest002;

import android.content.pm.PackageManager;
import android.security.keystore.KeyProtection;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Surface;
import android.view.SurfaceView;
import android.view.WindowManager;
import android.widget.Toast;
import android.Manifest;

import org.opencv.android.BaseLoaderCallback;
import org.opencv.android.CameraBridgeViewBase;
import org.opencv.android.LoaderCallbackInterface;
import org.opencv.android.OpenCVLoader;
import org.opencv.core.Core;
import org.opencv.core.Mat;

public class Opencamera2 extends AppCompatActivity implements CameraBridgeViewBase.CvCameraViewListener2 {

    private static final String TAG = "OpenCVCamara";
    private CameraBridgeViewBase cameraBridgeViewBase;


    Mat image;
    Mat grayImage;

    static {
        System.loadLibrary("native-lib");
    }


    private BaseLoaderCallback baseLoaderCallback = new BaseLoaderCallback(this) {
        @Override
        public void onManagerConnected(int status) {
            switch (status) {
                case LoaderCallbackInterface.SUCCESS:
                    cameraBridgeViewBase.enableView();
                    break;
                default:
                    super.onManagerConnected(status);
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_open_cv_camera2);

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA}, 0);
        }

        cameraBridgeViewBase = (CameraBridgeViewBase) findViewById(R.id.camara_view);
        cameraBridgeViewBase.setVisibility(SurfaceView.VISIBLE);
        cameraBridgeViewBase.setCvCameraViewListener(this);

    }


    @Override
    public void onResume() {
        super.onResume();

        if (!OpenCVLoader.initDebug()) {
            Log.d(TAG, "Opencv NOT LOAD");
            OpenCVLoader.initAsync(OpenCVLoader.OPENCV_VERSION_3_4_0, this, baseLoaderCallback);
        } else {
            Log.d(TAG, "Opencv NOT LOAD");
            baseLoaderCallback.onManagerConnected(LoaderCallbackInterface.SUCCESS);
        }


    }

    @Override
    public void onCameraViewStarted(int width, int height) {

    }

    @Override
    public void onCameraViewStopped() {

    }

    public void onDestroy() {
        super.onDestroy();
        disableCamera();
    }

    public void disableCamera() {
        if (cameraBridgeViewBase != null)
            cameraBridgeViewBase.disableView();
    }

    @Override
    public void onPause() {
        super.onPause();
        disableCamera();
    }

    // --------------------------------------------------------------------------------------- //
    private Mat mRgba;
    private Mat mGray;
    // --------------------------------------------------------------------------------------- //

    // --------------------------------------------------------------------------------------- //

    //public native void ImgProcess(long inputFrame, long grayImage);

    //public

    // --------------------------------------------------------------------------------------- //


    // --------------------------------------------------------------------------------------- //
    @Override
    public Mat onCameraFrame(CameraBridgeViewBase.CvCameraViewFrame inputFrame) {
        image = inputFrame.rgba();

        Mat mRgbaT = image.t();

        Core.flip(image, mRgbaT, 1);
        Core.flip(mRgbaT, mRgbaT, 2);


        //ImgProcess(mRgbaT.getNativeObjAddr(),grayImage.getNativeObjAddr());


        return image;

    }

    // --------------------------------------------------------------------------------------- //




}
