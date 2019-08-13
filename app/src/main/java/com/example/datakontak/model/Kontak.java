//15-08-2019
//Muhammad Ilyas
//10116339
//IF -8

package com.example.datakontak.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import java.io.Serializable;

@Entity(tableName = "tKontak")
public class Kontak implements Serializable{

    @NonNull
    @PrimaryKey
    @ColumnInfo(name = "nim")
    private
    String nim;

    @ColumnInfo(name = "nama")
    private
    String nama;

    @ColumnInfo(name = "kelas")
    private
    String kelas;

    @ColumnInfo(name = "telepon")
    private
    String telepon;

    @ColumnInfo(name = "email")
    private
    String email;

    @ColumnInfo(name = "fb")
    private
    String fb;

    @NonNull
    public String getNim() {
        return nim;
    }

    public void setNim(@NonNull String nim) {
        this.nim = nim;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getKelas() {
        return kelas;
    }

    public void setKelas(String kelas) {
        this.kelas = kelas;
    }

    public String getTelepon() {
        return telepon;
    }

    public void setTelepon(String telepon) {
        this.telepon = telepon;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFb() {
        return fb;
    }

    public void setFb(String fb) {
        this.fb = fb;
    }
}