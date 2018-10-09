package com.haytran.arctabbar;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.haytran.arctabbar.ArcTabBar.ArcTabBar;
import com.haytran.arctabbar.ArcTabBar.ArcTabBarListener;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ArcTabBar arcTabBar = findViewById(R.id.arcTabBar);
        arcTabBar.setListener(new ArcTabBarListener() {
            @Override
            public void didClickedCircle(int position) {
                Toast.makeText(MainActivity.this, "Did clicked at item " + position, Toast.LENGTH_SHORT).show();
            }
        });

    }
}