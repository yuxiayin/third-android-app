package com.example.third_android_app;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.button_listview).setOnClickListener(v -> open(ListViewActivity.class));
        findViewById(R.id.button_dialog).setOnClickListener(v -> open(DialogActivity.class));
        findViewById(R.id.button_menu).setOnClickListener(v -> open(MenuActivity.class));
        findViewById(R.id.button_action_mode).setOnClickListener(v -> open(ActionModeActivity.class));
        findViewById(R.id.button_recycler).setOnClickListener(v -> open(RecyclerViewActivity.class));
    }

    private void open(Class<?> activityClass) {
        startActivity(new Intent(this, activityClass));
    }
}
