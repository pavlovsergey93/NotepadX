package com.gmail.pavlovsv93.notepadx.domain.realization_save_data;

import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.preference.PreferenceActivity;

import androidx.annotation.RequiresApi;

import com.gmail.pavlovsv93.notepadx.R;
import com.gmail.pavlovsv93.notepadx.domain.Callback;
import com.gmail.pavlovsv93.notepadx.domain.Notes;
import com.gmail.pavlovsv93.notepadx.domain.NotesRepository;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

@RequiresApi(api = Build.VERSION_CODES.N)
public class InMemoryNotesRepository implements NotesRepository {

    private static final String KEY_TITLE = "KEY_TITLE";
    private static final String KEY_MESSAGE = "KEY_MESSAGE";
    private static final String KEY_TIME = "KEY_TIME";

    public static final NotesRepository INSTANCE = new InMemoryNotesRepository();

    private Executor executor = Executors.newSingleThreadExecutor();
    private ArrayList<Notes> notesList = new ArrayList<>();
    private Handler handler = new Handler(Looper.getMainLooper());

    @RequiresApi(api = Build.VERSION_CODES.N)
    private InMemoryNotesRepository() {
        for (int i = 0; i < 100; i++) {
            notesList.add(new Notes(UUID.randomUUID().toString(), "Заголовок "+ i +" lfjdalfdslkfha;jdsgh;adfhgjlhdfjgk;dfhag;jkdfhakghfdkjaghdfjkhgjkahgdf"+i, "Сообщение fdklflj;adfgl;hadf;jlghrkj;htiquerhgodfljagbkjadfgjhadfjgkdshgkjadhkjghsjkhgdsfdklflj;adfgl;hadf;jlghrkj;htiquerhgodfljagbkjadfgjhadfjgkdshgkjadhkjghsjkhgdsfdklflj;adfgl;hadf;jlghrkj;htiquerhgodfljagbkjadfgjhadfjgkdshgkjadhkjghsjkhgdsfdklflj;adfgl;hadf;jlghrkj;htiquerhgodfljagbkjadfgjhadfjgkdshgkjadhkjghsjkhgdsfdklflj;adfgl;hadf;jlghrkj;htiquerhgodfljagbkjadfgjhadfjgkdshgkjadhkjghsjkhgdsfdklflj;adfgl;hadf;jlghrkj;htiquerhgodfljagbkjadfgjhadfjgkdshgkjadhkjghsjkhgdsfdklflj;adfgl;hadf;jlghrkj;htiquerhgodfljagbkjadfgjhadfjgkdshgkjadhkjghsjkhgdsfdklflj;adfgl;hadf;jlghrkj;htiquerhgodfljagbkjadfgjhadfjgkdshgkjadhkjghsjkhgdsfdklflj;adfgl;hadf;jlghrkj;htiquerhgodfljagbkjadfgjhadfjgkdshgkjadhkjghsjkhgdsfdklflj;adfgl;hadf;jlghrkj;htiquerhgodfljagbkjadfgjhadfjgkdshgkjadhkjghsjkhgdsfdklflj;adfgl;hadf;jlghrkj;htiquerhgodfljagbkjadfgjhadfjgkdshgkjadhkjghsjkhgdsfdklflj;adfgl;hadf;jlghrkj;htiquerhgodfljagbkjadfgjhadfjgkdshgkjadhkjghsjkhgdsfdklflj;adfgl;hadf;jlghrkj;htiquerhgodfljagbkjadfgjhadfjgkdshgkjadhkjghsjkhgdsfdklflj;adfgl;hadf;jlghrkj;htiquerhgodfljagbkjadfgjhadfjgkdshgkjadhkjghsjkhgdsfdklflj;adfgl;hadf;jlghrkj;htiquerhgodfljagbkjadfgjhadfjgkdshgkjadhkjghsjkhgdsfdklflj;adfgl;hadf;jlghrkj;htiquerhgodfljagbkjadfgjhadfjgkdshgkjadhkjghsjkhgdsfdklflj;adfgl;hadf;jlghrkj;htiquerhgodfljagbkjadfgjhadfjgkdshgkjadhkjghsjkhgdsfdklflj;adfgl;hadf;jlghrkj;htiquerhgodfljagbkjadfgjhadfjgkdshgkjadhkjghsjkhgds"+i, timeNow(), false));
        }
    }

    @Override
    public void getNotesAll(Callback<List<Notes>> callback) {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        callback.onSuccess(notesList);
                    }
                });

            }
        });

    }

    @Override
    public void addNewNote(String title, String massage, Callback<Notes> callback) {
        executor.execute(new Runnable() {

            @Override
            public void run() {
                handler.post(new Runnable() {
                    @RequiresApi(api = Build.VERSION_CODES.N)
                    @Override
                    public void run() {
                        String id = UUID.randomUUID().toString();
                        String time = timeNow();

                        Notes note = new Notes(id, title, massage, time, false);

                        notesList.add(note);

                        callback.onSuccess(note);
                    }
                });

            }
        });
    }

    @Override
    public void updateNote(Notes note) {

    }

    @Override
    public void deleteNote(Notes note, Callback<Void> callback) {

        executor.execute(new Runnable() {

            @Override
            public void run() {
                handler.post(new Runnable() {
                    @RequiresApi(api = Build.VERSION_CODES.N)
                    @Override
                    public void run() {

                        notesList.remove(note);

                        callback.onSuccess(null);
                    }
                });

            }
        });


    }

    @Override
    public void checkDone(Notes note) {

    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private String timeNow() {
        // Текущее время
        Date currentDate = new Date();
        // Форматирование времени как "день.месяц.год"
        android.icu.text.SimpleDateFormat dateFormat = new android.icu.text.SimpleDateFormat("dd.MM.yyyy", Locale.getDefault());
        String dateText = dateFormat.format(currentDate);
        // Форматирование времени как "часы:минуты:секунды"
        android.icu.text.SimpleDateFormat timeFormat = new android.icu.text.SimpleDateFormat("HH:mm:ss", Locale.getDefault());
        String timeText = timeFormat.format(currentDate);
        String result = dateText + " " + timeText;
        return result;
    }
}

