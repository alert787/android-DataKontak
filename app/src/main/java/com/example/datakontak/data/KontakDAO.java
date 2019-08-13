//15-08-2019
//Muhammad Ilyas
//10116339
//IF -8

package com.example.datakontak.data;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.example.datakontak.model.Kontak;

@Dao
public interface KontakDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long insertKontak(Kontak kontak);

    @Query("SELECT * FROM tKontak ORDER BY nim")
    Kontak[] readDataKontak();

    @Update
    int updateKontak(Kontak kontak);

    @Delete
    void deleteKontak(Kontak kontak);

    @Query("SELECT * FROM tkontak WHERE nim = :nim LIMIT 1")
    Kontak selectDetailKontak(String nim);
}