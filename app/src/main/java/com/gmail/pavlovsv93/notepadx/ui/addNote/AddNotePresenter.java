package com.gmail.pavlovsv93.notepadx.ui.addNote;

import android.os.Bundle;
import android.widget.Toast;

import com.gmail.pavlovsv93.notepadx.R;
import com.gmail.pavlovsv93.notepadx.domain.Callback;
import com.gmail.pavlovsv93.notepadx.domain.Notes;
import com.gmail.pavlovsv93.notepadx.domain.NotesRepository;

public class AddNotePresenter implements AddNotesPresenter{

    public static final String KEY_ADD_NOTE = "KEY_ADD_NOTE";
    public static final String ARG_ADD_NOTE = "ARG_ADD_NOTE";

    private AddNoteView view;
    private NotesRepository repo;


    public AddNotePresenter(AddNoteView view, NotesRepository repo) {
        this.view = view;
        this.repo = repo;
        //view.setBtnText(R.string.btn_sheet_dialog_save);
    }

//    public void saveNote(String title, String message){
//        view.showProgress();
//
//        repo.addNewNote(title, message, new Callback<Notes>() {
//            @Override
//            public void onSuccess(Notes result) {
//                view.hideProgress();
//                view.noteSave(result);
//            }
//            @Override
//            public void onError(Throwable error) {
//                view.hideProgress();
//            }
//        });
//    }

    @Override
    public void onActionPressed(String title, String message) {
        view.showProgress();

        repo.addNewNote(title, message, new Callback<Notes>() {
            @Override
            public void onSuccess(Notes result) {
                view.hideProgress();
                Bundle data = new Bundle();
                data.putParcelable(ARG_ADD_NOTE, result);
                view.actionCompleted(KEY_ADD_NOTE, data);
            }
            @Override
            public void onError(Throwable error) {
                view.hideProgress();
            }
        });
    }
}
