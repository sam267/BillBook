package easyway2in.com.billbook;


import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;

import android.support.v4.app.Fragment;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.text.style.RelativeSizeSpan;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;



public class Fragment3 extends Fragment {

    PieChart mChart;
    String BALANCE;
    String LIMIT;
    Float amount_spent,amount_left,balance,spent_percentage,left_percentage;

    TextView remaining;
    int days_left;

    public Fragment3() {

    }

    public Fragment newInstance() {
        return new Fragment3();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v =inflater.inflate(R.layout.fragment3, container, false);

        mChart = (PieChart) v.findViewById(R.id.chart1);
        mChart.setDescription("BALANCE");
        mChart.setCenterTextSize(10f);

        remaining = (TextView) v.findViewById(R.id.days_remaining);
        addDaysRemaining();

        // radius of the center hole in percent of maximum radius
        mChart.setHoleRadius(45f);
        mChart.setTransparentCircleRadius(50f);

        Legend l = mChart.getLegend();
        l.setPosition(Legend.LegendPosition.RIGHT_OF_CHART);

        addData();

        return v;
    }

    private void addData() {
        DBHandler db = new DBHandler(getActivity().getApplicationContext());

        BALANCE = db.getBalance();
        LIMIT = db.getAmount();


        amount_spent = Math.abs(Float.valueOf(LIMIT) - Float.valueOf(BALANCE));
        amount_left = Float.valueOf(BALANCE);
        balance = Float.valueOf(BALANCE);

        spent_percentage = (amount_spent /(amount_spent + amount_left)) * 100;
        left_percentage = (amount_left /(amount_spent + amount_left)) * 100;

        float[] yData = { spent_percentage , left_percentage };
        String[] xData = { "Amount Spent :(", "Amount Left :)" };


        List<PieEntry> yVals1 = new ArrayList<PieEntry>();
        mChart.setCenterText(generateCenterText());

        for (int i = 0; i < yData.length; i++)
            yVals1.add(new PieEntry(yData[i], i));

        ArrayList<String> xVals = new ArrayList<String>();

        for (int i = 0; i < xData.length; i++)
            xVals.add(xData[i]);

        // create pie data set
        PieDataSet dataSet = new PieDataSet(yVals1, "Expenditure");
        dataSet.setSliceSpace(3);
        dataSet.setSelectionShift(5);

        // add many colors
        ArrayList<Integer> colors = new ArrayList<Integer>();

        for (int c : ColorTemplate.VORDIPLOM_COLORS)
            colors.add(c);

        for (int c : ColorTemplate.JOYFUL_COLORS)
            colors.add(c);

        for (int c : ColorTemplate.COLORFUL_COLORS)
            colors.add(c);

        for (int c : ColorTemplate.LIBERTY_COLORS)
            colors.add(c);

        for (int c : ColorTemplate.PASTEL_COLORS)
            colors.add(c);

        colors.add(ColorTemplate.getHoloBlue());
        dataSet.setColors(colors);

        // instantiate pie data object now
        PieData data = new PieData(dataSet);
        data.setValueFormatter(new PercentFormatter());
        data.setValueTextSize(11f);
        data.setValueTextColor(Color.GRAY);

        mChart.setData(data);

        // undo all highlights
        mChart.highlightValues(null);

        // update pie chart
        mChart.invalidate();
    }

    private PieData generatePieData() {
        
        return null;
    }

    private SpannableString generateCenterText() {
        DBHandler db = new DBHandler(getActivity().getApplicationContext());

        BALANCE = db.getBalance();
        balance = Float.valueOf(BALANCE);
        SpannableString s = new SpannableString("Balance\n" + String.valueOf(balance));
        s.setSpan(new RelativeSizeSpan(2f), 0, 8, 0);
        s.setSpan(new ForegroundColorSpan(Color.GRAY), 8, s.length(), 0);
        return s;
    }

    private void addDaysRemaining(){
        Calendar c = Calendar.getInstance();
        SimpleDateFormat df = new SimpleDateFormat("dd-mm-yy");
        String formattedDate = df.format(c.getTime());
        DBHandler db = new DBHandler(getActivity().getApplicationContext());

        String startDate = db.getStartDate();
        String today[] = formattedDate.split("-", 2);
        String start[] = startDate.split("-", 2);
        Log.d("check",today[0]);
        Log.d("check", start[0]);
        int begin = Integer.valueOf(start[0]);
        int present = Integer.valueOf(today[0]);

        if(present>=begin)
            days_left = 30 - (present - begin);
        else days_left = 30 - (present + 30 - begin);

        Log.d("check", String.valueOf(days_left));

        remaining.setText(String.valueOf(days_left)+" days remaining...");


        if(days_left==0){
            Intent intent = new Intent(getActivity().getApplicationContext(), ResetMonth.class);
            startActivity(intent);
            getActivity().overridePendingTransition(R.anim.zoom_in, R.anim.zoom_out);
            getActivity().finish();
        }


    }

    @Override
    public void onResume(){
        super.onResume();
        addData();
        addDaysRemaining();
    }

}
