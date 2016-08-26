package easyway2in.com.billbook;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    ImageView logo;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        logo = (ImageView) findViewById(R.id.logo);

        Intent myIntent = new Intent(MainActivity.this , Receiver.class);
        AlarmManager alarmManager = (AlarmManager)getSystemService(ALARM_SERVICE);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(getApplicationContext(),0,myIntent,PendingIntent.FLAG_UPDATE_CURRENT);

        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 21);
        calendar.set(Calendar.MINUTE, 00);
        calendar.set(Calendar.SECOND, 00);

        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), 24*60*60*1000 , pendingIntent);

       /* Calendar c = Calendar.getInstance();


        Intent myintent = new Intent(this,MainApp.class);
        PendingIntent pi = PendingIntent.getBroadcast(this,0,myintent,0);
        AlarmManager am = (AlarmManager) getSystemService(ALARM_SERVICE);
        am.setRepeating(AlarmManager.RTC,8000000,AlarmManager.INTERVAL_DAY,pi);*/

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
                    Intent intent = new Intent(MainActivity.this,SignIn.class);
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


}/*
NotificationCompat.Builder builder;

builder = new NotificationCompat.Builder(this);
        builder.setSmallIcon(R.drawable.ic_call_black_48dp);
        builder.setContentTitle("Title");
        builder.setContentText("Msg");
        Intent myintent = new Intent(this,Main2Activity.class);
        PendingIntent pi = PendingIntent.getBroadcast(this,0,myintent,0);
        AlarmManager am = (AlarmManager) getSystemService(ALARM_SERVICE);
        am.setRepeating(AlarmManager.RTC,8000000,AlarmManager.INTERVAL_DAY,pi);


        text.setOnClickListener(new View.OnClickListener() {
@Override
public void onClick(View v) {
        Notify("New Message");
        }
        });

        }

private void Notify(String msg ){
        NotificationManager nmanager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        nmanager.notify(736,builder.build());
        }*/