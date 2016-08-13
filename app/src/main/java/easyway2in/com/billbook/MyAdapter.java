package easyway2in.com.billbook;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by Sameer1 on 01-08-2016.
 */
public class MyAdapter extends FragmentPagerAdapter {
    int count = 3;
    public MyAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch(position) {
            case 0:
                Fragment fm1 = new Fragment1();
                return fm1;
            case 1:
                Fragment fm2 = new Fragment2();
                return fm2;
            default:
                Fragment fm3 = new Fragment3();
                return fm3;
        }
    }

    @Override
    public int getCount() {
        return count;
    }
    @Override
    public CharSequence getPageTitle(int position) {
        String title = "";
        switch (position) {
            case 0:
                title = "CREDIT";
                break;
            case 1:
                title = "DEBIT";
                break;
            case 2:
                title = "BALANCE";
                break;
        }
        return title;
    }


}
