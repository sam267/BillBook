package easyway2in.com.billbook;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by Sameer1 on 03-08-2016.
 */
public class MyPagerAdapter extends FragmentPagerAdapter {
    int count=3;

    public MyPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0: Fragment fm1= new IntroFragment1();
                return fm1;
            case 1: Fragment fm2= new IntroFragment2();
                return fm2;
            default: Fragment fm3= new IntroFragment3();
                return fm3;
        }

    }

    @Override
    public int getCount() {
        return count;
    }
}

