package com.example.dishe;


import static java.security.AccessController.getContext;

import android.content.res.Resources;
import android.media.MediaPlayer;
import android.os.CountDownTimer;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentOnAttachListener;
import androidx.fragment.app.FragmentTransaction;
import android.support.v4.app.*;

import android.os.Bundle;
import android.view.View;


import android.widget.TextView;

import android.widget.Button;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.lang.reflect.Type;
import java.util.zip.Inflater;

public class Stopwatch {
    private String name;
    private CountDownTimer c;
    private Button b;
    private FloatingActionButton start;
    private FloatingActionButton pause;
    private FloatingActionButton stop;
    private MediaPlayer med;
    private long a;
    private EditText e;
    private LinearLayout l;
    private int txtcolor;
    private int btncolor;
    private float height;
    private LinearLayout medbut;
    private View vi;
    private int errcol;
    private Button back;
    private String dish;
    private Fragment prev;
    private StopwatchFragment sp;
    private MainActivity fra;
    private boolean stationary;
    private int initime;
    //    private LinearLayout.LayoutParams but;
    Stopwatch(int initime,MainActivity fra, StopwatchFragment prev, String dish, Button back, String name, LinearLayout l, int bt, int col, float m, View v, int errcol)
    {
        this.initime=initime;
        stationary=true;
        this.fra=fra;
        this.prev=prev;
        this.dish=dish;
        //medbut=buttons;
        this.back=back;
        this.name=name;
        this.errcol=errcol;
        vi=v;
        height=m;
        btncolor=bt;
        txtcolor=col;
        this.l=l;
        e=new EditText(l.getContext());
        if (initime==-1)
        {
            e.setText(R.string.stopwatch);
        }
        else
        {
            e.setText(generatetime(initime));
        }
        e.setTextColor(txtcolor);
        //e.setTextSize(R.dimen.fontsize);
        e.setTextSize(height);
        e.setGravity(Gravity.CENTER);
        //    e.setTextSize(R.dimen.fontsize);

/*        int width= LinearLayout.LayoutParams.MATCH_PARENT;
        int height = LinearLayout.LayoutParams.WRAP_CONTENT;
        but=new LinearLayout.LayoutParams(width,height);
        but.setMargins(margin,margin,margin,margin);*/

        l.addView(e);
        //l.addView(v);
        //e.setLayoutParams(but);



        /*b=new Button(l.getContext());
        b.setText(R.string.start);
        b.setBackgroundColor(btncolor);
        b.setAllCaps(false);

        l.addView(b);*/
        //LinearLayout.LayoutParams lp=new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,LinearLayout.LayoutParams.WRAP_CONTENT,Gravity.CENTER);
        if (l!=null)
        {
            System.out.println("l is not null");
        }
        if (vi!=null)
        {
            System.out.println("vi is not null");
        }
        System.out.println("bonjour");
        l.addView(vi);
        System.out.println("bonjour");
        //l.addView(medbut);
        start=(FloatingActionButton) vi.findViewById(R.id.butt1);
        pause=(FloatingActionButton) vi.findViewById(R.id.butt2);
        stop=(FloatingActionButton) vi.findViewById(R.id.butt3);
        pause.setClickable(false);
        stop.setClickable(false);
        e.setEnabled(true);

        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stationary=false;
                String s;
                s="";
                e.setTextColor(txtcolor);
                if (initime==-1)
                {
                     s = e.getText().toString();
                }
                if (!(validformat(s) == false && initime==-1)) {
                    e.setEnabled(false);
                    //e.getInputType();
                    //e.setInputType(Type.);
                    start.setClickable(false);
                    pause.setClickable(true);
                    stop.setClickable(true);
                    if (initime==-1)
                    {
                        a = (long) (Long.parseLong(s.substring(0, 2)) * 3600 * 1000 + Integer.parseInt(s.substring(3, 5)) * 60 * 1000 + Integer.parseInt(s.substring(6, 8)) * 1000);
                        changetime(a);
                    }
                    else
                    {
                        a=initime;

                    }
                    c = new CountDownTimer(a, 1000) {
                        @Override
                        public void onTick(long l)
                        {
                            e.setText(generatetime(l));
                            a=l;
                        }

                        @Override
                        public void onFinish() {
                            e.setText(R.string.timeup);
                            e.setEnabled(true);
                        }
                    };

                    c.start();


                    ;
                    pause.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            if (c != null) {
                                c.cancel();
                                stationary=true;
                            }
                            start.setClickable(true);
                            pause.setClickable(false);
                        }
                    });
                    stop.setOnClickListener(new View.OnClickListener() {
                                                @Override
                                                public void onClick(View view) {
                                                    if (c != null) {
                                                        c.cancel();
                                                        stationary=true;
                                                    }
                                                    e.setEnabled(true);
                                                    e.setText(R.string.stopwatch);
                                                    //e.setFocusable(true);
                                                    start.setClickable(true);
                                                    pause.setClickable(false);
                                                    stop.setClickable(false);
                                                }
                                            }
                    );
                    pause.setClickable(true);
                    stop.setClickable(true);

                }
                else
                {
                    e.setText(R.string.inv);
                    e.setTextColor(errcol);
                }
            }
        });
