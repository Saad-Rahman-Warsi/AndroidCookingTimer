package com.example.dishe;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link //Query#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Query extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER

    private View v;
    private Button back;
    private Button done;
    private String name;
    private boolean clicked;
    private EditText e;
    private Fragment f;
    public Query(Button back,Fragment f) {
        // Required empty public constructor
        this.back=back;
        clicked=false;
        this.f=f;
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v= inflater.inflate(R.layout.fragment_query, container, false);
        done=(Button) v.findViewById(R.id.butt1);
        e=(EditText) v.findViewById(R.id.txt2);
        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle=new Bundle();
                name=e.getText().toString();
                if (f instanceof Dish) {
                    bundle.putString("querydish", name);
                }
                if (f instanceof StopwatchFragment)
                {
                    bundle.putString("querywatch",name);
                }
                f.setArguments(bundle);
                FragmentManager fra=getActivity().getSupportFragmentManager();
                FragmentTransaction frat=fra.beginTransaction();
                frat.replace(R.id.mainlay,f);
                frat.addToBackStack("back");
                frat.commit();
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle=new Bundle();
                FragmentManager fra=getActivity().getSupportFragmentManager();
                FragmentTransaction frat=fra.beginTransaction();
                if (f instanceof Dish) {
                    bundle.putString("querydish", null);
                }
                if (f instanceof StopwatchFragment)
                {
                    bundle.putString("querywatch",null);
                }
                f.setArguments(bundle);
                frat.replace(R.id.mainlay,f);
                frat.addToBackStack("back");
                frat.commit();
            }
        });
        return v;
    }
}