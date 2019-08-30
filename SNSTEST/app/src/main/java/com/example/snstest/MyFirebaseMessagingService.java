package com.example.snstest;

import android.util.Log;

import android.content.Context;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import com.example.snstest.MyWorker;

import androidx.annotation.NonNull;
import androidx.work.OneTimeWorkRequest;
import androidx.work.WorkManager;
import androidx.work.Worker;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import android.app.Notification;

public class MyFirebaseMessagingService extends FirebaseMessagingService {

    private static final String TAG = "MainActivity";

    /**
     * Called if InstanceID token is updated. This may occur if the security of
     * the previous token had been compromised. Note that this is called when the InstanceID token
     * is initially generated so this is where you would retrieve the token.
     */
    @Override
    public void onNewToken(String token) {
        Log.d(TAG, "Refreshed token: " + token);

        // If you want to send messages to this application instance or
        // manage this apps subscriptions on the server side, send the
        // Instance ID token to your app server.
        // sendRegistrationToServer(token);
    }

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {

        Log.d(TAG, "Receive MSG From: " + remoteMessage.getFrom());
        if (remoteMessage.getData().size() > 0) {
            Log.d(TAG, "Message data payload: " + remoteMessage.getData());
        }

        Log.d(TAG, "Receive MSG From: " + remoteMessage.getData().get("notification"));
        Log.d(TAG, "Receive MSG From: " + remoteMessage.getData().get("body"));

        String channelId ="";
        Notification notification = new NotificationCompat.Builder(this, channelId)
                .setContentTitle(remoteMessage.getData().get("notification"))
                .setContentText(remoteMessage.getData().get("body"))
                .setSmallIcon(R.mipmap.ic_launcher)
                .build();
        NotificationManagerCompat manager = NotificationManagerCompat.from(getApplicationContext());
        manager.notify(123, notification);

        // TODO(developer): Handle FCM messages here.
        // Not getting messages here? See why this may be: https://goo.gl/39bRNJ
        Log.d(TAG, "*****Notification Received From******: " + remoteMessage.getFrom());

        // Check if message contains a data payload.
//        if (remoteMessage.getData().size() > 0) {
//            Log.d(TAG, "****Message data payload****: " + remoteMessage.getData());
//
//            if (/* Check if data needs to be processed by long running job */ true) {
//                // For long-running tasks (10 seconds or more) use WorkManager.
//                scheduleJob();
//                // Log.d(TAG, "****In scheduleJob Now ****: ");
//                //handleNow();
//            } else {
//                // Handle message within 10 seconds
//                Log.d(TAG, "****In handle Now ****: ");
//                handleNow();
//            }
//
//        }
        if (remoteMessage.getNotification() != null) {
            Log.d(TAG, "Message Notification Body: " + remoteMessage.getNotification().getBody());
            // handleNow();
        }
    }

//    private void scheduleJob() {
//
//        //Context context;
//
//        Log.d(TAG, "****Start scheduleJob Now ****: ");
//        // workManager = WorkManager.getInstance(context);
//        // [START dispatch_job]
//        OneTimeWorkRequest work = new OneTimeWorkRequest.Builder(MyWorker.class)
//                .build();
//         WorkManager.getInstance(this).beginWith(work).enqueue();
//        Log.d(TAG, "****END scheduleJob Now ****: ");
//        // [END dispatch_job]
//    }

    /**
     * Handle time allotted to BroadcastReceivers.
     */
//    private void handleNow() {
//        Log.d(TAG, "Short lived task is done.");
//    }

//    private void sendRegistrationToServer(String token) {
//        Log.d(TAG, "Token :" + token);
//    }
}

