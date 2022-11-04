package com.pillgood.drholmes.noti;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.util.Log;
import android.widget.RemoteViews;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import com.pillgood.drholmes.MainActivity;
import com.pillgood.drholmes.R;

public class MyFirebaseMessaging extends FirebaseMessagingService {

//    fnfnfnfn


    //    @Override
//    public void onMessageReceived(@NonNull RemoteMessage message) {
//        super.onMessageReceived(message);
//
//        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(getApplicationContext());
//
//        NotificationCompat.Builder builder = null;
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//            if (notificationManager.getNotificationChannel(CHANNEL_ID) == null) {
//                NotificationChannel channel = new NotificationChannel(CHANNEL_ID, CHANNEL_NAME, NotificationManager.IMPORTANCE_DEFAULT);
//                notificationManager.createNotificationChannel(channel);
//            }
//            builder = new NotificationCompat.Builder(getApplicationContext(), CHANNEL_ID);
//        }else {
//            builder = new NotificationCompat.Builder(getApplicationContext(), CHANNEL_NAME);
//        }
//
//        String title = message.getNotification().getTitle();
//        String body = message.getNotification().getBody();
//
//        builder.setContentTitle(title)
//                .setContentText(body)
//                .setSmallIcon(R.drawable.ic_launcher_background);
//
//        Notification notification = builder.build();
//        notificationManager.notify(1, notification);
//    }
//
    @Override
    public void onNewToken(@NonNull String token) {
        super.onNewToken(token);
    }

    @Override
    public void onMessageReceived(@NonNull RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);
    }

    private void sendNotification(String messageBody) {
        //알림 클릭시 실행될 액티비티 (PendingIntent)
        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0 /* Request code */, intent,
                PendingIntent.FLAG_ONE_SHOT);

        String channelId = getString(R.string.default_notification_channel_id);
        Uri defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        NotificationCompat.Builder notificationBuilder =
                new NotificationCompat.Builder(this, channelId)
                        .setSmallIcon(R.drawable.ic_splash_logo)
                        .setContentTitle(getString(R.string.fcm_message))
                        .setContentText(messageBody)
                        .setAutoCancel(true)
                        .setSound(defaultSoundUri)
                        .setContentIntent(pendingIntent);

        //노티 메니저로 알림 팝업 띄우기
        NotificationManager notificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        // 오래오 버전 이상부터 channelId 값이 필수가 됨
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(channelId,
                    "Channel human readable title",
                    NotificationManager.IMPORTANCE_DEFAULT);
            notificationManager.createNotificationChannel(channel);
        }

        notificationManager.notify(0 /* ID of notification */, notificationBuilder.build());
    }
//
//    @Override
//    public void onNewToken(String s) {
//        Log.e("token?", s);
//        super.onNewToken(s);
//    }
//
//    private void makeNotification(RemoteMessage remoteMessage) {
//        try {
//            int notificationId = -1;
//            Context mContext = getApplicationContext();
//
//            Intent intent = new Intent(this, MainActivity.class);
//            intent.setAction(Intent.ACTION_MAIN);
//            intent.addCategory(Intent.CATEGORY_LAUNCHER);
//
//            String title = remoteMessage.getData().get("title");
//            String message = remoteMessage.getData().get("body");
//            String topic = remoteMessage.getFrom();
//
//            NotificationManager notificationManager = (NotificationManager) mContext.getSystemService(Context.NOTIFICATION_SERVICE);
//            NotificationCompat.Builder builder = new NotificationCompat.Builder(this, "10001");
//            if (Build.VERSION.SDK_INT < Build.VERSION_CODES.O) {
//                builder.setVibrate(new long[]{200, 100, 200});
//            }
//            builder.setSmallIcon(R.drawable.ic_splash_logo)
//                    .setAutoCancel(true)
//                    .setDefaults(Notification.DEFAULT_SOUND)
//                    .setContentTitle(title)
//                    .setContentText(message);
//
//            if (topic.equals(topics[0])) {
//                notificationId = 0;
//            } else if (topic.equals(topics[1])) {
//                notificationId = 1;
//            }
//
//            if (notificationId >= 0) {
//                PendingIntent pendingIntent = PendingIntent.getActivity(this, notificationId, intent, PendingIntent.FLAG_CANCEL_CURRENT);
//                builder.setContentIntent(pendingIntent);
//                notificationManager.notify(notificationId, builder.build());
//            }
//
//        } catch (NullPointerException nullException) {
//            Toast.makeText(getApplicationContext(), "알림에 오류가 발생했습니다.", Toast.LENGTH_SHORT).show();
//            Log.e("error Notify", nullException.toString());
//        }
//
//    }
}