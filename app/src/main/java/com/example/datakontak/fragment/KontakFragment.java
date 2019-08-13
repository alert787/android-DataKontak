//15-08-2019
//Muhammad Ilyas
//10116339
//IF -8

package com.example.datakontak.fragment;


import android.arch.persistence.room.Room;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.datakontak.R;
import com.example.datakontak.activity.AddActivity;
import com.example.datakontak.adapter.RecyclerViewAdapter;
import com.example.datakontak.data.AppDatabase;
import com.example.datakontak.model.Kontak;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * A simple {@link Fragment} subclass.
 */
public class KontakFragment extends Fragment {

    View v;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private AppDatabase database;
    private ArrayList<Kontak> daftarKontak;


    public KontakFragment() {
        // Required empty public constructor
    }

    public static KontakFragment newInstance() {
        return new KontakFragment();

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_kontak, container, false);

        daftarKontak = new ArrayList<>();


        database = Room.databaseBuilder(getActivity().getApplicationContext(),
                AppDatabase.class, "dbKontak").allowMainThreadQueries().build();

        daftarKontak.addAll(Arrays.asList(database.kontakDAO().readDataKontak()));

        recyclerView = (RecyclerView) v.findViewById(R.id.dataItem);
        recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(this.getActivity());
        recyclerView.setLayoutManager(layoutManager);

        adapter = new RecyclerViewAdapter(daftarKontak,this.getActivity());
        recyclerView.setAdapter(adapter);

        FloatingActionButton fab = (FloatingActionButton) v.findViewById(R.id.floatingActionButton);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getContext(), AddActivity.class);
                startActivity(i);
            }

        });

        return v;
    }
    @Override
    public void onResume() {
        super.onResume();
        daftarKontak.clear();
        daftarKontak.addAll(Arrays.asList(database.kontakDAO().readDataKontak()));
        adapter.notifyDataSetChanged();
    }


}

