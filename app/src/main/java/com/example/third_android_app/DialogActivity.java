package com.example.third_android_app;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class DialogActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle(R.string.dialog_title);
        setContentView(R.layout.activity_dialog);
        findViewById(R.id.show_dialog_button).setOnClickListener(v -> showLoginDialog());
    }

    private void showLoginDialog() {
        View content = LayoutInflater.from(this).inflate(R.layout.dialog_login, null);
        EditText username = content.findViewById(R.id.username_input);
        new AlertDialog.Builder(this)
                .setView(content)
                .setNegativeButton(R.string.cancel, null)
                .setPositiveButton(R.string.sign_in, (dialog, which) ->
                        Toast.makeText(this, "Welcome " + username.getText(), Toast.LENGTH_SHORT).show())
                .show();
    }
}
