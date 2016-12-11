package com.example.android.miwok;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.android.miwok.ColorsFragment;
import com.example.android.miwok.FamilyFragment;
import com.example.android.miwok.NumberFragment;
import com.example.android.miwok.PhrasesFragment;
import com.example.android.miwok.R;

/**
 * Created by gbans6 on 12/10/2016.
 */

public class MiwokPagerAdapter extends FragmentPagerAdapter {

    private Context context;

    public MiwokPagerAdapter(FragmentManager fm, Context context) {
        super(fm);
        this.context = context;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return  new NumberFragment();
            case 1:
                return  new FamilyFragment();
            case 2:
                return  new ColorsFragment();
            case 3:
                return new PhrasesFragment();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return 4;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return  context.getString(R.string.category_numbers);
            case 1:
                return  context.getString(R.string.category_family);
            case 2:
                return  context.getString(R.string.category_colors);
            case 3:
                return  context.getString(R.string.category_phrases);
            default:
                return null;
        }
    }
}
