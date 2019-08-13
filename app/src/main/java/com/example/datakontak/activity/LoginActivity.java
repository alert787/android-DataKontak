//15-08-2019
//Muhammad Ilyas
//10116339
//IF -8

package com.example.datakontak.activity;

import android.annotation.SuppressLint;
import android.arch.persistence.room.Room;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;

import com.example.datakontak.data.AppDatabase;
import com.example.datakontak.data.KontakDAO;
import com.example.datakontak.model.Kontak;
import com.example.datakontak.preferences.UserPreferences;
import com.example.datakontak.R;


public class LoginActivity extends AppCompatActivity {

    private EditText mViewUser, mViewPassword;

    AppDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mViewUser=findViewById(R.id.username);
        mViewPassword =findViewById(R.id.password);

        mViewPassword.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE || actionId == EditorInfo.IME_NULL) {
                    cek();
                    return true;
                }
                return false;
            }
        });

        findViewById(R.id.btnLogin).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cek();
            }
        });

        findViewById(R.id.btnregister).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getBaseContext(),RegisterActivity.class));
            }
        });

        Kontak data = new Kontak();

            data.setNim("10116322");
            data.setNama("Ari Abdul Majid");
            data.setKelas("IF-8");
            data.setTelepon("083827474755");
            data.setEmail("ariabdul@gmail.com");
            data.setFb("facebook.com/ariabdulmajid");

            insertData(data);


        Kontak data2 = new Kontak();

        data2.setNim("10116323");
        data2.setNama("Aria Pratomo");
        data2.setKelas("IF-8");
        data2.setTelepon("08577683265");
        data2.setEmail("aripratom@gmail.com");
        data2.setFb("facebook.com/aripratom");

        insertData(data2);
    }


    @Override
    protected void onStart() {
        super.onStart();
        if (UserPreferences.getLoggedInStatus(getBaseContext())){
            startActivity(new Intent(getBaseContext(),MainActivity.class));
            finish();
        }
    }


    private void cek(){

        mViewUser.setError(null);
        mViewPassword.setError(null);
        View fokus = null;
        boolean cancel = false;


        String user = mViewUser.getText().toString();
        String password = mViewPassword.getText().toString();


        if (TextUtils.isEmpty(user)){
            mViewUser.setError("Username tidak boleh kosong");
            fokus = mViewUser;
            cancel = true;
        }else if(!cekUser(user)){
            mViewUser.setError("Username tidak ditemukan");
            fokus = mViewUser;
            cancel = true;
        }


        if (TextUtils.isEmpty(password)){
            mViewPassword.setError("Password tidak boleh kosong");
            fokus = mViewPassword;
            cancel = true;
        }else if (!cekPassword(password)){
            mViewPassword.setError("Password salah, silahkan ulangi");
            fokus = mViewPassword;
            cancel = true;
        }


        if (cancel) fokus.requestFocus();
        else masuk();
    }


    private void masuk(){
        UserPreferences.setLoggedInUser(getBaseContext(), UserPreferences.getRegisteredUser(getBaseContext()));
        UserPreferences.setLoggedInStatus(getBaseContext(),true);
        startActivity(new Intent(getBaseContext(),MainActivity.class));finish();
    }


    private boolean cekPassword(String password){
        return password.equals(UserPreferences.getRegisteredPass(getBaseContext()));
    }


    private boolean cekUser(String user){
        return user.equals(UserPreferences.getRegisteredUser(getBaseContext()));
    }

    @SuppressLint("StaticFieldLeak")
    private void insertData(final Kontak kontak){
        new AsyncTask<Void, Void, Long>() {
            @Override
            protected Long doInBackground(Void... voids) {
                database = Room.databaseBuilder(
                        getApplicationContext(),
                        AppDatabase.class,
                        "dbKontak")
                        .build();
                return database.kontakDAO().insertKontak(kontak);
            }


        }.execute();
    }
}