//        start=new FloatingActionButton(l.getContext());
        //start.setBackgroundDrawable();
//        start.setImageResource((int)(androidx.constraintlayout.widget.R.drawable.abc_ic_star_black_16dp));
        //med=MediaPlayer.create(this,R.raw.soho);
        //start.seti
    }

    private String generatetime(long timedifference)
    {
        //long timedifference=currentime-startime;
        int hours;
        int minutes;
        int seconds;
        hours=(int)(timedifference/(3600*1000));
        minutes=(int)((timedifference-hours*3600*100)/(60*1000));
        seconds=(int)((timedifference-hours*3600*100-minutes*60*1000)/1000);
        String s="";
        String hourss="";
        String minutess="";
        String secondss="";
        if (hours<10)
        {
            hourss="0"+hours;
        }
        else
        {
            hourss=hourss+hours;
        }
        if (minutes<10)
        {
            minutess="0"+minutes;
        }
        else
        {
            minutess=minutess+minutes;
        }
        if (seconds<10)
        {
            secondss="0"+seconds;
        }
        else
        {
            secondss=secondss+seconds;
        }
        s=hourss+":"+minutess+":"+secondss;
        return s;
    }

    private boolean validformat(String t) {
        if (t.length() != 8) {
            return false;
        }
        if ((int) (t.charAt(0)) < 48 || (int) (t.charAt(1)) < 48 || (int) (t.charAt(3)) < 48 || (int) (t.charAt(4)) < 48 || (int) (t.charAt(6)) < 48 || (int) (t.charAt(7)) < 48) {
            return false;
        }
        if ((int) (t.charAt(0)) > 57 || (int) (t.charAt(1)) > 57 || (int) (t.charAt(3)) > 53 || (int) (t.charAt(4)) > 57 || (int) (t.charAt(6)) > 53 || (int) (t.charAt(7)) > 57)
        {
            return false;
        }

        return true;
    }

    public Stopwatch (MainActivity fra,StopwatchFragment sp,Fragment prev,LinearLayout v,Stopwatch s,View but)
    {
        //View but=inflater.inflate(R.layout.buttons,container,false);
        this.initime= s.initime;
        this.stationary=s.stationary;
        this.fra=fra;
        this.sp=sp;
        this.prev=prev;
        this.dish=s.dish;
        this.back=s.back;
         this.name=s.name;
         this.a=s.a;
         this.vi=but;
         this.l=v;
         this.txtcolor=s.txtcolor;
         this.btncolor=s.btncolor;
         this.height= s.height;
         this.errcol=s.errcol;
         e=new EditText(l.getContext());
         e.setText(R.string.stopwatch);
         e.setTextColor(txtcolor);
         //e.setTextSize(R.dimen.fontsize);
         e.setTextSize(height);
         e.setGravity(Gravity.CENTER);
         v.addView(e);
        l.addView(vi);
        //l.addView(medbut);
        start=(FloatingActionButton) vi.findViewById(R.id.butt1);
        pause=(FloatingActionButton) vi.findViewById(R.id.butt2);
        stop=(FloatingActionButton) vi.findViewById(R.id.butt3);
        c = new CountDownTimer(a, 1000) {
            @Override
            public void onTick(long l)
            {
                a=l;
                e.setText(generatetime(l));
            }

            @Override
            public void onFinish() {
                //stationary=true;
                e.setText(R.string.timeup);
                e.setEnabled(true);
                System.out.println("oui");
                Bundle bu=new Bundle();
                String key="frag";
                bu.putInt(key,1);
                Fragment pre;
                pre=null;
                try
                {
                    pre=fra.getSupportFragmentManager().getFragment(bu,key);
                }
                catch (Exception e)
                {
                    ;
                }
                Timeup tp=new Timeup(pre,dish,name,back);
                System.out.println("oui");
                //fra.popFragment(tp);

                //Bundle bdl=new Bundle();
                //bdl.putString("finish",dish+"\n"+name);
                //sp.setimeupfragment(fra,tp,bdl);
                //tp.onCreateView();
                if (stationary==false)
                {
                    FragmentManager ff = fra.getSupportFragmentManager();
                    FragmentTransaction frat = ff.beginTransaction();
                    frat.replace(R.id.mainlay, tp);
                    frat.addToBackStack("back");
                    frat.commit();
                    stationary=true;
                }
                //FragmentTransaction fts = getSupportFragmentManager().beginTransaction();
                // Replace the content of the container
                //fts.replace(R.id.flContainer, new FirstFragment());
                // Append this transaction to the backstack
                //fts.addToBackStack("optional tag");
// Commit the changes
                //fts.commit();

            }
        };

        c.start();
        start.setClickable(false);
        pause.setClickable(true);
        stop.setClickable(true);
        pause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (c != null) {
                    c.cancel();
                    stationary=true;
                }
                start.setClickable(true);
                pause.setClickable(false);
            }
        });
        stop.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        if (c != null) {
                                            c.cancel();
                                            stationary=true;
                                        }
                                        e.setEnabled(true);
                                        e.setText(R.string.stopwatch);
                                        //e.setFocusable(true);
                                        start.setClickable(true);
                                        pause.setClickable(false);
                                        stop.setClickable(false);
                                    }
                                }
        );
    }

    private void changetime(long time)
    {
        String s;
        s=fra.readFile();
        //System.out.println(s);
        boolean record;
        record = true;
        String dishe;
        dishe = "";
        int i = 0;
        String nf;
        nf="";
        while (!(dishe.equals(dish) && s.charAt(i) == '\n')) {
            if (record == true) {
                if (s.charAt(i) == '\n') {
                    //dishes[num] = new Dishdef(ma, dish);
                    //num++;
                    //System.out.println("dish namde is ="+dish );
                    dishe = "";
                    record = false;
                } else {
                    dishe = dishe + s.charAt(i);
                }
            } else {
                if (s.charAt(i) == '\n' && s.charAt(i - 1) == '/') {
                    record = true;
                }
            }
            nf = nf + s.charAt(i);
            i++;
        }
        nf=nf+s.charAt(i);
        i=i+1;
        String stn;
        stn="";
        record=true;
        System.out.println("nf=");
        System.out.println(nf);
        System.out.println("nf ends");
        while (!(stn.equals(name) && s.charAt(i) == '#')) {
            System.out.println(s.charAt(i));
            if (record == true) {
                if (s.charAt(i) == '#') {
                    //dishes[num] = new Dishdef(ma, dish);
                    //num++;
                    //System.out.println("dish namde is ="+dish );
                    stn = "";
                    record = false;
                } else {
                    stn = stn + s.charAt(i);
                }
            } else {
                if (s.charAt(i) == '\n' && s.charAt(i - 1) == '#') {
                    record = true;
                }
            }
            nf = nf + s.charAt(i);
            i++;
        }
        i=i+5;
        String of;
        of="";
        while (i <= s.length() - 1) {
            of = of + s.charAt(i);
            i++;
        }
        String newfile=nf+"#"+time+"#"+'\n'+of;
        try
        {
            fra.writeFile(newfile);
        }
        catch (Exception e)
        {
            ;
        }
        System.out.println("newfile=");
        System.out.println(newfile);
    }
}





                /*char ch;
                for(int i=0;i<count;i++)
        {
        String s=songs[i].toString();
        for (int j=0;j<s.length();j++)
        {
        ch=s.charAt(j);
        //System.out.println ((char)ch);
        out.write((int)ch);

        }
        out.write((int)'\n');
        }
        //input.close();
        out.close();*/


