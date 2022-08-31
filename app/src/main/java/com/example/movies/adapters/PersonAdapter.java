package com.example.movies.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.movies.data.models.Person;
import com.example.movies.R;

import java.util.ArrayList;
import java.util.List;

public class PersonAdapter extends RecyclerView.Adapter<PersonAdapter.PersonHolder> {

    private List<Person> people = new ArrayList<>();

    public void setPeople(List<Person> people) {
        this.people = people;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public PersonHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.person_item,
                parent,
                false
        );
        return new PersonHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PersonHolder holder, int position) {
        Person person = people.get(position);

        Glide.with(holder.itemView)
                .load(person.getPhotoUrl())
                .into(holder.imageViewPerson);
        holder.textViewPersonName.setText(person.getName());
        holder.textViewPersonRole.setText(person.getDescription());
    }

    @Override
    public int getItemCount() {
        return people.size();
    }

    protected static class PersonHolder extends RecyclerView.ViewHolder {

        private final ImageView imageViewPerson;
        private final TextView textViewPersonName;
        private final TextView textViewPersonRole;

        public PersonHolder(@NonNull View itemView) {
            super(itemView);
            imageViewPerson = itemView.findViewById(R.id.imageViewPerson);
            textViewPersonName = itemView.findViewById(R.id.textViewPersonName);
            textViewPersonRole = itemView.findViewById(R.id.textViewPersonRole);
        }
    }
}
