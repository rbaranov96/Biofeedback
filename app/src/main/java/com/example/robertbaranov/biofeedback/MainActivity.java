package com.example.robertbaranov.biofeedback;

import android.hardware.Camera;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.PowerManager;
import android.os.PowerManager.WakeLock;
import android.app.Activity;
import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Color;
import android.graphics.Paint.Align;
import android.hardware.Camera;
import android.hardware.Camera.PreviewCallback;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.ViewGroup.LayoutParams;
import android.widget.LinearLayout;
import android.widget.Toast;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private static Camera camera = null;
    private static SurfaceHolder previewHolder = null;
    private static WakeLock wakeLock = null;
    private static long startTime = 0;
    private static SurfaceView preview = null;
    private Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }
    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }

    @Override
    public void onResume() {
        super.onResume();
        wakeLock.acquire();
        camera = Camera.open();
        startTime = System.currentTimeMillis();
    }

    @Override
    public void onPause() {
        super.onPause();
        wakeLock.release();
        camera.setPreviewCallback(null);
        camera.stopPreview();
        camera.release();
        camera = null;
    }
    private static PreviewCallback previewCallback = new PreviewCallback() {

        public void onPreviewFrame(byte[] data, Camera cam) {
            if (data == null)
                throw new NullPointerException();
            Camera.Size size = cam.getParameters().getPreviewSize();
            if (size == null)
                throw new NullPointerException();
//            if (!processing.compareAndSet(false, true))
//                return;
            int width = size.width;
            int height = size.height;
            // Process image
            //int imgAvg = ImageProcessing.decodeYUV420SPtoRedAvg(data.clone(),height,width);
            //gx=imgAvg;
            //text1.setText("The average pixel value is : "+String.valueOf(imgAvg));
            //imgAvg of Pixel average value, Log
            //			Log.i(TAG, "imgAvg=" + imgAvg);
            //if (imgAvg == 0 || imgAvg == 255) {
            //    processing.set(false);
            //    return;
            //}

            //int averageArrayAvg = 0;
            //int averageArrayCnt = 0;
//            for (int i = 0; i < averageArray.length; i++) {
//                if (averageArray[i] > 0) {
//                    averageArrayAvg += averageArray[i];
//                    averageArrayCnt++;
//                }
//            }

            //int rollingAverage = (averageArrayCnt > 0)?(averageArrayAvg/averageArrayCnt):0;
//            TYPE newType = currentType;
//            if (imgAvg < rollingAverage) {
//                newType = TYPE.RED;
//                if (newType != currentType) {
//                    beats++;
//                    flag=0;
//                    text2.setText("The number of pulses is   "+String.valueOf(beats));
//                    //					Log.e(TAG, "BEAT!! beats=" + beats);
//                }
//            } else if (imgAvg > rollingAverage) {
//                newType = TYPE.GREEN;
//            }
//
//            if (averageIndex == averageArraySize)
//                averageIndex = 0;
//            averageArray[averageIndex] = imgAvg;
//            averageIndex++;
//
//            // Transitioned from one state to another to the same
//            if (newType != currentType) {
//                currentType = newType;
//                //				image.postInvalidate();
//            }
//Get System End Time（ms）
//            long endTime = System.currentTimeMillis();
//            double totalTimeInSecs = (endTime - startTime) / 1000d;
//            if (totalTimeInSecs >= 2) {
//                double bps = (beats / totalTimeInSecs);
//                int dpm = (int) (bps * 60d);
//                if (dpm < 30 || dpm > 180||imgAvg<200) {
//                    //Get System start Time（ms）
//                    startTime = System.currentTimeMillis();
//                    //beats : Total heartbeat
//                    beats = 0;
//                    processing.set(false);
//                    return;
//                }
            //				Log.e(TAG, "totalTimeInSecs=" + totalTimeInSecs + " beats="+ beats);
//                if (beatsIndex == beatsArraySize)
//                    beatsIndex = 0;
//                beatsArray[beatsIndex] = dpm;
//                beatsIndex++;
//                int beatsArrayAvg = 0;
//                int beatsArrayCnt = 0;
//                for (int i = 0; i < beatsArray.length; i++) {
//                    if (beatsArray[i] > 0) {
//                        beatsArrayAvg += beatsArray[i];
//                        beatsArrayCnt++;
//                    }
//                }
//                int beatsAvg = (beatsArrayAvg / beatsArrayCnt);
//                text.setText("Your heart rate is : "+String.valueOf(beatsAvg)+"  zhi : "+String.valueOf(beatsArray.length)
//                        +"    "+String.valueOf(beatsIndex)+"    "+String.valueOf(beatsArrayAvg)+"    "+String.valueOf(beatsArrayCnt));
////Get System time（ms）
//                startTime = System.currentTimeMillis();
//                beats = 0;
//            }
//            processing.set(false);


        }

    };
    private static SurfaceHolder.Callback surfaceCallback = new SurfaceHolder.Callback() {

        public void surfaceCreated(SurfaceHolder holder) {
            try {
                camera.setPreviewDisplay(previewHolder);
                camera.setPreviewCallback(previewCallback);
            } catch (Throwable t) {
                //				Log.e("PreviewDemo-surfaceCallback","Exception in setPreviewDisplay()", t);
            }
        }

        public void surfaceChanged(SurfaceHolder holder, int format, int width,
                                   int height) {
            Camera.Parameters parameters = camera.getParameters();
            parameters.setFlashMode(Camera.Parameters.FLASH_MODE_TORCH);
            Camera.Size size = getSmallestPreviewSize(width, height, parameters);
            if (size != null) {
                parameters.setPreviewSize(size.width, size.height);
                //				Log.d(TAG, "Using width=" + size.width + " height="	+ size.height);
            }
            camera.setParameters(parameters);
            camera.startPreview();
        }

        public void surfaceDestroyed(SurfaceHolder holder) {
            // Ignore
        }
    };
    private static Camera.Size getSmallestPreviewSize(int width, int height,
                                                      Camera.Parameters parameters) {
        Camera.Size result = null;
        for (Camera.Size size : parameters.getSupportedPreviewSizes()) {
            if (size.width <= width && size.height <= height) {
                if (result == null) {
                    result = size;
                } else {
                    int resultArea = result.width * result.height;
                    int newArea = size.width * size.height;
                    if (newArea < resultArea)
                        result = size;
                }
            }
        }
        return result;
    }

}
