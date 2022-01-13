package com.gmail.pavlovsv93.notepadx.ui.addNote;

import com.gmail.pavlovsv93.notepadx.domain.Notes;

public interface AddNoteView {

    void showProgress();

    void hideProgress();

    void noteSave(Notes note);
}
