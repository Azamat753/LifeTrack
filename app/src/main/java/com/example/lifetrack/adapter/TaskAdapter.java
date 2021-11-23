package com.example.lifetrack.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lifetrack.R;
import com.example.lifetrack.databinding.ItemTaskBinding;
import com.example.lifetrack.model.TaskModel;

import java.util.ArrayList;

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.TaskViewHolder> {
    ArrayList<TaskModel> list;
    ItemTaskBinding binding;
    Listener listener;

    public TaskAdapter(ArrayList<TaskModel> list, Listener listener) {
        this.list = list;
        this.listener = listener;
    }

    @NonNull
    @Override
    public TaskViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        binding = ItemTaskBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new TaskViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull TaskViewHolder holder, int position) {
        holder.onFill(list.get(position));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class TaskViewHolder extends RecyclerView.ViewHolder {

        public TaskViewHolder(@NonNull ItemTaskBinding binding) {
            super(binding.getRoot());
        }

        public void onFill(TaskModel model) {
            binding.taskTv.setText(model.getTask());
            binding.deadlineTv.setText(model.getDeadline());
            binding.repeatTv.setText(model.getRepeatCount());

            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    listener.itemClick(model);
                    return true;
                }
            });
        }
    }

    public interface Listener {
        void itemClick(TaskModel taskModel);
    }
}
