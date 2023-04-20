package com.gmail.pavlovsv93.notepadx.ui.addNote;

import android.os.Bundle;

import androidx.annotation.StringRes;

import com.gmail.pavlovsv93.notepadx.domain.Notes;

public interface AddNoteView {

    void showProgress();

    void hideProgress();

    void setBtnText(@StringRes int title);

    void setNotesInAddSheet(String title, String message);

    void actionCompleted(String key, Bundle data);
}
