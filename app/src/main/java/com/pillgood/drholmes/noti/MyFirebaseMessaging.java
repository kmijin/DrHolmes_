package com.pillgood.drholmes.noti;

import static android.content.ContentValues.TAG;

import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.util.Log;
import android.widget.RemoteViews;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import com.pillgood.drholmes.MainActivity;
import com.pillgood.drholmes.R;
import com.pillgood.drholmes.home.PillModel;

import java.util.ArrayList;
import java.util.Calendar;

public class MyFirebaseMessaging extends FirebaseMessagingService {


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

    private FirebaseFirestore db;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private ArrayList<PillModel> arrayList;
    private ProgressDialog progressDialog;



    @Override
    public void onNewToken(@NonNull String token) {
        super.onNewToken(token);
    }

    @Override
    public void onMessageReceived(@NonNull RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);
    }

    private void EventChangeListener() {
        db.collection("Pills")
                .orderBy("pillName", Query.Direction.ASCENDING)
                .addSnapshotListener(new EventListener<QuerySnapshot>() {
                    @Override
                    public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                        if(error != null) {
                            if(progressDialog.isShowing()){
                                progressDialog.dismiss();
                            }
                            Log.e("Firestore error", error.getMessage());
                            return;
                        }
                        for (DocumentChange dc : value.getDocumentChanges()) {
                            if (dc.getType() == DocumentChange.Type.ADDED) {
                                arrayList.add(dc.getDocument().toObject(PillModel.class));
                            }
                            adapter.notifyDataSetChanged();
                            if (progressDialog.isShowing()) {
                                progressDialog.dismiss();
                            }
                        }
                    }
                });
    }

    private void sendNotification(String messageBody) {
        //?????? ????????? ????????? ???????????? (PendingIntent)
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

        //?????? ???????????? ?????? ?????? ?????????
        NotificationManager notificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        // ????????? ?????? ???????????? channelId ?????? ????????? ???
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
//            Toast.makeText(getApplicationContext(), "????????? ????????? ??????????????????.", Toast.LENGTH_SHORT).show();
//            Log.e("error Notify", nullException.toString());
//        }
//
//    }
//    void diaryNotification(Calendar calendar)
//    {
//        Log.d(TAG, "## AlarmManager ## ");
//
//        Boolean dailyNotify = true; // ?????? ???????????? ????????? ?????? ??????(????????????)
//
//        PackageManager pm = this.getPackageManager();
//        //ComponentName receiver = new ComponentName(this, DeviceBootReceiver.class);
//        Intent alarmIntent = new Intent(this, AlertReceiver.class);
//        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 0, alarmIntent, 0);
//        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
//
//
//        // ?????? ????????? ?????? ??????
//        if (dailyNotify) {
//            if (alarmManager != null) {
//                alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), AlarmManager.INTERVAL_DAY, pendingIntent);
//
//                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
//                    alarmManager.setExactAndAllowWhileIdle(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pendingIntent);
//                }
//            }
//
//            // ?????? ??? ???????????? ????????? ?????????????????? ??????
//            //pm.setComponentEnabledSetting(receiver, PackageManager.COMPONENT_ENABLED_STATE_ENABLED, PackageManager.DONT_KILL_APP);
//
//        }
//
//    }
}