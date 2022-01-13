package com.gmail.pavlovsv93.notepadx.ui.details;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.gmail.pavlovsv93.notepadx.R;
import com.gmail.pavlovsv93.notepadx.domain.Notes;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

public class NoteDetailsSheetDialogFragment extends BottomSheetDialogFragment {

    public static final String KEY_BUNDLE_NOTE = "KEY_BUNDLE_NOTE";
    public static final String TAG = "NoteDetailsFragment";



    private TextView title;
    private TextView massage;
    private TextView time;
    private Button bnt;

    public static NoteDetailsSheetDialogFragment newInstance(Notes note) {
        NoteDetailsSheetDialogFragment df = new NoteDetailsSheetDialogFragment();
        Bundle args = new Bundle();
        args.putParcelable(KEY_BUNDLE_NOTE, note);
        df.setArguments(args);
        return df;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_note_details_sheet_dialog, container, false);
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Notes note = requireArguments().getParcelable(KEY_BUNDLE_NOTE);

        title = view.findViewById(R.id.details_title);
        title.setText(note.getTitle());

        time = view.findViewById(R.id.details_time);
        time.setText(note.getTime());

        massage = view.findViewById(R.id.details_massage);
        massage.setText(note.getMassage());
    }

}