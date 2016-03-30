package ro.techstars.chatelectronica;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.client.Firebase;
import com.firebase.ui.FirebaseListAdapter;

import java.util.HashMap;
import java.util.Map;


public class MainActivity extends AppCompatActivity {


    private String TAG="Horia";
    private Firebase mFireBaseRef;
    private ListView listView;
    private Button lgIN;
    FirebaseListAdapter<ChatMessage> mListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Firebase.setAndroidContext(this);

        mFireBaseRef = new Firebase("https://chatelectronica3.firebaseio.com");
        lgIN= (Button) findViewById(R.id.login);
        final EditText textEdit = (EditText) this.findViewById(R.id.text_edit);
        Button sendButton = (Button) this.findViewById(R.id.send_button);

        listView = (ListView) this.findViewById(android.R.id.list);
        mListAdapter = new FirebaseListAdapter<ChatMessage>(this, ChatMessage.class,
                android.R.layout.two_line_list_item, mFireBaseRef) {
            @Override
            protected void populateView(View v, ChatMessage model, int position) {
                Log.e(TAG, "populateView:"+position);

                ((TextView)v.findViewById(android.R.id.text1)).setText(model.getName());
                ((TextView)v.findViewById(android.R.id.text2)).setText(model.getText());


            }
        };

        lgIN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this,LoginActivity.class);
                startActivity(i);
            }
        });

        if (sendButton != null) {
            final SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);

                sendButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String text = textEdit.getText().toString();
                        Map<String, Object> values = new HashMap<>();
                        values.put("name", "Android User");
                        values.put("text", text);
                        if(prefs.getBoolean("login",false)) {
                            Toast.makeText(MainActivity.this, "You are not LogedIn please do befeore sending a message", Toast.LENGTH_SHORT).show();
                        }else {
                            mFireBaseRef.push().setValue(values);
                            textEdit.setText("");
                        }
                    }
                });

        }

       if(listView!=null && mListAdapter!= null) {
            listView.setAdapter(mListAdapter);
        }
        else {

            Log.e("Horia","Mesaj");

        }

    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        mListAdapter.cleanup();
    }

}
