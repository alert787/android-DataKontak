//15-08-2019
//Muhammad Ilyas
//10116339
//IF -8

package com.example.datakontak.data;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.example.datakontak.model.Kontak;

@Database(entities = {Kontak.class}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {


    public abstract KontakDAO kontakDAO();
}