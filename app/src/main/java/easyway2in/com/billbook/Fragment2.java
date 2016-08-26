package easyway2in.com.billbook;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;

import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.text.InputType;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;


public class Fragment2 extends Fragment {

    EditText debit ;
    FloatingActionButton debited;
    /*Button undo;*/

    ListView list;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment2, container, false);
        debit = (EditText) v.findViewById(R.id.debit);
      /*  undo = (Button) v.findViewById(R.id.undo);*/

        debit.setInputType(InputType.TYPE_CLASS_NUMBER);
        list = (ListView) v.findViewById(R.id.DebitList);

        final DBHandler db = new DBHandler(getActivity().getApplicationContext());
        Float amount = Float.valueOf(db.getAmount());
        Float balance = Float.valueOf(db.getBalance());

        if(balance<(0.2*amount)){
            AlertDialog ad = new AlertDialog.Builder(this.getActivity())
                    .create();
            ad.setCancelable(false);
            ad.setTitle("Warning!!");
            ad.setMessage("You are close to spending all of your total amount, be careful :)");
            ad.setButton(this.getActivity().getString(R.string.ok), new DialogInterface.OnClickListener() {

                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            });
            ad.show();
        }

if(db.getDebitRowCount()>0) {
    String data[][] = db.getDebit();


    MyDebitListAdapter adapter2 = new MyDebitListAdapter(getActivity().getApplicationContext(), data);
    list.setAdapter(adapter2);
}

        debited = (FloatingActionButton) v.findViewById(R.id.debited);
        debited.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#fa2e2e")));
        debited.setRippleColor(Color.parseColor("#a91f1f"));


        debited.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String debitAmount= String.valueOf(debit.getText());



                if(debitAmount.equals("")) {
                    debit.setText("");
                    Toast.makeText(getActivity().getApplicationContext(), "Enter Debit Amount", Toast.LENGTH_SHORT).show();
                }
                else{
                    /*Float debit1 = Float.valueOf(db.getDebitAmount());*/
                    Float amount1 = Float.valueOf(db.getAmount());
                    Float debit1 = Float.valueOf(db.getDebitAmount());

                    Float debitA = Float.valueOf(debitAmount);
                    if((amount1 - debit1 - debitA)<0) {
                        Toast.makeText(getActivity().getApplicationContext(), "Can't spend more than you have", Toast.LENGTH_SHORT).show();
                        debit.setText("");
                    }
                    else {

                        Calendar c = Calendar.getInstance();
                        SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yy");
                        SimpleDateFormat tf = new SimpleDateFormat("HH:mm");
                        String formattedDate = df.format(c.getTime());
                        String formattedTime = tf.format(c.getTime());
                        debit.setText("");

                        db.addDebit(debitAmount, formattedDate, formattedTime);


                        Float debit = Float.valueOf(db.getDebitAmount());
                        Float amount = Float.valueOf(db.getAmount());

                        Float Balance = amount - debit;

                        String balance = String.valueOf(Balance);

                        Log.d("balance", balance);
                        db.updateBalance(String.valueOf(amount), balance);


                        String data[][] = db.getDebit();

                        MyDebitListAdapter adap2 = new MyDebitListAdapter(getActivity().getApplicationContext(), data);
                        list.setAdapter(adap2);

                        if(Float.valueOf(balance)<(0.2*amount)){

                            AlertDialog ad = new AlertDialog.Builder(getActivity())
                                    .create();
                            ad.setCancelable(false);
                            ad.setTitle("Warning!!");
                            ad.setMessage("You have spent more than 80% of your total amount, be careful :)");
                            ad.setButton(getActivity().getString(R.string.ok), new DialogInterface.OnClickListener() {

                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            });
                            ad.show();
                        }
                    }


                }


            }
        });


        return v;
    }


}
