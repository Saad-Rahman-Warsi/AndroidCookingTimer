package com.example.dishe;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import java.io.IOException;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link //Dish#//newInstance} factory method to
 * create an instance of this fragment.
 */
public class Dish extends Fragment {

    private View v;
    private Button back;
    private Button addish;
    private Fragment f;
    private LinearLayout frame;
    private static Dishdef dishes[];
    private static int num;
    private MainActivity ma;
    private static boolean onceread;
    static {
        dishes=new Dishdef[10];
        num=0;
        onceread=false;
    }
    public Dish(MainActivity ma,Button back) {
        // Required empty public constructor
        this.back=back;
        f=this;
        this.ma=ma;
        if (onceread==false) {
            readdishfromfile();
            onceread=true;
        }
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v=inflater.inflate(R.layout.fragment_dish, container, false);
        addish=(Button) v.findViewById(R.id.butt1);
        frame=(LinearLayout) v.findViewById(R.id.lay2);
        for (int i=0;i<num;i++)
        {
            Button b;
            b=new Button(frame.getContext());
            b.setText(dishes[i].getName());
            b.setBackgroundColor(getResources().getColor(R.color.pink));
            b.setAllCaps(false);
            FrameLayout.LayoutParams pam= new FrameLayout.LayoutParams(-1,-2) ;
            pam.setMargins(20,0,20,50);
            b.setTextColor(getResources().getColor(R.color.dark_green));
            StopwatchFragment sf=new StopwatchFragment(ma,dishes[i],back);
            b.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    FragmentManager fra=getActivity().getSupportFragmentManager();
                    FragmentTransaction frat=fra.beginTransaction();
                    /*Query q;
                    q=new Query(back,f);
                    frat.replace(R.id.lay,q);
                    frat.commit();*/

                    frat.replace(R.id.mainlay,sf);
                    frat.addToBackStack("back");
                    frat.commit();
                }
            });
            frame.addView(b,pam);
        }
        if (this.getArguments()!=null) {

            Bundle bundle = this.getArguments();
            String s = bundle.getString("querydish");
            if (s != null) {
                Button b;
                b = new Button(frame.getContext());
                b.setText(s);
                b.setBackgroundColor(getResources().getColor(R.color.pink));
                b.setAllCaps(false);
                FrameLayout.LayoutParams pam = new FrameLayout.LayoutParams(-1, -2);
                pam.setMargins(20, 0, 20, 50);
                b.setTextColor(getResources().getColor(R.color.dark_green));

                frame.addView(b, pam);

                dishes[num] = new Dishdef(ma,s);
                System.out.println("assigned to dishes");
                System.out.println("num is when assigned"+num);
                try {
                    ma.writeFile(ma.readFile()  + s + '\n' + "/"+'\n');
                }
                catch(Exception e)
                {
                    ;
                }
                b.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        FragmentManager fra = getActivity().getSupportFragmentManager();
                        FragmentTransaction frat = fra.beginTransaction();
                    /*Query q;
                    q=new Query(back,f);
                    frat.replace(R.id.lay,q);
                    frat.commit();*/
                        System.out.println("num is before sending"+num);
                        StopwatchFragment sf = new StopwatchFragment(ma,dishes[num-1], back);
                        frat.replace(R.id.mainlay, sf);
                        frat.addToBackStack("back");
                        frat.commit();
                    }
                });
                num++;
          //      System.out.println(num);
            }
        }
        addish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fra=getActivity().getSupportFragmentManager();
                FragmentTransaction frat=fra.beginTransaction();
                Query q;
                q=new Query(back,f);
                frat.replace(R.id.mainlay,q);
                frat.addToBackStack("back");
                frat.commit();
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ;
            }
        });
        return v;
    }
    private void readdishfromfile()
    {
        String s;
        s=ma.readFile();
        //System.out.println(s);
        if (s!="")
        {
            boolean record;
            record=true;
            String dish;
            dish="";
            for (int i=0;i<s.length();i++)
            {
                if (record==true)
                {
                    if (s.charAt(i) == '\n') {
                        dishes[num] = new Dishdef(ma, dish);
                        num++;
                        //System.out.println("dish namde is ="+dish );
                        dish = "";
                        record=false;
                    } else {
                        dish = dish + s.charAt(i);
                    }
                }
                else
                {
                    if (s.charAt(i)=='\n' && s.charAt(i-1)=='/')
                    {
                        record=true;
                    }
                }
            }
        }
    }
}