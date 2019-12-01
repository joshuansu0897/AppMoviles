package com.example.proyectofinal.views.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.proyectofinal.DB.User;
import com.example.proyectofinal.R;
import com.example.proyectofinal.utils.ImageUtils;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ToDoListAdapter extends RecyclerView.Adapter<ToDoListAdapter.ViewHolder> {

    private ArrayList<User> toDoList;

    public ToDoListAdapter() {
        toDoList = new ArrayList<>();
    }

    @NonNull
    @Override
    public ToDoListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.list_item, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ToDoListAdapter.ViewHolder viewHolder, int position) {
        viewHolder.bindData(toDoList.get(position));
    }

    @Override
    public int getItemCount() {
        return toDoList.size();
    }

    public void updateToDos(ArrayList<User> todos) {
        toDoList.clear();
        toDoList.addAll(todos);
        notifyDataSetChanged();
    }

    public void addToDos(ArrayList<User> todos) {
        toDoList.addAll(todos);
        notifyDataSetChanged();
    }


    static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        User user;

        @BindView(R.id.image_card)
        ImageView image_card;
        @BindView(R.id.username_card)
        TextView username_card;
        @BindView(R.id.high_score_card)
        TextView high_score_card;
        @BindView(R.id.cardViewContainer)
        CardView container;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        void bindData(User toDoItem) {
            user = toDoItem;
            username_card.setText(user.getUsername());
            high_score_card.setText("HIGH SCORE:" + user.getHighScore());
            image_card.setImageBitmap(ImageUtils.convert(user.getPhoto()));
            container.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {

        }
    }
}
