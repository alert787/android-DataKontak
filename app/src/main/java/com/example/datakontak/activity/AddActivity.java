//15-08-2019
//Muhammad Ilyas
//10116339
//IF -8

package com.example.datakontak.activity;

import android.annotation.SuppressLint;
import android.arch.persistence.room.Room;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.datakontak.R;
import com.example.datakontak.data.AppDatabase;
import com.example.datakontak.model.Kontak;

public class AddActivity extends AppCompatActivity implements View.OnClickListener{

    private EditText NIM, Nama, kelas, telepon, email, fb;
    private AppDatabase database;
    private Button bSimpan;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        NIM = findViewById(R.id.nim);
        Nama = findViewById(R.id.nama);
        kelas = findViewById(R.id.kelas);
        telepon = findViewById(R.id.telepon);
        email = findViewById(R.id.email);
        fb = findViewById(R.id.fb);
        bSimpan = (Button)findViewById(R.id.save);
        bSimpan.setOnClickListener(this);


        database = Room.databaseBuilder(
                getApplicationContext(),
                AppDatabase.class,
                "dbKontak")
                .build();


    }

    @SuppressLint("StaticFieldLeak")
    private void insertData(final Kontak kontak){
        new AsyncTask<Void, Void, Long>() {
            @Override
            protected Long doInBackground(Void... voids) {

                return database.kontakDAO().insertKontak(kontak);
            }


        }.execute();
    }

    @Override
    public void onClick(View v) {

        NIM.setError(null);
        Nama.setError(null);
        View fokus = null;
        boolean cancel = false;

        switch (v.getId()){
            case R.id.save:

                if(NIM.getText().toString().isEmpty()){
                    NIM.setError("NIM tidak boleh kosong");
                    fokus = NIM;
                    cancel = true;
                }

                if(Nama.getText().toString().isEmpty()){
                    Nama.setError("Nama tidak boleh kosong");
                    fokus = Nama;
                    cancel = true;
                }

                if (cancel){
                    fokus.requestFocus();
                }
                else {

                    Kontak data = new Kontak();


                    data.setNim(NIM.getText().toString());
                    data.setNama(Nama.getText().toString());
                    data.setKelas(kelas.getText().toString());
                    data.setTelepon(telepon.getText().toString());
                    data.setEmail(email.getText().toString());
                    data.setFb(fb.getText().toString());

                    try {
                        insertData(data);

                        Toast.makeText(AddActivity.this, "Data Berhasil Disimpan", Toast.LENGTH_SHORT).show();
                        finish();

                        break;
                    }
                    catch (Exception e){
                        NIM.setError("NIM sudah digunakan");
                        fokus = NIM;
                        cancel = true;
                    }

                    if (cancel){
                        fokus.requestFocus();
                    }
                }
        }
    }

}
