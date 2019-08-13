//15-08-2019
//Muhammad Ilyas
//10116339
//IF -8

package com.example.datakontak.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.datakontak.R;
import com.example.datakontak.activity.LoginActivity;
import com.example.datakontak.preferences.UserPreferences;

import java.util.prefs.Preferences;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SayaFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class UserFragment extends Fragment {

    public Button btnlogout;
    View v;
    public UserFragment() {
        // Required empty public constructor
    }

    public static UserFragment newInstance() {
        return new UserFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.fragment_user, container, false);

        TextView nama = (TextView) v.findViewById(R.id.user);

        nama.setText(UserPreferences.getLoggedInUser(getContext()));

        btnlogout = (Button) v.findViewById(R.id.btnLOGOUT);
        if (btnlogout != null) {
            btnlogout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {


                    UserPreferences.clearLoggedInUser(getContext());
                    startActivity(new Intent(getContext(), LoginActivity.class));
                    getActivity().finish();
                }
            });
        }
        return v;
    }


}
