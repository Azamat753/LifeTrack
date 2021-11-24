package com.example.lifetrack.fragment;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.DatePicker;
import android.widget.RadioButton;

import com.example.lifetrack.R;
import com.example.lifetrack.databinding.FragmentCreateTaskBinding;
import com.example.lifetrack.model.TaskModel;
import com.example.lifetrack.utils.App;
import com.example.lifetrack.utils.Constants;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import java.util.Calendar;


public class CreateTaskFragment extends BottomSheetDialogFragment implements DatePickerDialog.OnDateSetListener {
    FragmentCreateTaskBinding binding;
    String userTask;
    String deadline;
    String repeatCount;
    private int startYear;
    private int starthMonth;
    private int startDay;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentCreateTaskBinding.inflate(getLayoutInflater());
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull @org.jetbrains.annotations.NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initClickers();
        fillDialog();
    }

    private void fillDialog() {
        if (getTag().equals(Constants.UPDATE)) {
            TaskModel taskModel = (TaskModel) getArguments().getSerializable(Constants.UPDATE_MODEL);

            deadline = taskModel.getDeadline();
            userTask = taskModel.getTask();
            repeatCount = taskModel.getRepeatCount();

            binding.taskEd.setText(userTask);
            binding.dateTv.setText(deadline);
            binding.repeatTv.setText(repeatCount);
        }
    }

    private void initClickers() {
        binding.addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (getTag().equals(Constants.UPDATE)) {
                    updateTask();
                } else {
                    insertTask();
                }
            }
        });
        binding.dateTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog();
            }
        });
        binding.repeatTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showRepeatDialog();
            }
        });
    }

    private void updateTask() {
        TaskModel taskModel = (TaskModel) getArguments().getSerializable(Constants.UPDATE_MODEL);
        deadline = binding.dateTv.getText().toString();
        userTask = binding.taskEd.getText().toString();
        repeatCount = binding.repeatTv.getText().toString();
        TaskModel updateModel = new TaskModel(userTask, deadline, repeatCount);
        updateModel.setId(taskModel.getId());
        App.getInstance().getDatabase().taskDao().update(updateModel);
        dismiss();
    }

    private void showRepeatDialog() {
        LayoutInflater inflater = LayoutInflater.from(requireContext());
        @SuppressLint("InflateParams") View view = inflater.inflate(R.layout.repeat_dialog, null);
        Dialog alertDialog = new Dialog(requireContext());
        alertDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        alertDialog.setContentView(view);
        alertDialog.show();
        RadioButton neverBtn = alertDialog.findViewById(R.id.never);
        neverBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.repeatTv.setText(neverBtn.getText().toString());
                repeatCount = neverBtn.getText().toString();
                alertDialog.dismiss();
            }
        });
    }

    private void showDialog() {
        Calendar calendar = Calendar.getInstance();
        startYear = calendar.get(Calendar.YEAR);
        starthMonth = calendar.get(Calendar.MONTH);
        startDay = calendar.get(Calendar.DAY_OF_MONTH);
        DatePickerDialog datePickerDialog = new DatePickerDialog(
                requireContext(), this::onDateSet, startYear, starthMonth, startDay);
        datePickerDialog.show();
    }

    private void insertTask() {
        userTask = binding.taskEd.getText().toString();
        TaskModel taskModel = new TaskModel(userTask, deadline, repeatCount);
        App.getInstance().getDatabase().taskDao().insert(taskModel);
        dismiss();
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
        deadline = year + "." + month + "." + day;
        binding.dateTv.setText(deadline);
    }
}