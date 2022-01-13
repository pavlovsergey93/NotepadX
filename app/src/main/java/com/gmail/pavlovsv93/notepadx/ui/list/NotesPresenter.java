package com.gmail.pavlovsv93.notepadx.ui.list;

import com.gmail.pavlovsv93.notepadx.domain.Callback;
import com.gmail.pavlovsv93.notepadx.domain.Notes;
import com.gmail.pavlovsv93.notepadx.domain.NotesRepository;

import java.util.List;

public class NotesPresenter {

    NotesView view;
    NotesRepository repo;

   public NotesPresenter(NotesRepository repo, NotesView view){
       this.repo = repo;
       this.view = view;
   }

   public void requestCurrNotes() {

       view.showProgress();

       repo.getNotesAll(new Callback<List<Notes>>() {
           @Override
           public void onSuccess(List<Notes> result) {
               view.showNotes(result);
               view.highProgress();
           }

           @Override
           public void onError(Throwable error) {
               view.highProgress();
               view.showError(error.getMessage());
           }
       });

   }
}
