package soha.labapplication;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;
import com.google.firebase.messaging.FirebaseMessaging;

public class MainActivity extends AppCompatActivity {

    //FCM registeration token
    //token:f8IwZTiZ388:APA91bESIJqXV962OBDBW8FQNJhFVU2aNikViVBfMUfWD4osV_xVwTc8FSI69
    // lv_dZzkQySQ6JhrEguJv50ytQJgDTvN0WElpxUX1snBTYg7ifTXMe254LddJTzDtVbbe5Vfy3iURYm1

    private static final String TAG = "MainActivity";
    private int weatherSubscribeFlag=0;

    // Used to load the 'native-lib' library on application startup.
    static {
        System.loadLibrary("native-lib");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Example of a call to a native method
        //TextView tv = (TextView) findViewById(R.id.messageTextView);
        //tv.setText(stringFromJNI());

        TextView msg = (TextView)findViewById(R.id.messageTextView);
        String notificationBody= getIntent().getStringExtra("Notification Body");

        msg.setText(notificationBody);


        if (getIntent().getExtras() != null) {
            for (String key : getIntent().getExtras().keySet()) {
                Object value = getIntent().getExtras().get(key);
                Log.d(TAG, "Key: " + key + " Value: " + value);
            }
        }

        FirebaseInstanceId.getInstance().getInstanceId()
                .addOnCompleteListener(new OnCompleteListener<InstanceIdResult>() {
                    @Override
                    public void onComplete(@NonNull Task<InstanceIdResult> task) {
                        if (!task.isSuccessful()) {
                            Log.d(TAG, "getInstanceId failed", task.getException());
                            return;
                        }

                        // Get new Instance ID token
                        String token = task.getResult().getToken();

                        // Log and toast
                        //String msg = getString(R.string.msg_token_fmt, token);
                          // for(int i=0;i<400;i++)
                            Log.d(TAG, token);

                       // Toast.makeText(MainActivity.this, token, Toast.LENGTH_LONG).show();
                    }
                });

        final Button DBButton = findViewById(R.id.DBButton);

        DBButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),RealTimeDataBase.class);
                startActivity(i);
            }
        });

        final Button subscribeButton = findViewById(R.id.subscribeButton);
        if(weatherSubscribeFlag==0){
            subscribeButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.d(TAG, "Subscribing to weather topic");
                    // [START subscribe_topics]
                    FirebaseMessaging.getInstance().subscribeToTopic("weather")
                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    String msg = getString(R.string.msg_subscribed);
                                    if (!task.isSuccessful()) {
                                        msg = getString(R.string.msg_subscribe_failed);
                                    }
                                    weatherSubscribeFlag = 1;
                                    Log.d(TAG, msg);
                                    Toast.makeText(MainActivity.this, msg, Toast.LENGTH_SHORT).show();
                                }
                            });
                    // [END subscribe_topics]
                    subscribeButton.setText("Unsubscribe From Weather");
                }
            });

        }

         if (weatherSubscribeFlag == 1){

            subscribeButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.d(TAG, "unsubscribing from weather topic");
                    // [START subscribe_topics]
                    FirebaseMessaging.getInstance().unsubscribeFromTopic("weather")
                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    String msg = getString(R.string.unsubscribe_to_weather);
                                    if (!task.isSuccessful()) {
                                        msg = getString(R.string.msg_subscribe_failed);
                                    }
                                    weatherSubscribeFlag = 0;
                                    Log.d(TAG, msg);
                                    Toast.makeText(MainActivity.this, msg, Toast.LENGTH_SHORT).show();
                                }
                            });
                    // [END subscribe_topics]
                    subscribeButton.setText("Subscribe To Weather");
                }
            });


        }



    }






    /**
     * A native method that is implemented by the 'native-lib' native library,
     * which is packaged with this application.
     */
    public native String stringFromJNI();
}
