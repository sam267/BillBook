package easyway2in.com.billbook;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class ResetMonth extends AppCompatActivity {
EditText limit;
    TextView head,subHead;
    Button start;
    String Limit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.reset_month);

        head = (TextView) findViewById(R.id.heading);
        subHead = (TextView) findViewById(R.id.subheading);

        limit = (EditText) findViewById(R.id.limit);


        Typeface tf1 = Typeface.createFromAsset(getAssets(),"fonts/ralewaybold.ttf");
        Typeface tf2 = Typeface.createFromAsset(getAssets(), "fonts/raleway.ttf");

        head.setTypeface(tf1);
        subHead.setTypeface(tf2);

        start = (Button) findViewById(R.id.start);

        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Limit = String.valueOf(limit.getText());

                if(Limit.equals("")) {
                    Toast.makeText(getApplicationContext(), "Please Enter Limit", Toast.LENGTH_SHORT).show();

                }
                else {
                    DBHandler db = new DBHandler(getApplicationContext());

                    Calendar c = Calendar.getInstance();
                    SimpleDateFormat df = new SimpleDateFormat("dd-mm-yy");
                    String formattedDate = df.format(c.getTime());

                    db.resetTable_Misc();
                    db.resetTable_Credit();
                    db.resetTable_Debit();
                    db.resetTable_final();

                    db.addAmount(Limit);
                    db.addStartDate(formattedDate);

                    Intent intent = new Intent(getApplicationContext(), MainApp.class);
                    startActivity(intent);
                    overridePendingTransition(R.anim.zoom_in, R.anim.zoom_out);
                    finish();
                }
            }

        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_reset_month, menu);
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
}
