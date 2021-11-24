package com.example.lifetrack.fragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.lifetrack.R;
import com.example.lifetrack.adapter.TaskAdapter;
import com.example.lifetrack.databinding.FragmentHomeBinding;
import com.example.lifetrack.model.TaskModel;
import com.example.lifetrack.utils.App;
import com.example.lifetrack.utils.Constants;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class HomeFragment extends Fragment implements TaskAdapter.Listener {

    FragmentHomeBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentHomeBinding.inflate(getLayoutInflater());
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.addFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CreateTaskFragment createTaskFragment = new CreateTaskFragment();
                createTaskFragment.show(requireActivity().getSupportFragmentManager(), "ololo");
            }
        });
        initRecycler();
    }

    private void initRecycler() {
        App.getInstance().getDatabase().taskDao().getAll().observe(getViewLifecycleOwner(), taskModels -> {
            TaskAdapter taskAdapter = new TaskAdapter((ArrayList<TaskModel>) taskModels,this);
            binding.taskRecycler.setAdapter(taskAdapter);
        });
    }

    @Override
    public void itemLongClick(TaskModel model) {
        new AlertDialog.Builder(requireContext())
                .setTitle("Delete entry")
                .setMessage("Are you sure you want to delete this entry?")

                // Specifying a listener allows you to take an action before dismissing the dialog.
                // The dialog is automatically dismissed when a dialog button is clicked.
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        App.getInstance().getDatabase().taskDao().delete(model);
                    }
                })

                // A null listener allows the button to dismiss the dialog and take no further action.
                .setNegativeButton(android.R.string.no, null)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();
    }

    @Override
    public void itemClick(TaskModel model) {
        CreateTaskFragment createTaskFragment = new CreateTaskFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable(Constants.UPDATE_MODEL,model);
        createTaskFragment.setArguments(bundle);
        createTaskFragment.show(requireActivity().getSupportFragmentManager(), Constants.UPDATE);
    }
}