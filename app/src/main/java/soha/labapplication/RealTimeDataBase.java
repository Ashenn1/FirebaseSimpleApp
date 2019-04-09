package soha.labapplication;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.messaging.FirebaseMessaging;

import java.util.ArrayList;

public class RealTimeDataBase extends AppCompatActivity {

    ArrayList<String>Messages = new ArrayList<String>();
    private static final String TAG = "RealTimeDataBase";
    ListView myListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_real_time_data_base);

        /*
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference myRef = database.getReference("messages");

        final Button saveButton = findViewById(R.id.button_submit_data);
        myListView = findViewById(R.id.listView_Messages);



        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                EditText msg = (EditText)findViewById(R.id.inputMessage);
                myRef.setValue(msg.getText().toString());
                Messages.add(msg.getText().toString());
                msg.getText().clear();

            }
        });

        ArrayAdapter<String> myMessages = new ArrayAdapter<String>(RealTimeDataBase.this , android.R.layout.simple_list_item_1,Messages);
        myListView.setAdapter(myMessages);


        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                String value = dataSnapshot.getValue(String.class);
                Log.d(TAG, "Value is: " + value);

                ArrayAdapter<String> myMessages = new ArrayAdapter<String>(RealTimeDataBase.this , android.R.layout.simple_list_item_1,Messages);
                myListView.setAdapter(myMessages);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w(TAG, "Failed to read value.", error.toException());
            }
        });


*/




    }



}
