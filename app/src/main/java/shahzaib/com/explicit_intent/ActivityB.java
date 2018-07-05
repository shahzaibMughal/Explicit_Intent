package shahzaib.com.explicit_intent;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class ActivityB extends AppCompatActivity {
    TextView textView;
    public static final String KEY_INTENT_ACTIVITY_B_DATA = "myData";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_b);

        if ( getIntent() != null)
        {
            Intent intent = getIntent();
            String dataReceived = intent.getStringExtra(ActivityA.KEY_INTENT_ACTIVITY_A_DATA);
            textView = findViewById(R.id.TV_in_activity_B);
            if(dataReceived!=null) textView.setText(dataReceived);
        }




    }

    public void sendDataBackToActivityA(View view) {
        EditText editText = findViewById(R.id.activityB_ET);
        if(editText.getText().toString().length()>0)
        {
            String data = editText.getText().toString();
            editText.setText("");
            Intent intent = new Intent();
            intent.putExtra(KEY_INTENT_ACTIVITY_B_DATA,data);
            setResult(RESULT_OK,intent);
            super.finish();
        }
        else
        {
            Toast.makeText(this, "Enter Data, First!", Toast.LENGTH_SHORT).show();
        }
    }
}
