package com.shabbirdhangot.nougatnotification;

import android.app.PendingIntent;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v4.app.RemoteInput;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    Context mContext;
    boolean isCallBack;
    boolean isReplyMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        mContext=this;

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showNotification();
            }
        });


    }

    private void showNotification() {
        String replyLabel = getString(R.string.reply);
        RemoteInput remoteInput = new RemoteInput.Builder(Constants.REPLY_KEY)
                .setLabel(replyLabel)
                .build();

        Intent callbackIntent = new Intent(this,ResultActivity.class);
        callbackIntent.putExtra("CallBack",true);
        PendingIntent callbackPI = PendingIntent.getActivity(this,0,callbackIntent,0);

        NotificationCompat.Action messageReplyAction =  null;
        NotificationCompat.Action callReplyAction    = new NotificationCompat.Action.Builder(0,"Call Back",callbackPI).build();


        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            Intent        messageReplyIntent = new Intent(this, ResultActivity.class);
            PendingIntent messageReplyPI     = PendingIntent.getActivity(this, 0, messageReplyIntent, 0);

            messageReplyAction = new NotificationCompat.Action.Builder(R.drawable.ic_reply,
                    getString(R.string.reply), messageReplyPI)
                    .addRemoteInput(remoteInput)
                    .build();
        }else{
            Intent        messageReplyIntent = new Intent(this, AndroidLowerNReply.class);
            PendingIntent messageReplyPI     = PendingIntent.getActivity(this, 0, messageReplyIntent, PendingIntent.FLAG_UPDATE_CURRENT);

            messageReplyAction = new NotificationCompat.Action.Builder(R.drawable.ic_reply,
                    getString(R.string.reply), messageReplyPI)
                    .addRemoteInput(remoteInput)
                    .build();
        }

        NotificationCompat.Builder newMessageNotification =
                new NotificationCompat.Builder(mContext)
                        .setSmallIcon(R.mipmap.ic_launcher)
                        .setContentTitle(getString(R.string.username))
                        .setContentText(getString(R.string.content))
                        .addAction(messageReplyAction)
                        .addAction(callReplyAction);

        NotificationManagerCompat notificationManager =NotificationManagerCompat.from(this);
        notificationManager.notify(Constants.NOTIFICATION_ID, newMessageNotification.build());
        finish();
    }
}
