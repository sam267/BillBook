package easyway2in.com.billbook;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;

import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;


public class IntroFragment3 extends Fragment {
    TextView mainStart,text;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v=inflater.inflate(R.layout.fragment_intro_fragment3, container, false);

        mainStart = (TextView) v.findViewById(R.id.MainStart);
        Animation animation1 = AnimationUtils.loadAnimation(getActivity().getApplicationContext(), R.anim.get_started);
        mainStart.startAnimation(animation1);

        text = (TextView) v.findViewById(R.id.text);

        Typeface tf = Typeface.createFromAsset(getActivity().getAssets(), "fonts/raleway.ttf");
        text.setTypeface(tf);

        mainStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getActivity().getApplicationContext(),CreateProfile.class);
                startActivity(intent);
                getActivity().overridePendingTransition(R.anim.zoom_in, R.anim.zoom_out);
                getActivity().finish();
            }
        });

        return v;
    }



}
