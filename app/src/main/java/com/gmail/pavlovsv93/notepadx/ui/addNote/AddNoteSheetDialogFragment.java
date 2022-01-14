package com.gmail.pavlovsv93.notepadx.ui.addNote;

import static com.gmail.pavlovsv93.notepadx.R.layout.fragment_add_note_sheet_dialog;
import static com.gmail.pavlovsv93.notepadx.R.string.btn_sheet_dialog_update;

import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.annotation.StringRes;

import com.gmail.pavlovsv93.notepadx.R;
import com.gmail.pavlovsv93.notepadx.domain.Notes;
import com.gmail.pavlovsv93.notepadx.domain.realization_save_data.InMemoryNotesRepository;
import com.gmail.pavlovsv93.notepadx.domain.realization_save_data.RoomRepository;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

public class AddNoteSheetDialogFragment extends BottomSheetDialogFragment implements AddNoteView {

    public static final String ARG_NOTE = "ARG_NOTE";
    public static final String ARG_ADD_NOTE = "ARG_ADD_NOTE";

    private AddNotesPresenter presenter;

    private EditText editTextTitle;
    private EditText editTextMassage;
    private ProgressBar progressBar;
    private Button btnSheetDialog;

    public static final String TAG = "AddNoteSheetDialog";


    public static AddNoteSheetDialogFragment newInstance() {
        AddNoteSheetDialogFragment ansd = new AddNoteSheetDialogFragment();
        return ansd;
    }

    public static AddNoteSheetDialogFragment updateInstance(Notes note) {
        AddNoteSheetDialogFragment ansd = new AddNoteSheetDialogFragment();
        Bundle data = new Bundle();
        data.putParcelable(ARG_NOTE, note);
        ansd.setArguments(data);
        return ansd;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(fragment_add_note_sheet_dialog, container, false);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        editTextTitle = view.findViewById(R.id.edit_text_title);
        editTextMassage = view.findViewById(R.id.edit_text_massage);

        progressBar = view.findViewById(R.id.progress_sheet_dialog);

        btnSheetDialog = view.findViewById(R.id.btn_sheet_dialog);

        btnSheetDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.onActionPressed(editTextTitle.getText().toString(), editTextMassage.getText().toString());
            }
        });

        if(getArguments() != null){
            Notes note = getArguments().getParcelable(ARG_NOTE);
            presenter = new UpdateNotePresenter(this, InMemoryNotesRepository.INSTANCE, note);
        }else {

            //presenter = new AddNotePresenter(this, RoomRepository.INSTANCE);
            presenter = new AddNotePresenter(this, InMemoryNotesRepository.INSTANCE);
        }

    }

    @Override
    public void showProgress() {
        btnSheetDialog.setVisibility(View.GONE);
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        btnSheetDialog.setVisibility(View.VISIBLE);
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void setBtnText(@StringRes int title) {
        btnSheetDialog.setText(title);
    }

    @Override
    public void setNotesInAddSheet(String title, String message) {
        editTextTitle.setText(title);
        editTextMassage.setText(message);

    }


    @Override
    public void actionCompleted(String key, Bundle data) {
        getParentFragmentManager()
                .setFragmentResult(key, data);
        dismiss();
    }


}
