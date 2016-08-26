package in.ac.iiitd.psingh.mc16.objectivequiz;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.Random;

public class QuizActivity extends AppCompatActivity {

    private Button mTrueButton;
    private Button mFalseButton;
    private Button mNext_Button;
    private TextView mPrimeNumber;
    private TextView mCheatTaken;
    private TextView mHintTaken;
    private String correct="Correct Answer";
    private String wrong="Wrong Answer";
    private String State_Number="Current Number";
    public String Utsav_message="MESSAGE";
    static final int Cheat_Request = 1;
    static final int Hint_Request = 2;

    int prime;
    Random rand = new Random();

    private static final String TAG = "QuizActivity";

    public boolean check_prime(int prime_number)
    {
        if(prime_number<=3)
            return true;
        for(int i=2;i<=(prime_number/2);i++)
        {
            if(prime_number%i==0)
                return false;
        }
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "Inside OnCreate");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);


        mTrueButton = (Button) findViewById(R.id.TrueButton);
        mTrueButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Context context=getApplicationContext();
                Log.d(TAG, "Clicked True");
                if(check_prime(prime))
                {
                    Toast toast = Toast.makeText(context,correct,Toast.LENGTH_SHORT);
                    toast.show();
                }
                else
                {
                    Toast toast = Toast.makeText(context,wrong,Toast.LENGTH_SHORT);
                    toast.show();
                }
                prime=rand.nextInt(999)+2;
                mCheatTaken = (TextView) findViewById(R.id.CheatTaken);
                mCheatTaken.setText("");
                mHintTaken = (TextView) findViewById(R.id.HintTaken);
                mHintTaken.setText("");
                mPrimeNumber.setText(Integer.toString(prime));

            }
        });

        mFalseButton= (Button) findViewById(R.id.FalseButton);
        mFalseButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Context context = getApplicationContext();
                Log.d(TAG, "Clicked False");
                if(check_prime(prime))
                {
                    Toast toast = Toast.makeText(context,wrong,Toast.LENGTH_SHORT);
                    toast.show();
                }
                else
                {
                    Toast toast = Toast.makeText(context,correct,Toast.LENGTH_SHORT);
                    toast.show();
                }
                prime=rand.nextInt(999)+2;
                mCheatTaken = (TextView) findViewById(R.id.CheatTaken);
                mCheatTaken.setText("");
                mHintTaken = (TextView) findViewById(R.id.HintTaken);
                mHintTaken.setText("");
                mPrimeNumber.setText(Integer.toString(prime));
            }
        });

        mNext_Button= (Button) findViewById(R.id.next_button);
        mNext_Button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view)
            {
                prime=rand.nextInt(999)+2;
                mCheatTaken = (TextView) findViewById(R.id.CheatTaken);
                mCheatTaken.setText("");
                mHintTaken = (TextView) findViewById(R.id.HintTaken);
                mHintTaken.setText("");
                mPrimeNumber.setText(Integer.toString(prime));
                Log.d(TAG, "Clicked Next");
            }
        });
        if(savedInstanceState!=null)
        {
            prime=savedInstanceState.getInt(State_Number);
        }
        else
        {
            prime = rand.nextInt(999) + 2;
        }
        mPrimeNumber= ((TextView) findViewById(R.id.PrimeNumber));
        if(mPrimeNumber!=null)
            mPrimeNumber.setText(Integer.toString(prime));
        else
            Log.d(TAG," It's Null");
    }

    public void hintClicked(View view)
    {
        Intent intent = new Intent(this, HintActivity.class);
        startActivityForResult(intent, Hint_Request);
    }

    public void cheatClicked(View view)
    {
        Intent intent = new Intent(this, CheatActivity.class);
        boolean val = check_prime(prime);
        intent.putExtra(Utsav_message,val);
        intent.putExtra("prime", prime);
        startActivityForResult(intent, Cheat_Request);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        Log.d(TAG,"Inside onActivityResult");
        if(requestCode == Cheat_Request)
        {
            if(resultCode == RESULT_OK)
            {
                if(data.getExtras().getBoolean("CheatUsed") == true)
                {
                    mCheatTaken = (TextView) findViewById(R.id.CheatTaken);
                    mCheatTaken.setText("You have used CHEAT!");
                }
            }
        }
        if(requestCode == Hint_Request)
        {
            if(resultCode == RESULT_OK)
            {
                if(data.getExtras().getBoolean("HintUsed") == true)
                {
                    mHintTaken = (TextView) findViewById(R.id.HintTaken);
                    mHintTaken.setText("You have used HINT!");
                }
            }
        }
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState){

        savedInstanceState.putInt(State_Number,prime);
        super.onSaveInstanceState(savedInstanceState);
        Log.i(TAG, "Inside onSaveInstance");
    }

    @Override
    public void onStart()
    {
        super.onStart();
        Log.d(TAG, "Inside OnStart");
    }

    @Override
    public void onPause()
    {
        super.onPause();
        Log.d(TAG,"Inside OnPause");
    }

    @Override
    public void onResume(){
        super.onResume();
        Log.d(TAG,"Inside OnResume");

    }

    @Override
    public void onStop(){
        super.onStop();
        Log.d(TAG, "Inside OnStop");
    }

    @Override
    public void onDestroy(){
        super.onDestroy();
        Log.d(TAG, "Inside OnDestroy");
    }
}
//android:configChanges="orientation|keyboardHidden|screenSize"