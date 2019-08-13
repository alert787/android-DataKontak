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
 * Use the {@link SayaFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SayaFragment extends Fragment {

    public SayaFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment
     * @return A new instance of fragment SayaFragment.
     */
    public static SayaFragment newInstance() {
        SayaFragment fragment = new SayaFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_saya, container, false);
    }
}
