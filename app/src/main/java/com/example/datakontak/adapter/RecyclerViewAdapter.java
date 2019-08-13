//15-08-2019
//Muhammad Ilyas
//10116339
//IF -8

package com.example.datakontak.adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.arch.persistence.room.Room;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.datakontak.activity.EditActivity;
import com.example.datakontak.R;
import com.example.datakontak.data.AppDatabase;
import com.example.datakontak.model.Kontak;

import java.util.ArrayList;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {


    private ArrayList<Kontak> daftarKontak;
    private AppDatabase appDatabase;
    private Context context;
    private Dialog mydialog;

    public RecyclerViewAdapter(ArrayList<Kontak> daftarKontak, Context context) {

        this.daftarKontak = daftarKontak;
        this.context = context;
        appDatabase = Room.databaseBuilder(
                context.getApplicationContext(),
                AppDatabase.class, "dbKontak").allowMainThreadQueries().build();
    }

    class ViewHolder extends RecyclerView.ViewHolder {


        private TextView Nim, Nama;
        private CardView item;

        ViewHolder(View itemView) {
            super(itemView);
            Nim = itemView.findViewById(R.id.nim);
            Nama = itemView.findViewById(R.id.nama);
            item = itemView.findViewById(R.id.cvMain);
        }

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_view, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public int getItemCount() {

        return daftarKontak.size();
    }

    @SuppressLint("RecyclerView")
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {


        final String getNIM = daftarKontak.get(position).getNim();
        final String getNama = daftarKontak.get(position).getNama();


        holder.Nim.setText(getNIM);
        holder.Nama.setText(getNama);

        mydialog = new Dialog(context);
        mydialog.setContentView(R.layout.dialog_contact);
        mydialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        holder.item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Kontak kontak = appDatabase.kontakDAO()
                        .selectDetailKontak(daftarKontak.get(position).getNim());

                TextView dialog_name = (TextView) mydialog.findViewById(R.id.nama);
                TextView dialog_nim = (TextView) mydialog.findViewById(R.id.nim);
                TextView dialog_kelas = (TextView) mydialog.findViewById(R.id.kelas);
                TextView dialog_telepon = (TextView) mydialog.findViewById(R.id.telepon);
                TextView dialog_email = (TextView) mydialog.findViewById(R.id.email);
                TextView dialog_fb = (TextView) mydialog.findViewById(R.id.fb);

                if(kontak != null){
                    dialog_nim.setText(kontak.getNim());
                    dialog_name.setText(kontak.getNama());
                    if (kontak.getKelas().isEmpty()){
                        dialog_kelas.setText("-");
                    }else {
                        dialog_kelas.setText(kontak.getKelas());
                    }

                    if (kontak.getTelepon().isEmpty()){
                        dialog_telepon.setText("-");
                    }else {
                        dialog_telepon.setText(kontak.getTelepon());
                    }

                    if (kontak.getEmail().isEmpty()){
                        dialog_email.setText("-");
                    }else {
                        dialog_email.setText(kontak.getEmail());
                    }

                    if (kontak.getFb().isEmpty()){
                        dialog_fb.setText("-");
                    }else {
                        dialog_fb.setText(kontak.getFb());
                    }
                }

                mydialog.show();
                if(!kontak.getTelepon().isEmpty()) {
                    dialog_telepon.setPaintFlags(Paint.UNDERLINE_TEXT_FLAG);
                    dialog_telepon.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            context.startActivity(new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", kontak.getTelepon(), null)));
                        }
                    });
                }

                if(!kontak.getEmail().isEmpty()) {
                    dialog_email.setPaintFlags(Paint.UNDERLINE_TEXT_FLAG);
                    dialog_email.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            context.startActivity(new Intent(Intent.ACTION_SENDTO, Uri.parse("mailto:" + kontak.getEmail())));

                        }
                    });
                }

                if(!kontak.getFb().isEmpty()) {
                    dialog_fb.setPaintFlags(Paint.UNDERLINE_TEXT_FLAG);
                    dialog_fb.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            context.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://" + kontak.getFb())));

                        }
                    });
                }


            }
        });

        holder.item.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                CharSequence[] menuPilihan = {"Edit", "Delete"};
                AlertDialog.Builder dialog = new AlertDialog.Builder(v.getContext())
                        .setItems(menuPilihan, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                switch (which) {
                                    case 0:

                                        onEditData(position, context);
                                        break;

                                    case 1:

                                        onDeleteData(position);
                                        break;
                                }
                            }
                        });
                dialog.create();
                dialog.show();
                return true;
            }
        });

    }


    private void onEditData(int position, Context context) {
        context.startActivity(new Intent(context, EditActivity.class).putExtra("data", daftarKontak.get(position)));
    }

    private void onDeleteData(int position) {
        appDatabase.kontakDAO().deleteKontak(daftarKontak.get(position));
        daftarKontak.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, daftarKontak.size());
        Toast.makeText(context, "Data Berhasil Dihapus", Toast.LENGTH_SHORT).show();

    }
}