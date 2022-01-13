package com.gmail.pavlovsv93.notepadx.ui.addNote;

import com.gmail.pavlovsv93.notepadx.domain.Callback;
import com.gmail.pavlovsv93.notepadx.domain.Notes;
import com.gmail.pavlovsv93.notepadx.domain.NotesRepository;

public class AddNotePresenter {

    private AddNoteView view;
    private NotesRepository repo;


    public AddNotePresenter(AddNoteView view, NotesRepository repo) {
        this.view = view;
        this.repo = repo;
    }

    public void saveNote(String title, String massage){
        view.showProgress();

        repo.addNewNote(title, massage, new Callback<Notes>() {
            @Override
            public void onSuccess(Notes result) {
                view.hideProgress();
                view.noteSave(result);
            }
            @Override
            public void onError(Throwable error) {
                view.hideProgress();
            }
        });
    }
}
