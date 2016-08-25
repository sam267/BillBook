package easyway2in.com.billbook;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.design.widget.TabLayout.ViewPagerOnTabSelectedListener;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class MainApp extends AppCompatActivity {

    ViewPager  pager;
    TabLayout tabLayout;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.app_main);

        Intent intent = getIntent();

        toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setLogoDescription("BillBook");
        getSupportActionBar().setTitle("BillBook");
        toolbar.setTitleMargin(3, 0, 0, 0);

        pager = (ViewPager) findViewById(R.id.pager);
        final MyAdapter adapter = new MyAdapter(getSupportFragmentManager());
        pager.setAdapter(adapter);

        tabLayout= (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(pager);


    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.new_month) {
            Intent intent = new Intent(getApplicationContext(), ResetMonth.class);
            startActivity(intent);
            overridePendingTransition(R.anim.zoom_in, R.anim.zoom_out);
            return true;
        }
        else if(id == R.id.view_stats){
            Intent intent = new Intent(getApplicationContext(), Statistics.class);
            startActivity(intent);
            overridePendingTransition(R.anim.zoom_in, R.anim.zoom_out);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
