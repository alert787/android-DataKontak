//15-08-2019
//Muhammad Ilyas
//10116339
//IF -8

package com.example.datakontak.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.datakontak.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class BantuanFragment extends Fragment {


    public static final String PAGE_TITLE = "BANTUAN";


    public BantuanFragment() {
        // Required empty public constructor
    }

    public static BantuanFragment newInstance() {
        return new BantuanFragment();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_bantuan, container, false);
    }

}
