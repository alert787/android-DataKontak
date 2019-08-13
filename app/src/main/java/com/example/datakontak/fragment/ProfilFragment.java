//15-08-2019
//Muhammad Ilyas
//10116339
//IF -8

package com.example.datakontak.fragment;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.datakontak.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProfilFragment extends Fragment {

    public ProfilFragment() {
        // Required empty public constructor
    }

    public static ProfilFragment newInstance() {
        return new ProfilFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment


        return inflater.inflate(R.layout.fragment_profil, container, false);


    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        RelativeLayout telp = getView().findViewById(R.id.telp);
        RelativeLayout email = getView().findViewById(R.id.ee);
        RelativeLayout fb = getView().findViewById(R.id.ff);
        RelativeLayout steam = getView().findViewById(R.id.steam);
        telp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel","6285798521952", null));
                startActivity(i);
            }
        });

        email.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Intent.ACTION_SENDTO, Uri.parse("mailto:alert081@mahasiswa.unikom.ac.id"));
                startActivity(i);
            }
        });

        fb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse("https://facebook.com/alert787"));
                startActivity(i);
            }
        });

        steam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse("https://steamcommunity.com/id/alert7878"));
                startActivity(i);
            }
        });
    }

}
