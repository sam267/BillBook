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
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;


public class Fragment1 extends Fragment {
    EditText credit ;
    TextView credited;
    ListView list1;
    /*Button undo;*/

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment1, container, false);
        credit = (EditText) v.findViewById(R.id.credit);
        /*undo = (Button) v.findViewById(R.id.undo);*/

        credit.setInputType(InputType.TYPE_CLASS_NUMBER);
        list1 = (ListView) v.findViewById(R.id.CreditList);

        final DBHandler db = new DBHandler(getActivity().getApplicationContext());
        Log.d("rowCount", String.valueOf((db.getCreditRowCount())));
if(db.getCreditRowCount()>0) {
    String initial[][] = db.getCredit();



    MyCreditListAdapter adapter1 = new MyCreditListAdapter(getActivity().getApplicationContext(), initial);
    list1.setAdapter(adapter1);
}

        credited = (TextView) v.findViewById(R.id.credited);

        credited.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String creditAmount= String.valueOf(credit.getText());

                if(creditAmount.equals("")) {
                credit.setText("");
                    Toast.makeText(getActivity().getApplicationContext(), "Enter Credit Amount", Toast.LENGTH_SHORT).show();
                }
                else{

                    Calendar c = Calendar.getInstance();
                    SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yy");
                    SimpleDateFormat tf = new SimpleDateFormat("HH:mm");
                    String formattedDate = df.format(c.getTime());
                    String formattedTime = tf.format(c.getTime());
                    credit.setText("");

                    db.addCredit(creditAmount, formattedDate, formattedTime);

                    Float credit = Float.valueOf(db.getCreditAmount());
                    Float debit = Float.valueOf(db.getDebitAmount());
                    Float amount = Float.valueOf(db.getAmount());


                    amount = amount + credit;

                    Float Balance = amount - debit;
                    String balance = String.valueOf(Balance);

                    Log.d("balance", balance);

                    db.updateBalance(String.valueOf(amount), balance);

                    String data[][] = db.getCredit();

                    MyCreditListAdapter adap2 = new MyCreditListAdapter(getActivity().getApplicationContext(), data);
                    list1.setAdapter(adap2);


                }




            }
        });
        /*undo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String time = db.getLastCreditTime();
                db.deleteLastCreditRecord(time);


                Float debit = Float.valueOf(db.getDebitAmount());
                Float amount = Float.valueOf(db.getAmount());


                Float Balance = amount - debit;
                String balance = String.valueOf(Balance);

                Log.d("balance", balance);

                db.updateBalance(String.valueOf(amount), balance);

                String data[][] = db.getCredit();


                MyCreditListAdapter adap2 = new MyCreditListAdapter(getActivity().getApplicationContext(), data);
                list1.setAdapter(adap2);
            }
        });*/
        return v;
    }
}
