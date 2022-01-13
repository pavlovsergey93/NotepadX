package com.gmail.pavlovsv93.notepadx.domain.realization_save_data;

import com.gmail.pavlovsv93.notepadx.domain.Callback;
import com.gmail.pavlovsv93.notepadx.domain.Notes;
import com.gmail.pavlovsv93.notepadx.domain.NotesRepository;

import java.util.List;

public class FirebaseRepository implements NotesRepository {
    @Override
    public void getNotesAll(Callback<List<Notes>> callback) {

    }

    @Override
    public void addNewNote(String title, String massage, Callback<Notes> callback) {

    }

    @Override
    public void updateNote(Notes note) {

    }

    @Override
    public void deleteNote(Notes note, Callback<Void> callback) {

    }

    @Override
    public void checkDone(Notes note) {

    }
}
