package com.example.dishe;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link //Timeup#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Timeup extends Fragment {
    private View v;
    private LinearLayout l;
    private Button back;
    private Fragment f;
    private Fragment t;
    private String dish;
    private String stopwatch;
    private TextView e;
    //private static String done[][];
    //private static int num;
    /*static
    {
        done=new String[25][2];
        num=-1;
    }*/

    public Timeup(Fragment f, String dish, String stopwatch, Button back) {
        // Required empty public constructor
      //  num++;
        this.dish=dish;
        this.stopwatch=stopwatch;
        this.back=back;
        this.f=f;
        t=this;
        //LayoutInflater li=getLayoutInflater();
        //setimeupfragment();
        //ExampleFragment myFragment = new ExampleFragment();

    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v=inflater.inflate(R.layout.fragment_timeup, container, false);
        l=(LinearLayout) v.findViewById(R.id.timeup);
        int i=0;

        e=new TextView(l.getContext());
        e.setText(dish+"'s "+stopwatch+" timer is up");
        e.setTextColor(getResources().getColor(R.color.teal_200));
        //e.setTextSize(R.dimen.fontsize);
        e.setTextSize(getResources().getDimension(R.dimen.height));
        e.setGravity(Gravity.CENTER);
        l.addView(e);
        /*while (done[i][0]!=null)
        {
            TextView e;
            e=new TextView(l.getContext());
            e.setText(done[i][0]+"'s "+done[i][1]+" timer is up");
            if (i%2==0) {
                e.setTextColor(getResources().getColor(R.color.teal_200));
            }
            else
            {
                e.setTextColor(getResources().getColor(R.color.pink));
            }
            //e.setTextSize(R.dimen.fontsize);
            e.setTextSize(R.dimen.height);
            e.setGravity(Gravity.CENTER);
            l.addView(e);
        }*/
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //done=new String[25][2];
                //num=-1;

                FragmentManager fra = getActivity().getSupportFragmentManager();
                FragmentTransaction frat = fra.beginTransaction();
                //frat.replace(R.id.mainlay,f);
                frat.remove(t);
                fra.popBackStack();
                frat.commit();
            }
        });
        return v;
    }

}