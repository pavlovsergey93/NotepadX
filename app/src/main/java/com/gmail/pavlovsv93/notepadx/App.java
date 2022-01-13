package com.gmail.pavlovsv93.notepadx;

import android.app.Application;

import androidx.room.Room;

import com.gmail.pavlovsv93.notepadx.domain.room.NoteRoomDB;
import com.gmail.pavlovsv93.notepadx.domain.room.NoteRoomDao;

public class App extends Application {

    private NoteRoomDB notesDB;

    public NoteRoomDao getNotesDao() {
        return notesDao;
    }

    private NoteRoomDao notesDao;

    private static NoteRoomDB INSTANCE;

    public static NoteRoomDB getINSTANCE() {
        return INSTANCE;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        if (INSTANCE == null) {

            INSTANCE = Room.databaseBuilder(getApplicationContext(), NoteRoomDB.class, "NoteRoomDB")
                    .allowMainThreadQueries()
                    .build();
            notesDao = INSTANCE.noteRoomDao();
        }
    }
}
