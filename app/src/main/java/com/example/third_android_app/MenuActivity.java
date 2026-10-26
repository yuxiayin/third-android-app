package com.example.third_android_app;

import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class MenuActivity extends AppCompatActivity {
    private TextView testText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle(R.string.menu_title);
        setContentView(R.layout.activity_menu);
        testText = findViewById(R.id.menu_test_text);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_text_options, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.font_small) testText.setTextSize(10);
        else if (id == R.id.font_medium) testText.setTextSize(16);
        else if (id == R.id.font_large) testText.setTextSize(20);
        else if (id == R.id.color_red) testText.setTextColor(Color.RED);
        else if (id == R.id.color_black) testText.setTextColor(Color.BLACK);
        else if (id == R.id.normal_item) {
            Toast.makeText(this, R.string.normal_toast, Toast.LENGTH_SHORT).show();
        } else {
            return super.onOptionsItemSelected(item);
        }
        return true;
    }
}
