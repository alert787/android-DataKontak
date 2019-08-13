//15-08-2019
//Muhammad Ilyas
//10116339
//IF -8

package com.example.datakontak.activity;

import android.annotation.SuppressLint;
import android.arch.persistence.room.Room;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.datakontak.R;
import com.example.datakontak.data.AppDatabase;
import com.example.datakontak.model.Kontak;

public class EditActivity extends AppCompatActivity implements View.OnClickListener{

    private TextInputEditText Nama, kelas, telepon, email, fb;
    private AppDatabase database;
    private Button bSimpan;
    private Kontak kontak;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        Nama = findViewById(R.id.nama);
        kelas = findViewById(R.id.kelas);
        telepon = findViewById(R.id.telepon);
        email = findViewById(R.id.email);
        fb = findViewById(R.id.fb);
        bSimpan = findViewById(R.id.save);
        bSimpan.setOnClickListener(this);

        database = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, "dbKontak").build();


        getDataKontak();

    }


    private void getDataKontak(){

        kontak = (Kontak)getIntent().getSerializableExtra("data");

        Nama.setText(kontak.getNama());
        kelas.setText(kontak.getKelas());
        telepon.setText(kontak.getTelepon());
        email.setText(kontak.getEmail());
        fb.setText(kontak.getFb());

    }


    @SuppressLint("StaticFieldLeak")
    private void updateData(final Kontak kontak){
        new AsyncTask<Void, Void, Integer>() {
            @Override
            protected Integer doInBackground(Void... voids) {

                return database.kontakDAO().updateKontak(kontak);
            }

            @Override
            protected void onPostExecute(Integer status) {

                Toast.makeText(EditActivity.this, "Data Berhasil Diubah", Toast.LENGTH_SHORT).show();
                finish();
            }
        }.execute();
    }

    @Override
    public void onClick(View v) {

        Nama.setError(null);
        View fokus = null;
        boolean cancel = false;

        if(Nama.getText().toString().isEmpty()){
            Nama.setError("Nama tidak boleh kosong");
            fokus = Nama;
            cancel = true;
        }

        if (cancel){
            fokus.requestFocus();
        }else {
            kontak.setNama(Nama.getText().toString());
            kontak.setKelas(kelas.getText().toString());
            kontak.setTelepon(telepon.getText().toString());
            kontak.setEmail(email.getText().toString());
            kontak.setFb(fb.getText().toString());
            updateData(kontak);
        }
    }
}
