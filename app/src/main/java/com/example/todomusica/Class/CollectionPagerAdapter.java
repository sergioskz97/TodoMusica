package com.example.todomusica.Class;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.todomusica.DemoFragment;

public class CollectionPagerAdapter extends FragmentStatePagerAdapter {
    public CollectionPagerAdapter(@NonNull FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment = new DemoFragment();
        Bundle args = new Bundle();
        args.putInt(DemoFragment.ARG_OBJECT, position + 1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getCount() {
        return 3;
    }
}
