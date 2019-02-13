package com.example.marko.justgo;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

class SectionPagerAdapter extends FragmentPagerAdapter {


    public SectionPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {

        switch (position){
            case 0:
                CheckBoxFragment checkBoxFragment= new CheckBoxFragment();
                return  checkBoxFragment;

            case 1:
                WorldClockFragment worldClockFragment=new WorldClockFragment();
                return  worldClockFragment;

            case 2:
                ConverterFragment converterFragment=new ConverterFragment();
                return  converterFragment;

            default:
                return null;

        }

    }

    @Override
    public int getCount(){
        return 3;
    }

    public CharSequence getPageTitle(int position){

        switch (position) {
            case 0:
                return "CHECKBOX";
            case 1:
                return "WORLD CLOCK";
            case 2:
                return "CONVERTER";
            default:
                return null;
        }
    }
}
