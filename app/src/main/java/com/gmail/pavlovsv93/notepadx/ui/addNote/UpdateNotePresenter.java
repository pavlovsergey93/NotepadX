package com.gmail.pavlovsv93.notepadx.ui.addNote;


import android.os.Bundle;

import com.gmail.pavlovsv93.notepadx.R;
import com.gmail.pavlovsv93.notepadx.domain.Callback;
import com.gmail.pavlovsv93.notepadx.domain.Notes;
import com.gmail.pavlovsv93.notepadx.domain.NotesRepository;

public class UpdateNotePresenter implements AddNotesPresenter{

    public static final String KEY_UPDATE_NOTE = "KEY_UPDATE_NOTE";
    public static final String ARG_UPDATE_NOTE = "ARG_UPDATE_NOTE";

    private AddNoteView view;
    private NotesRepository repo;
    private Notes note;

    public UpdateNotePresenter(AddNoteView view, NotesRepository repo, Notes note) {
        this.view = view;
        this.repo = repo;
        this.note = note;

        view.setBtnText(R.string.btn_sheet_dialog_update);
        view.setNotesInAddSheet(note.getTitle(), note.getMassage());
    }

    @Override
    public void onActionPressed(String title, String message) {
        view.showProgress();
        repo.updateNote(note.getId(), title, message, new Callback<Notes>() {
            @Override
            public void onSuccess(Notes result) {
                view.hideProgress();
                Bundle data = new Bundle();
                data.putParcelable(ARG_UPDATE_NOTE, result);
                view.actionCompleted(KEY_UPDATE_NOTE, data);
            }

            @Override
            public void onError(Throwable error) {
                view.hideProgress();
            }
        });

    }
}
