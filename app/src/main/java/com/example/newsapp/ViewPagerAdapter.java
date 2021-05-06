package com.example.newsapp;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

public class ViewPagerAdapter extends FragmentStatePagerAdapter {

    private String[] titles;
    private String language;

    public ViewPagerAdapter(@NonNull FragmentManager fm, int behavior, Context context, String language) {
        super(fm, behavior);
        this.language = language;
        titles = context.getResources().getStringArray(R.array.tab_titles);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        Fragment fragment;

        Bundle args = new Bundle();
        args.putInt("screen_type", position);
        args.putString("lang", language);
        fragment = new FragmentNewsDetails();
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public int getCount() {
        return titles.length;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return titles[position];
    }
}
