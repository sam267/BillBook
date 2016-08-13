package easyway2in.com.billbook;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;

import android.support.v4.app.Fragment;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;


public class Fragment2 extends Fragment {

EditText amount ;
    TextView debited;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment2, container, false);
        amount = (EditText) v.findViewById(R.id.debit);

        amount.setInputType(InputType.TYPE_CLASS_NUMBER);
        String debit= String.valueOf(amount.getText());
        debited = (TextView) v.findViewById(R.id.debited);

        debited.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String debit= String.valueOf(amount.getText());
            }
        });

        return v;
    }


}
