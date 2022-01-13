package com.gmail.pavlovsv93.notepadx.domain.room;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class NoteRoom {
    @PrimaryKey(autoGenerate = true)
    public int id;

    @ColumnInfo(name = "idNotes")
    public String idNotes;

    @ColumnInfo(name = "titleNotes")
    public String titleNotes;

    @ColumnInfo(name = "massageNotes")
    public String massageNotes;

    @ColumnInfo(name = "timeNotes")
    public String timeNotes;

    @ColumnInfo(name = "doneNotes")
    public boolean doneNotes;


    public NoteRoom(String idNotes, String titleNotes, String massageNotes, String timeNotes, boolean doneNotes) {
        this.idNotes = idNotes;
        this.titleNotes = titleNotes;
        this.massageNotes = massageNotes;
        this.timeNotes = timeNotes;
        this.doneNotes = doneNotes;
    }
}
