package com.example.dishe;

import android.app.Activity;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link StopwatchFragment//#newInstance} factory method to
 * create an instance of this fragment.
 */
public class StopwatchFragment extends Fragment {

    private View v;
    private Button add;
    private Stopwatch w[];
    private Button back;
    private Fragment f;
    private int num;
    private Dishdef d;
    private LinearLayout l;
    private StopwatchFragment sf;
    private MainActivity ab;
    private static boolean onceread;
    static
    {
        onceread=false;
    }
    public StopwatchFragment(MainActivity ab, Dishdef d, Button back) {
        // Required empty public constructor
        w = d.getstopwatches();
        this.back = back;
        f = this;
        num = 0;
        this.d = d;
        sf = this;
        this.ab = ab;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.fragment_stopwatch, container, false);

        add = (Button) v.findViewById(R.id.but1);
        l = (LinearLayout) v.findViewById(R.id.lay);
        w = d.getstopwatches();
        if (onceread==false) {
            readdishfromfile(inflater, container);
            onceread=true;
        }
        int i=num;
        while (w[i] != null) {
            //  Stopwatch s;
            View but = inflater.inflate(R.layout.buttons, container, false);
            w[i] = new Stopwatch(ab, sf, f, l, w[i], but);
            i++;
        }
        if (i!=0)
        {
            num = i - 1;
        }

        if (f.getArguments() != null) {
            System.out.println("hola");
            Stopwatch s;
            View but = inflater.inflate(R.layout.buttons, container, false);
            Bundle bundle = f.getArguments();
            String str = bundle.getString("querywatch");
            if (str != null) {
                s = new Stopwatch(-1, ab, sf, d.getName(), back, str, l, getResources().getColor(R.color.pink), getResources().getColor(R.color.teal_200), getResources().getDimension(R.dimen.height), but, getResources().getColor(R.color.red));
                d.addStopwatch(s);
                //System.out.println("have a nice day");
                writeStopwatch(str,-1);
                //System.out.println("have a nice day");
                num++;
                w[num] = s;
            }
        }
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                FragmentManager fra = getActivity().getSupportFragmentManager();
                FragmentTransaction frat = fra.beginTransaction();
                Query q;
                q = new Query(back, f);
                frat.replace(R.id.mainlay, q);
                frat.addToBackStack("back");
                frat.commit();
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fra = getActivity().getSupportFragmentManager();
                FragmentTransaction frat = fra.beginTransaction();
                Dish d;
                d = new Dish(ab, back);
                frat.replace(R.id.mainlay, d);
                frat.addToBackStack("back");
                frat.commit();
            }
        });
        return v;
    }

    private void writeStopwatch(String namedish,Integer time) {
        String s;
        s = ab.readFile();
        String nf;
        nf="";
        //System.out.println(s);
        if (s != "") {
            boolean record;
            record = true;
            String dish;
            dish = "";
            int i = 0;
            while (!(dish.equals(d.getName()) && s.charAt(i) == '\n')) {
                if (record == true) {
                    if (s.charAt(i) == '\n') {
                        //dishes[num] = new Dishdef(ma, dish);
                        //num++;
                        //System.out.println("dish namde is ="+dish );
                        dish = "";
                        record = false;
                    } else {
                        dish = dish + s.charAt(i);
                    }
                } else {
                    if (s.charAt(i) == '\n' && s.charAt(i - 1) == '/') {
                        record = true;
                    }
                }
                nf = nf + s.charAt(i);
                i++;
            }
            //i = i + 1;
            while (s.charAt(i) != '/') {
                nf = nf + s.charAt(i);
                i++;
            }
            //nf = "";

            //s = s.substring(0, i);
            String of;
            of="";
            while (i <= s.length() - 1) {
                of = of + s.charAt(i);
                i++;
            }
            System.out.println("nf="+nf);
            System.out.println("nf end");
            s = nf + namedish + "#" + time.toString() + "#"  +'\n'+of;
       //     System.out.println("s="+s);
        //    System.out.println("s end");
            try {
                ab.writeFile(s);
            } catch (Exception e) {
                  ;
            }
        }
        System.out.println("beginning of file");
        System.out.println(s);
        System.out.println("end of file");
    }
        public void setimeupfragment (FragmentActivity a, Timeup t, Bundle b)
        {
            System.out.println(getActivity() == null);
            FragmentManager fra = getActivity().getSupportFragmentManager();
            FragmentTransaction frat = fra.beginTransaction();
            //fra.put
            frat.add(R.id.mainlay, t);
            frat.addToBackStack("back");
            frat.commit();
        }
        private void readdishfromfile (LayoutInflater inflater, ViewGroup container){
            System.out.println("reached readdishfromfile");
            String s;
            s = ab.readFile();
            //System.out.println("File is");
            //System.out.println(s);
            //System.out.println("file ends");
            if (s != "") {
                boolean record;
                record = true;
                String dish;
                dish = "";
                int i = 0;
                //System.out.println("start");
                //System.out.println('\n');
                //System.out.println(d.getName());
                while (!(dish.equals( d.getName()) && s.charAt(i) == '\n')) {
                    //System.out.println("boolean="+ (s.charAt(i)=='\n'));
                    //System.out.println("s.charAt(i)="+s.charAt(i));
                    //System.out.println("dish="+dish);
                    if (record == true) {
                        if (s.charAt(i) == '\n') {
                            //dishes[num] = new Dishdef(ma, dish);
                            //num++;
                            //System.out.println("dish namde is ="+dish );
                            dish = "";
                            record = false;
                        } else {
                            dish = dish + s.charAt(i);
                        }
                    } else {
                        if (s.charAt(i) == '\n' && s.charAt(i - 1) == '/') {
                            record = true;
                        }
                    }
                    i++;
                }

                //System.out.println("loop1 crossed");
                //System.out.println(s.charAt(i));

                i = i + 1;
                //System.out.println(s.charAt(i));
                while (s.charAt(i) != '/') {
                    //System.out.println(s.charAt(i));
                    boolean watchread;
                    watchread = true;
                    String watch;
                    String time;
                    watch = "";
                    time = "";
                    while (s.charAt(i) != '\n') {
                        while (s.charAt(i) != '#') {
                            System.out.println(s.charAt(i));
                            if (watchread == true) {
                                watch = watch + s.charAt(i);
                            } else {
                                time = time + s.charAt(i);
                            }
                            i++;
                        }
                        if (watchread == true) {
                            watchread = false;
                        } else {
                            watchread = true;
                        }
                        i++;

                    }
                    Stopwatch stop;
                    View but = inflater.inflate(R.layout.buttons, container, false);
                    int timeinsec;
                    //System.out.println(time);
                    timeinsec = Integer.parseInt(time);
                    stop = new Stopwatch(timeinsec, ab, sf, d.getName(), back, watch, l, getResources().getColor(R.color.pink), getResources().getColor(R.color.teal_200), getResources().getDimension(R.dimen.height), but, getResources().getColor(R.color.red));
                    d.addStopwatch(stop);
                    w[num] = stop;
                    num++;
                    System.out.println("watch="+watch);
                    System.out.println("time="+time);
                    watch = "";
                    time = "";
                    i++;
                }
            }
        }
    }




