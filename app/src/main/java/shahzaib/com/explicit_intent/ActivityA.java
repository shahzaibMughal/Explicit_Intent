package shahzaib.com.explicit_intent;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class ActivityA extends AppCompatActivity {
    // TODO: ActivityA will start ActivityB  & also pass data
    // TODO: Then ActivityB will return data to ActivityA

    public static final int REQUEST_CODE = 10;
    public static final String KEY_INTENT_ACTIVITY_A_DATA = "myMessage";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(getIntent() != null)
        {
            Intent intent = getIntent();
            String data = intent.getStringExtra(ActivityB.KEY_INTENT_ACTIVITY_B_DATA);
            TextView textView = findViewById(R.id.TV_in_activity_A);
            if(data!=null) textView.setText(data);
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == REQUEST_CODE)
        {
            if(data == null ) return;
           String dataReceived = data.getStringExtra(ActivityB.KEY_INTENT_ACTIVITY_B_DATA);
           if(dataReceived != null)
           {
               TextView textView = findViewById(R.id.TV_in_activity_A);
               if(dataReceived.length()>0) textView.setText(dataReceived);
           }
        }
    }



    public void sendDataToActivityB(View view) {
        EditText editText = findViewById(R.id.editText);
        if(editText.getText().toString().length()>0)
        {
            Intent intent = new Intent(this,ActivityB.class);
            intent.putExtra(KEY_INTENT_ACTIVITY_A_DATA,editText.getText().toString());
            editText.setText("");
            startActivityForResult(intent,REQUEST_CODE); // this will start ActivityA as a parent Activity of ActivityB
        }
        else
        {
            Toast.makeText(this, "Enter Data, First!", Toast.LENGTH_SHORT).show();
        }
    }
}
