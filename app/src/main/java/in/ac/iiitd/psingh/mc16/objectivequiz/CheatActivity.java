package in.ac.iiitd.psingh.mc16.objectivequiz;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class CheatActivity extends AppCompatActivity {

    private Button mShowCheatButton;
    private TextView mCheatMessage;
    private String cheat1="Is ";
    private String cheat2=" a Prime?";
    private String string;
    boolean cheat_used = false;
    private static final String TAG = "CheatActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cheat);
        Intent intent = getIntent();
        boolean val = intent.getExtras().getBoolean("MESSAGE");
        int prime = intent.getIntExtra("prime",0);
        string= cheat1+ prime + cheat2;
        if(val)
        {
            string = string + ": Yes";
        }
        else
        {
            string = string + ": No";
        }
        mShowCheatButton = (Button) findViewById(R.id.ShowCheatButton);

        mShowCheatButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cheat_used = true;
                mCheatMessage = (TextView) findViewById(R.id.CheatMessage);
                mCheatMessage.setText(string);
            }
        });

    }

    @Override
    public void onBackPressed()
    {
        Log.d(TAG,"Inside onBackPressed");
        Intent intent = new Intent();
        intent.putExtra("CheatUsed",cheat_used);
        setResult(QuizActivity.RESULT_OK, intent);
        super.onBackPressed();
    }


}
