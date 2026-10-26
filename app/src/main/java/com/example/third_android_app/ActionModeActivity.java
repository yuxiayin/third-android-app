package com.example.third_android_app;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.view.ActionMode;

import java.util.ArrayList;
import java.util.Arrays;

public class ActionModeActivity extends AppCompatActivity {
    private final ArrayList<String> items = new ArrayList<>(Arrays.asList(AnimalData.NAMES));
    private ArrayAdapter<String> adapter;
    private ListView listView;
    private ActionMode actionMode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle(R.string.demo_action_mode);
        setContentView(R.layout.activity_action_mode);
        listView = findViewById(R.id.action_list);
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_activated_1, items);
        listView.setAdapter(adapter);
        listView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
        listView.setOnItemClickListener((parent, view, position, id) -> {
            int selectedCount = listView.getCheckedItemCount();
            if (selectedCount > 0 && actionMode == null) {
                actionMode = startSupportActionMode(actionModeCallback);
            }
            if (actionMode != null) {
                actionMode.setTitle(getString(R.string.selected_count, selectedCount));
                if (selectedCount == 0) actionMode.finish();
            }
        });
    }

    private final ActionMode.Callback actionModeCallback = new ActionMode.Callback() {
            @Override
            public boolean onCreateActionMode(ActionMode mode, Menu menu) {
                mode.getMenuInflater().inflate(R.menu.menu_action_mode, menu);
                return true;
            }

            @Override public boolean onPrepareActionMode(ActionMode mode, Menu menu) { return false; }

            @Override
            public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
                if (item.getItemId() != R.id.action_delete) return false;
                for (int i = items.size() - 1; i >= 0; i--) {
                    if (listView.isItemChecked(i)) items.remove(i);
                }
                adapter.notifyDataSetChanged();
                mode.finish();
                return true;
            }

            @Override
            public void onDestroyActionMode(ActionMode mode) {
                listView.clearChoices();
                adapter.notifyDataSetChanged();
                actionMode = null;
            }
        };
}
