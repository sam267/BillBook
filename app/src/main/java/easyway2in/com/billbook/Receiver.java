package easyway2in.com.billbook;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.NotificationCompat;

/**
 * Created by Sameer1 on 26-08-2016.
 */
public class Receiver extends BroadcastReceiver {
    NotificationCompat.Builder builder;
    @Override
    public void onReceive(Context context, Intent intent) {

        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        Intent rIntent = new Intent(context,SignIn.class);
        rIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        PendingIntent pendingIntent = PendingIntent.getActivity(context,0,rIntent,PendingIntent.FLAG_UPDATE_CURRENT);


        builder = new NotificationCompat.Builder(context);
        builder.setContentIntent(pendingIntent);
        builder.setSmallIcon(R.drawable.logo2);
        builder.setContentTitle("Its Time");
        builder.setContentText("Make your daily Entries now to stay updated...");
        builder.setAutoCancel(true);

        notificationManager.notify(0,builder.build());
    }
}
