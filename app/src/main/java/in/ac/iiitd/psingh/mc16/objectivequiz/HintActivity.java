package in.ac.iiitd.psingh.mc16.objectivequiz;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class HintActivity extends AppCompatActivity {

    private Button mShowHintButton;
    private TextView mHintMessage;
    boolean hint_used = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hint);

        mShowHintButton = (Button) findViewById(R.id.ShowHintButton);
        mShowHintButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view)
            {
                hint_used = true;
                mHintMessage = (TextView) findViewById(R.id.HintMessage);
                String message="Prime numbers are numbers which are divisible by only 1 and the number itself";
                mHintMessage.setText(message);
            }
        });

    }

    @Override
     public void onBackPressed()
    {
        Intent intent = new Intent();
        intent.putExtra("HintUsed", hint_used);
        setResult(QuizActivity.RESULT_OK, intent);
        super.onBackPressed();
    }



}
