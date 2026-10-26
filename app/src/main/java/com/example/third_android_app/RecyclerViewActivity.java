package com.example.third_android_app;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class RecyclerViewActivity extends AppCompatActivity {
    private List<AnimalData.Animal> animals;
    private AnimalAdapter adapter;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle(R.string.recycler_title);
        setContentView(R.layout.activity_recycler);
        recyclerView = findViewById(R.id.animal_recycler);
        animals = AnimalData.createAnimals();
        adapter = new AnimalAdapter(animals);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        DividerItemDecoration divider = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        divider.setDrawable(getDrawable(R.drawable.recycler_divider));
        recyclerView.addItemDecoration(divider);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_add_animal, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.add_lion) addAnimal(0);
        else if (id == R.id.add_tiger) addAnimal(1);
        else if (id == R.id.add_monkey) addAnimal(2);
        else if (id == R.id.add_dog) addAnimal(3);
        else if (id == R.id.add_cat) addAnimal(4);
        else if (id == R.id.add_elephant) addAnimal(5);
        else return super.onOptionsItemSelected(item);
        return true;
    }

    private void addAnimal(int animalIndex) {
        animals.add(new AnimalData.Animal(
                AnimalData.NAMES[animalIndex], AnimalData.IMAGES[animalIndex]));
        adapter.notifyItemInserted(animals.size() - 1);
        recyclerView.smoothScrollToPosition(animals.size() - 1);
    }

    private final class AnimalAdapter extends RecyclerView.Adapter<AnimalViewHolder> {
        private final List<AnimalData.Animal> animals;
        private int selectedPosition = RecyclerView.NO_POSITION;

        AnimalAdapter(List<AnimalData.Animal> animals) {
            this.animals = animals;
        }

        @NonNull
        @Override
        public AnimalViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.row_animal, parent, false);
            return new AnimalViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull AnimalViewHolder holder, int position) {
            AnimalData.Animal animal = animals.get(position);
            holder.name.setText(animal.name);
            holder.image.setImageResource(animal.imageRes);
            holder.itemView.setActivated(position == selectedPosition);
            holder.itemView.setOnClickListener(v -> {
                int clickedPosition = holder.getBindingAdapterPosition();
                if (clickedPosition == RecyclerView.NO_POSITION) return;
                AnimalData.Animal clicked = animals.get(clickedPosition);
                Toast.makeText(RecyclerViewActivity.this, clicked.name, Toast.LENGTH_SHORT).show();
                int previousPosition = selectedPosition;
                selectedPosition = clickedPosition;
                if (previousPosition != RecyclerView.NO_POSITION) {
                    notifyItemChanged(previousPosition);
                }
                notifyItemChanged(selectedPosition);
            });
        }

        @Override public int getItemCount() { return animals.size(); }
    }

    private static final class AnimalViewHolder extends RecyclerView.ViewHolder {
        final ImageView image;
        final TextView name;

        AnimalViewHolder(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.animal_image);
            name = itemView.findViewById(R.id.animal_name);
        }
    }
}
