package easyway2in.com.billbook;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.design.widget.TabLayout.ViewPagerOnTabSelectedListener;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainApp extends AppCompatActivity {

    ViewPager  pager;
    TabLayout tabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.app_main);

        Intent intent = getIntent();


        tabLayout= (TabLayout) findViewById(R.id.tabs);


        pager = (ViewPager) findViewById(R.id.pager);
        MyAdapter adapter = new MyAdapter(getSupportFragmentManager());
        pager.setAdapter(adapter);

        tabLayout.setupWithViewPager(pager);
        pager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.setTabsFromPagerAdapter(adapter);
        tabLayout.addOnTabSelectedListener(new ViewPagerOnTabSelectedListener(pager));



    }
}
