package com.gmail.pavlovsv93.notepadx.ui.list;

import com.gmail.pavlovsv93.notepadx.domain.Callback;
import com.gmail.pavlovsv93.notepadx.domain.Notes;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;

public interface NotesView {
    void showProgress();
    void highProgress();
    void showNotes(List<Notes> notesList);
    void showError(String message);
    void showEmpty();
    void hideEmpty();
    void noteAddView(Notes note);
    void noteUpdateView(Notes note);

    void onNoteRemove(Notes note);
}
