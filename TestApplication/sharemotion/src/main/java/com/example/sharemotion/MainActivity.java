package com.example.sharemotion;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityOptionsCompat;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    /*支持共享元素动画，该动画主要用于两个activity之间，两个Activity可以共享某些控件，
    Activity A跳转到Activity B的时候，A的某个控件能自动移动到B的相应控件的位置，产生动画
    1、在要共享的控件中加上属性，两个Activity的共享控件都要加这个属性，并且值保持一致。
    android:transitionName="test_share"
    2、跳转到第二个Activity的时候，用下面的方式跳转
     Intent intent = new Intent(MainActivity.this,SecendActivity.class);
                Bundle bundle = ActivityOptionsCompat.makeSceneTransitionAnimation(
                        MainActivity.this,shareImage,"test_share").toBundle();
                startActivity(intent,bundle);
     ActivityOptionsCompat.makeSceneTransitionAnimation方法，该方法有两个API：
     makeSceneTransitionAnimation(@NonNull Activity activity,
            @NonNull View sharedElement, @NonNull String sharedElementName)  -- activity就是发起跳转的Activity，shareElement就是共享的控件的id，sharedElementName就是第一步定义的字符串。这个方式只支持共享单个控件
     makeSceneTransitionAnimation(@NonNull Activity activity,
            Pair<View, String>... sharedElements)  --  ：activity就是发起跳转的Activity，sharedElements是一个类型为Paire<View,String>可变长数组，每一个pair存放的就是一个共享控件的id和对应的字符串，可见该方法是可以支持多个共享控件的。
    3.共享元素一般都是相同的控件，比如两个控件都为ImageView，但是如果不是同一类型的控件，比如ImageView和一个TextView共享了，也是可以的，效果一样
     */

    private ImageView shareImage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        shareImage = (ImageView) findViewById(R.id.share_image);
        shareImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,SecendActivity.class);
                Bundle bundle = ActivityOptionsCompat.makeSceneTransitionAnimation(
                        MainActivity.this,shareImage,"test_share").toBundle();
                startActivity(intent,bundle);
            }
        });
    }
}