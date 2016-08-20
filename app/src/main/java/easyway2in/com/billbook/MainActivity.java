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




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        logo = (ImageView) findViewById(R.id.logo);


        Animation animation1 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.zoom);

        logo.startAnimation(animation1);

        final DBHandler db = new DBHandler(getApplicationContext());
        final Thread timerThread = new Thread(){
            public void run(){
                try{
                    sleep(3100);
                }catch(InterruptedException e){
                    e.printStackTrace();
                }finally{

                    if(db.getRecordsRowCount()>0){
                    Intent intent = new Intent(MainActivity.this,MainApp.class);
                    startActivity(intent);
                    overridePendingTransition(R.anim.zoom_in, R.anim.zoom_out);
                    finish();
                    }


                    else{
                        Intent intent = new Intent(MainActivity.this,AppIntro.class);
                        startActivity(intent);
                        overridePendingTransition(R.anim.zoom_in, R.anim.zoom_out);
                        finish();
                    }
                }
            }
        };
        timerThread.start();


    }


}
