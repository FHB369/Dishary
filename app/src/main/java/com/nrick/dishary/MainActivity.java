package com.nrick.dishary;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

public class MainActivity extends AppCompatActivity {

    private CardView course, profile;
    private Button scan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        course = findViewById(R.id.course);
        profile = findViewById(R.id.profile);
        scan = findViewById(R.id.qr_scan);

        course.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, AllCourse.class);
                startActivity(intent);
                overridePendingTransition(R.anim.right_to_left, R.anim.right_to_left_exit);
            }
        });

        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Profile.class);
                startActivity(intent);
                overridePendingTransition(R.anim.right_to_left, R.anim.right_to_left_exit);
                finish();
            }
        });

        scan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                IntentIntegrator intentIntegrator = new IntentIntegrator(MainActivity.this);
                intentIntegrator.setDesiredBarcodeFormats(IntentIntegrator.QR_CODE_TYPES);
                intentIntegrator.setCameraId(0);
                intentIntegrator.setCaptureActivity(AnyOrientationCaptureActivity.class);
                intentIntegrator.setOrientationLocked(false);
                intentIntegrator.setPrompt("scanning");
                intentIntegrator.setBeepEnabled(true);
                intentIntegrator.setBarcodeImageEnabled(true);
                intentIntegrator.initiateScan();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        final IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if(result!=null && result.getContents()!=null){
            Log.d("QR", result.getContents());
            Intent intent = new Intent(MainActivity.this, QRScan.class);
            intent.putExtra("id", result.getContents());
            startActivity(intent);
        }
    }
}
