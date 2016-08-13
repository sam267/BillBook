package easyway2in.com.billbook;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;

import android.text.InputType;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.support.v4.app.Fragment;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;


public class Fragment1 extends Fragment {
    EditText amount ;
    TextView credited;
    ListView list;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment1, container, false);
        amount = (EditText) v.findViewById(R.id.credit);

        amount.setInputType(InputType.TYPE_CLASS_NUMBER);
        list = (ListView) v.findViewById(R.id.CreditList);

        credited = (TextView) v.findViewById(R.id.credited);

        credited.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String credit= String.valueOf(amount.getText());
                DBHandler db = new DBHandler(getActivity().getApplicationContext());

                Calendar c = Calendar.getInstance();
                SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy");
                String date = df.format(c.getTime());
                db.addCredit(credit,date, String.valueOf(System.currentTimeMillis()));

                String data[][] =  db.getCredit();

                for (int i=0;i<data[0].length;i++)
                {
                    Log.d("credit",data[0][i]);
                    Log.d("date",data[1][i]);
                    Log.d("time",data[2][i]);
                }

            }
        });

        return v;
    }
}
