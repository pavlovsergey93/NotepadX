package com.gmail.pavlovsv93.notepadx.domain;

import java.util.List;

public interface NotesRepository {

    void getNotesAll(Callback <List<Notes>> callback);

    void addNewNote(String title, String massage, Callback<Notes> callback);

    void updateNote(String noteId,String title, String massage, Callback<Notes> callback);

    void deleteNote(Notes note, Callback<Void> callback);

    void checkDone(Notes note, Callback<Void> callback);

}
