package com.example.dishe;

public class Dishdef {
    private Stopwatch w[];
    private String name;
    private int num;
    private MainActivity ma;
    Dishdef(MainActivity ma,String name)
    {
        this.name=name;
        w=new Stopwatch[10];
        num=0;
        this.ma=ma;
    }

    public String getName()
    {
        return name;
    }

    public void addStopwatch(Stopwatch s)
    {
        w[num]=s;
        num++;
    }

    public Stopwatch[] getstopwatches()
    {
        return w;
    }
}
