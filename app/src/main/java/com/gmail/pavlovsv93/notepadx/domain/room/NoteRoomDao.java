package com.gmail.pavlovsv93.notepadx.domain.room;

import android.provider.ContactsContract;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface NoteRoomDao {
    @Insert // Будет изменять текущий элемент БД, если id одинаковый
    void insertNoteRoom(NoteRoom noteRoom);

    @Query("SELECT * FROM NoteRoom")
    List<NoteRoom> listNoteAll();

    @Delete
    void deleteNoteRoom(NoteRoom noteRoom);

    @Update
    void updateNoteRoom(NoteRoom noteRoom);


}
