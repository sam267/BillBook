package easyway2in.com.billbook;

import android.content.Intent;
import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

public class AppIntro extends AppCompatActivity {
int count =3;
    ViewPager pager;
    TextView[] view = new TextView[3];


    String unselected = ".";
    String selected = ".";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.app_intro);

        view[0] = (TextView) findViewById(R.id.view1);
        view[1] = (TextView) findViewById(R.id.view2);
        view[2] = (TextView) findViewById(R.id.view3);

        view[0].setTextColor(Color.parseColor("#fff123"));
        view[1].setTextColor(Color.parseColor("#ffffff"));
        view[2].setTextColor(Color.parseColor("#ffffff"));



        Intent intent = getIntent();

        pager = (ViewPager) findViewById(R.id.pager);
        MyPagerAdapter adapter = new MyPagerAdapter(getSupportFragmentManager());
        pager.setAdapter(adapter);

        pager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                view[0].setTextColor(Color.parseColor("#ffffff"));
                view[1].setTextColor(Color.parseColor("#ffffff"));
                view[2].setTextColor(Color.parseColor("#ffffff"));


               view[position].setTextColor(Color.parseColor("#fff123"));


            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }



}
