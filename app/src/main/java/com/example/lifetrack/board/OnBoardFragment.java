package com.example.lifetrack.board;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.lifetrack.R;
import com.example.lifetrack.databinding.FragmentOnBoardBinding;
import com.example.lifetrack.utils.Constants;

public class OnBoardFragment extends Fragment {

    FragmentOnBoardBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentOnBoardBinding.inflate(getLayoutInflater());
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setupViewPager();
        openHomeFragment();
        isOpenHome();
    }

    private void isOpenHome() {
        SharedPreferences sharedPreferences = requireActivity().getSharedPreferences(Constants.BOARD_PREF, Context.MODE_PRIVATE);
        boolean isShow = sharedPreferences.getBoolean(Constants.IS_SHOW_BOARD, false);
        if (isShow){
            Navigation.findNavController(requireView()).navigate(R.id.homeFragment);
        }
    }

    private void openHomeFragment() {
        binding.continueBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(requireView()).navigate(R.id.homeFragment);
                SharedPreferences sharedPreferences = requireActivity().getSharedPreferences(Constants.BOARD_PREF, Context.MODE_PRIVATE);
                sharedPreferences.edit().putBoolean(Constants.IS_SHOW_BOARD, true).apply();
            }
        });
    }

    private void setupViewPager() {
        binding.viewpager.setAdapter(new BoardAdapter(requireActivity().getSupportFragmentManager()));
    }
}