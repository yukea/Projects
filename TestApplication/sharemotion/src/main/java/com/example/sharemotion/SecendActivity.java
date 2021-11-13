package com.example.sharemotion;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityOptionsCompat;

public class SecendActivity extends AppCompatActivity {
    private ImageView shareImage;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.secend_activity_main);
        shareImage = (ImageView) findViewById(R.id.share_image);
        shareImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SecendActivity.this,MainActivity.class);
                Bundle bundle = ActivityOptionsCompat.makeSceneTransitionAnimation(
                        SecendActivity.this,shareImage,"test_share").toBundle();
                startActivity(intent,bundle);
            }
        });
    }
}
