package com.example.lifetrack.board;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.lifetrack.utils.Constants;

public class BoardAdapter extends FragmentPagerAdapter {

    public BoardAdapter(@NonNull FragmentManager fm) {
        super(fm);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        BoardFragment fragment = new BoardFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(Constants.FRAGMENT_POSITION, position);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public int getCount() {
        return 3;
    }
}
