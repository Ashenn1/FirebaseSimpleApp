package soha.labapplication;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.util.Log;
import android.util.Pair;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.logging.Handler;

import soha.labapplication.MainActivity;
import soha.labapplication.R;

import static android.content.ContentValues.TAG;

public class MyFirebaseMessagingService extends FirebaseMessagingService  {

   private static final String TAG = "MyFirebaseMsgService";


    FirebaseDatabase database = FirebaseDatabase.getInstance();
    final DatabaseReference myRef = database.getReference();
    public MessagesObj msg = new MessagesObj();

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {

        Log.d(TAG , "Message recieved");
        Date date = new Date(remoteMessage.getSentTime());
        Format dateformat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        String dateText = dateformat.format(date);


        // Handle FCM messages here.
        // Not getting messages here? See why this may be: https://goo.gl/39bRNJ
        Log.d(TAG, "From: " + remoteMessage.getFrom());

        // Check if message contains a data payload.
        if (remoteMessage.getData().size() > 0) {
            Log.d(TAG, "Message data payload: " + remoteMessage.getData());

        }

        // Check if message contains a notification/data payload.
        if (remoteMessage.getNotification() != null) {
            Log.d(TAG, "Message Notification Body: " + remoteMessage.getNotification().getBody());
            Log.d(TAG , "DateTime" + remoteMessage.getSentTime());
            if(remoteMessage.getData()!=null){
                Log.d(TAG,"DataPayload : " +"message = "+ remoteMessage.getData() );

                msg.id = myRef.child("messages").push().getKey();
                msg.pair = new Pair<>(dateText , remoteMessage.getNotification().getBody());
                myRef.child("messages").child(msg.id).setValue(msg);

                // Toast.makeText(MyFirebaseMessagingService.this, "Sent to Database", Toast.LENGTH_SHORT).show();
                //myRef.child(remoteMessage.getSentTime()).setValue(remoteMessage.getNotification().getBody());
                 //myRef.push(remoteMessage.getSentTime());

            }



            Intent intent = new Intent(getBaseContext(), MainActivity.class);
            intent.putExtra("Notification Body", remoteMessage.getNotification().getBody() );
            startActivity(intent);
        }

        Log.d(TAG , "Notification is null");


    }



    @Override
    public void onNewToken(String token) {
        Log.d(TAG, "Refreshed token: " + token);

        // If you want to send messages to this application instance or
        // manage this apps subscriptions on the server side, send the
        // Instance ID token to your app server.
        sendRegistrationToServer(token);
    }

    private void sendRegistrationToServer(String token) {
        // TODO: Implement this method to send token to your app server.
        Log.d("Token: " , token);
    }


}
