package com.gmail.pavlovsv93.notepadx.domain;

public interface Callback<T> {

    void onSuccess(T result);

    void onError(Throwable error);
}
