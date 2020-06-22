package com.example.testpath;

import android.Manifest;
import android.app.Activity;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import org.apache.commons.math3.fitting.leastsquares.LeastSquaresFactory;
import org.apache.commons.math3.fitting.leastsquares.LeastSquaresOptimizer;

import org.apache.commons.math3.fitting.leastsquares.LeastSquaresOptimizer.Optimum;
import org.apache.commons.math3.fitting.leastsquares.LeastSquaresProblem;

import org.apache.commons.math3.fitting.leastsquares.LevenbergMarquardtOptimizer;
import org.apache.commons.math3.linear.ArrayRealVector;
import org.apache.commons.math3.linear.DiagonalMatrix;

import com.example.testpath.GPSTracker;


public class MainActivity extends AppCompatActivity {

//    Button btnShowLocation;
//    private static final int REQUEST_CODE_PERMISSION = 2;
//    String mPermission = Manifest.permission.ACCESS_FINE_LOCATION;
//
//    // GPSTracker class
//    GPSTracker gps;
//
//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//
//        try {
//            if (ActivityCompat.checkSelfPermission(this, mPermission)
//                    != MockPackageManager.PERMISSION_GRANTED) {
//
//                ActivityCompat.requestPermissions(this, new String[]{mPermission},
//                        REQUEST_CODE_PERMISSION);
//
//                // If any permission above not allowed by user, this condition will
//                execute every time, else your else part will work
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//        btnShowLocation = (Button) findViewById(R.id.button);
//
//        // show location button click event
//        btnShowLocation.setOnClickListener(new View.OnClickListener() {
//
//            @Override
//            public void onClick(View arg0) {
//                // create class object
//                gps = new GPSTracker(MainActivity.this);
//
//                // check if GPS enabled
//                if(gps.canGetLocation()){
//
//                    double latitude = gps.getLatitude();
//                    double longitude = gps.getLongitude();
//
//                    // \n is for new line
//                    Toast.makeText(getApplicationContext(), "Your Location is - \nLat: "
//                            + latitude + "\nLong: " + longitude, Toast.LENGTH_LONG).show();
//                }else{
//                    // can't get location
//                    // GPS or Network is not enabled
//                    // Ask user to enable GPS/network in settings
//                    gps.showSettingsAlert();
//                }
//
//            }
//        });
//    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try {
            double[][] positions = new double[][]{{5.0, -6.0}, {13.0, -15.0}, {21.0, -3.0}, {12.4, -21.2}};
            double[] distances = new double[]{8.06, 13.97, 23.32, 15.31};

            NonLinearLeastSquaresSolver solver = new NonLinearLeastSquaresSolver(new TrilaterationFunction(positions, distances),new LevenbergMarquardtOptimizer());
            Optimum optimum = solver.solve();


            double[] centroid = optimum.getPoint().toArray();

            for (int i=0;i< centroid.length;i++)System.out.println("Coordinates :: " + centroid[i]);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}


