package com.example.dishe;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.io.FileNotFoundException;
import java.io.OutputStreamWriter;
import java.util.Scanner;
import java.util.Arrays;
import java.io.*;
public class MainActivity extends AppCompatActivity {
    private Button b;
    public MainActivity ma;
    public static String fileName;
    static
    {
        fileName="Rough25.csv";
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ma=this;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        b=(Button)findViewById(R.id.butt1);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                b.setText("Back");
                Dish d=new Dish(ma,b);
                FragmentManager fra=getSupportFragmentManager();
                FragmentTransaction frat=fra.beginTransaction();
                frat.replace(R.id.mainlay,d);
                frat.addToBackStack("back");
                frat.commit();
            }
        });
    }
    public void popFragment(Timeup t)
    {
        FragmentManager fra=getSupportFragmentManager();
        FragmentTransaction frat=fra.beginTransaction();
        frat.add(R.id.mainlay,t);
        frat.addToBackStack("back");
        frat.commit();
    }

    /*@Override
    protected void onDestroy () {

            Scanner sc=new Scanner(fileName);

            OutputStreamWriter out = null;
//throw new UnsupportedOperationException("SecretMessage encrypt not implemented");
// YOUR CODE HERE (remove the exception)

            out=new OutputStreamWriter(new FileOutputStream(fileName));

        super.onDestroy();
    }*/
    public String readFile()
    {
        String s;
        s="";
        //int ch;
        //InputStreamReader input = null;
        try {

            /*input = new InputStreamReader(new FileInputStream(file));
            do {
                ch = input.read();
                if (ch!=-1)
                {
                    s = s + (char) ch;
                }
            } while (ch != -1);

            input.close();*/
            FileInputStream f=openFileInput(fileName);
            int n;
            n=f.available();
            byte[] buf=new byte[n];
            f.read(buf);
            s=new String(buf);
            f.close();
        }
        catch(Exception e)
        {
            ;
        }

        return s;
    }

    public void writeFile(String s)throws IOException
    {
        /*OutputStreamWriter out = null;
        out=new OutputStreamWriter(new FileOutputStream(file));
        char ch;
        for (int j=0;j<s.length();j++)
        {
            ch=s.charAt(j);
            //System.out.println ((char)ch);
            if ((int)ch!=-1)
            {
                out.write((int)ch);
            }

        }
        //out.write((int)'\n');
        out.close();*/
        FileOutputStream f=openFileOutput(fileName,MODE_PRIVATE);
        f.write(s.getBytes());
        f.close();
    }

}
