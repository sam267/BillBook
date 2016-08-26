package easyway2in.com.billbook;

import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.NotificationCompat;
import android.text.InputType;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class SignIn extends AppCompatActivity {

    TextView head, subHead;
    TextView create_new_profile, cancel, log_in;
    EditText username, password;
    String Username, Password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_in);




        head = (TextView) findViewById(R.id.heading1);
        subHead = (TextView) findViewById(R.id.subheading1);
        Typeface tf1 = Typeface.createFromAsset(getAssets(),"fonts/ralewaybold.ttf");
        Typeface tf2 = Typeface.createFromAsset(getAssets(), "fonts/raleway.ttf");

        head.setTypeface(tf1);
        subHead.setTypeface(tf2);

        create_new_profile = (TextView) findViewById(R.id.create_profile);
        cancel = (TextView) findViewById(R.id.cancel);
        log_in = (TextView) findViewById(R.id.login);

        username = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);

        username.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);

        create_new_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getApplicationContext(),CreateProfile.class);
                startActivity(intent);
                overridePendingTransition(R.anim.zoom_in, R.anim.zoom_out);
                finish();
            }
        });
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        log_in.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Username = String.valueOf(username.getText());
                Password = String.valueOf(password.getText());


                if(Password.length()!=4 || !isNumeric(Password)) {
                    Toast.makeText(getApplicationContext(),"Enter 4-digit Pin",Toast.LENGTH_SHORT).show();
                    password.setText("");
                }
                Password = String.valueOf(password.getText());
                DBHandler db = new DBHandler(getApplicationContext());

                if(Username.equals("") || Password.equals("")) {
                    Toast.makeText(getApplicationContext(), "Please Enter Your Details", Toast.LENGTH_SHORT).show();
                }

                else if(db.getRecordsRowCount()==0) Toast.makeText(getApplicationContext(), "No User Exists. Create new Account to Login", Toast.LENGTH_SHORT).show();
                else  {

                    String[][] actual_details = db.getLoginDetails();
                    if(Username.equals(actual_details[0][0]) && Password.equals(actual_details[1][0])){
                        Intent intent = new Intent(getApplicationContext(), MainApp.class);
                        startActivity(intent);
                        overridePendingTransition(R.anim.zoom_in, R.anim.zoom_out);
                        finish();
                    }
                    else {
                        Toast.makeText(getApplicationContext(), "Invalid Username or Password", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_sign_in, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    private boolean isNumeric(String str)
    {
        for (char c : str.toCharArray())
        {
            if (!Character.isDigit(c)) return false;
        }
        return true;
    }
}
