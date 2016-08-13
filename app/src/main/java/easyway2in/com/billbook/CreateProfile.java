package easyway2in.com.billbook;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class CreateProfile extends AppCompatActivity {
    TextView head,subHead;
    Button login;
    EditText name,dob,email, profession;
    String Name,DOB, Email, Profession;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_profile);

        head = (TextView) findViewById(R.id.heading);
        subHead = (TextView) findViewById(R.id.subheading);

        name = (EditText) findViewById(R.id.name);
        dob = (EditText) findViewById(R.id.dob);
        email = (EditText) findViewById(R.id.email);
        profession = (EditText) findViewById(R.id.profession);


        Typeface tf1 = Typeface.createFromAsset(getAssets(),"fonts/ralewaybold.ttf");
        Typeface tf2 = Typeface.createFromAsset(getAssets(), "fonts/raleway.ttf");

        head.setTypeface(tf1);
        subHead.setTypeface(tf2);

        login = (Button) findViewById(R.id.login);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Name = String.valueOf(name.getText());
                DOB = String.valueOf(dob.getText());
                Email = String.valueOf(email.getText());
                Profession = String.valueOf(profession.getText());


                Intent intent = new Intent(getApplicationContext(),MainApp.class);
                startActivity(intent);
                overridePendingTransition(R.anim.zoom_in, R.anim.zoom_out);
                finish();
            }
        });
    }

}
