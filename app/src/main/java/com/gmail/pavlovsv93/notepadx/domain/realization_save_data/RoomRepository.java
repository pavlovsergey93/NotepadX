package com.gmail.pavlovsv93.notepadx.domain.realization_save_data;

import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.provider.ContactsContract;

import androidx.annotation.RequiresApi;

import com.gmail.pavlovsv93.notepadx.App;
import com.gmail.pavlovsv93.notepadx.R;
import com.gmail.pavlovsv93.notepadx.domain.Callback;
import com.gmail.pavlovsv93.notepadx.domain.Notes;
import com.gmail.pavlovsv93.notepadx.domain.NotesRepository;
import com.gmail.pavlovsv93.notepadx.domain.room.NoteRoom;
import com.gmail.pavlovsv93.notepadx.domain.room.NoteRoomDB;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.UUID;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class RoomRepository implements NotesRepository {

    public static final NotesRepository INSTANCE = new RoomRepository();

    private NoteRoomDB db = App.getINSTANCE();

    private Executor executor = Executors.newSingleThreadExecutor();
    private Handler handler = new Handler(Looper.getMainLooper());

    private ArrayList<Notes> notesList = new ArrayList<>();
    private List<NoteRoom> notesRoomList = new ArrayList<>();


    @Override
    public void getNotesAll(Callback<List<Notes>> callback) {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                if(App.getINSTANCE() != null) {
                    notesRoomList = App.getINSTANCE().noteRoomDao().listNoteAll();
                    for (NoteRoom noteRoom : notesRoomList) {
                        Notes note = new Notes(noteRoom.idNotes,
                                noteRoom.titleNotes,
                                noteRoom.massageNotes,
                                noteRoom.timeNotes,
                                noteRoom.doneNotes);
                        notesList.add(note);
                    }
                }
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        if (notesRoomList != null) {
                            callback.onSuccess(notesList);
                        } else {
                            callback.onSuccess(new ArrayList<>());
                        }
                    }
                });

            }
        });

    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void addNewNote(String title, String massage, Callback<Notes> callback) {
        String id = UUID.randomUUID().toString();
        String time = timeNow();
        NoteRoom noteRoom = new NoteRoom(id, title, massage, time, false);
        App.getINSTANCE().noteRoomDao().insertNoteRoom(noteRoom);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void updateNote(Notes note) {
        for (NoteRoom noteRoom : notesRoomList) {
            if (noteRoom.idNotes.equals(note.getId())) {
                noteRoom.titleNotes = note.getTitle();
                noteRoom.massageNotes = note.getMassage();
                noteRoom.doneNotes = note.isDone();
                noteRoom.timeNotes = R.string.update_roon + timeNow();
            }
        }
    }

    @Override
    public void deleteNote(Notes note) {
        for (NoteRoom noteRoom : notesRoomList) {
            if (noteRoom.idNotes.equals(note.getId())) {
                App.getINSTANCE().noteRoomDao().deleteNoteRoom(noteRoom);
            }
        }
    }

    @Override
    public void checkDone(Notes note) {
        for (NoteRoom noteRoom : notesRoomList) {
            if (noteRoom.idNotes.equals(note.getId())) {
                noteRoom.doneNotes = !note.isDone();
                App.getINSTANCE().noteRoomDao().updateNoteRoom(noteRoom);
            }
        }
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
