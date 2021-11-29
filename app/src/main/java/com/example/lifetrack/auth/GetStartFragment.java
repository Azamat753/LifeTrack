package com.example.lifetrack.auth;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.lifetrack.R;
import com.example.lifetrack.databinding.FragmentGetStartBinding;


public class GetStartFragment extends Fragment {
    FragmentGetStartBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentGetStartBinding.inflate(getLayoutInflater());
       return  binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
      binding.getStart.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {
              Navigation.findNavController(requireView()).navigate(R.id.homeFragment);
          }
      });
    }
}