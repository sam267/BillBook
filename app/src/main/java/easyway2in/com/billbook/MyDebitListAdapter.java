package easyway2in.com.billbook;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

/**
 * Created by Sameer1 on 13-08-2016.
 */
public class MyDebitListAdapter extends ArrayAdapter {
    private final Context context;

    private  final String[][] data;
int count = 0;
    public MyDebitListAdapter(Context context,String[][] data) {
        super(context, R.layout.debit_list_item);
        this.context = context;
        this.data = data;
        count = data[0].length;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View rowView = inflater.inflate(R.layout.debit_list_item, parent, false);

        TextView textView1 = (TextView) rowView.findViewById(R.id.amount);
        TextView textView2 = (TextView) rowView.findViewById(R.id.date);
        TextView textView3 = (TextView) rowView.findViewById(R.id.time);

        textView1.setText(data[0][count-1-position]);
        textView2.setText(data[1][count-1-position]);
        textView3.setText(data[2][count-1-position]);

        // Change icon based on name



        return rowView;
    }
    @Override
    public int getCount(){
        return count;
    }
}
