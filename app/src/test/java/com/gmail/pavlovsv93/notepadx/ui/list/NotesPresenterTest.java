package com.gmail.pavlovsv93.notepadx.ui.list;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.gmail.pavlovsv93.notepadx.domain.Callback;
import com.gmail.pavlovsv93.notepadx.domain.Notes;
import com.gmail.pavlovsv93.notepadx.domain.NotesRepository;


import org.junit.Before;
import org.junit.Test;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.List;

public class NotesPresenterTest{
    private NotesPresenter presenter;
    @Mock
    private NotesView view;
    @Mock
    private NotesRepository repo;
    @Mock
    private Notes note;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        presenter = new NotesPresenter(repo, view);
    }

    @Test
    public void noteAdd_Test() {
        presenter.noteAdd(note);
        Mockito.verify(view).noteAddView(note);

        InOrder inOrder = inOrder(view);
        inOrder.verify(view).noteAddView(note);
        inOrder.verify(view).hideEmpty();
    }

    @Test
    public void noteUpdate_Test() {
        presenter.noteUpdate(note);
        Mockito.verify(view).noteUpdateView(note);

        InOrder inOrder = inOrder(view);
        inOrder.verify(view).noteUpdateView(note);
        inOrder.verify(view).hideEmpty();
    }

    @Test
    public void Callback_Test(){
        Callback<Void> callback = mock(Callback.class);
        Void result = mock(Void.class);
        Throwable throwable = mock(Throwable.class);
        callback.onSuccess(result);
        callback.onError(throwable);
        Mockito.verify(callback).onSuccess(result);
        Mockito.verify(callback).onError(throwable);
    }

    @Test
    public void requestCurrNotes_Test(){
        presenter.requestCurrNotes();

        InOrder inOrder = inOrder(view, repo);
        inOrder.verify(view).showProgress();
        inOrder.verify(repo).getNotesAll(any());
    }

    @Test
    public void removeNote_Test(){
        presenter.removeNote(note);

        InOrder inOrder = inOrder(view, repo);
        inOrder.verify(view).showProgress();
        inOrder.verify(repo).deleteNote(any() ,any());
    }
}