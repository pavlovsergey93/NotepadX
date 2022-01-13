package com.gmail.pavlovsv93.notepadx.domain.room;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {NoteRoom.class}, version = 1, exportSchema = false)
public abstract class NoteRoomDB extends RoomDatabase {
    public abstract  NoteRoomDao noteRoomDao();
}
