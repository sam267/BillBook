package easyway2in.com.billbook;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    ImageView logo;
    TextView start;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        logo = (ImageView) findViewById(R.id.logo);
        start = (TextView) findViewById(R.id.start);

        Animation animation1 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.zoom);
        Animation animation2 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.get_started);

        logo.startAnimation(animation1);
        start.startAnimation(animation2);

        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getApplicationContext(),AppIntro.class);
                startActivity(intent);
                overridePendingTransition(R.anim.zoom_in, R.anim.zoom_out);
                finish();
            }
        });

    }


}
