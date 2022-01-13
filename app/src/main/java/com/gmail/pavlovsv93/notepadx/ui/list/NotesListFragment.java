package com.gmail.pavlovsv93.notepadx.ui.list;

import android.os.Build;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentResultListener;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.gmail.pavlovsv93.notepadx.NotesAdapter;
import com.gmail.pavlovsv93.notepadx.R;
import com.gmail.pavlovsv93.notepadx.domain.Notes;
import com.gmail.pavlovsv93.notepadx.domain.realization_save_data.InMemoryNotesRepository;
import com.gmail.pavlovsv93.notepadx.ui.addNote.AddNoteSheetDialogFragment;

import java.util.List;

public class NotesListFragment extends Fragment implements NotesView {

    public static final String ARG_NOTE = "ARG_NOTE";
    public static final String KEY_NOTE = "KEY_NOTE";

    private ProgressBar progress;
    private ImageView imageView;

    private Notes selectNote;

    private RecyclerView recyclerList;

    private NotesAdapter adapter;

    private NotesPresenter presenter;

    private FragmentManager fragmentManager;

    public NotesListFragment() {
        super(R.layout.fragment_notes_list);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        fragmentManager = getParentFragmentManager();


        //presenter = new NotesPresenter(RoomRepository.INSTANCE, this);
        presenter = new NotesPresenter(InMemoryNotesRepository.INSTANCE, this);

        adapter = new NotesAdapter(this);
        adapter.setOnClick(new NotesAdapter.OnClick() {
            @Override
            public void onClick(Notes note) {
                Bundle data = new Bundle();
                data.putParcelable(ARG_NOTE, note);
                getParentFragmentManager().setFragmentResult(KEY_NOTE, data);
            }

            @Override
            public void onLongClick(Notes note) {
                selectNote = note;
            }
        });

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        progress = view.findViewById(R.id.progress);
        imageView = view.findViewById(R.id.image_empty);

        recyclerList = view.findViewById(R.id.recycler_list);
        recyclerList.setLayoutManager(new LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false));
        recyclerList.setAdapter(adapter);
        presenter.requestCurrNotes();

        view.findViewById(R.id.add_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddNoteSheetDialogFragment.newInstance().show(getParentFragmentManager(), AddNoteSheetDialogFragment.TAG);
            }
        });

        getParentFragmentManager().setFragmentResultListener(AddNoteSheetDialogFragment.KEY_ADD_NOTE, getViewLifecycleOwner(), new FragmentResultListener() {
            @Override
            public void onFragmentResult(@NonNull String requestKey, @NonNull Bundle result) {

                Notes note = result.getParcelable(AddNoteSheetDialogFragment.ARG_ADD_NOTE);

                presenter.noteAdd(note);

            }
        });

    }

    @Override
    public void onCreateContextMenu(@NonNull ContextMenu menu, @NonNull View v, @Nullable ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);

        MenuInflater mi = requireActivity().getMenuInflater();
        mi.inflate(R.menu.context_menu, menu);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.menu_context_update:
                AddNoteSheetDialogFragment.updateInstance(selectNote);
                return true;
            case R.id.menu_context_delete:

                return true;
            case R.id.menu_context_share:

                return true;
        }
        return super.onContextItemSelected(item);
    }

    @Override
    public void showProgress() {
        progress.setVisibility(View.VISIBLE);
    }

    @Override
    public void highProgress() {
        progress.setVisibility(View.GONE);
    }

    @Override
    public void showNotes(List<Notes> notesList) {
        adapter.setListData(notesList);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void showError(String message) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_LONG);
    }

    @Override
    public void showEmpty() {
        imageView.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideEmpty() {
        imageView.setVisibility(View.GONE);

    }

    @Override
    public void noteAddView(Notes note) {
        int index = adapter.addNotes(note);
        recyclerList.smoothScrollToPosition(index-1);
        adapter.notifyItemInserted(index - 1);
    }

    @Override
    public void noteUpdateView(Notes note) {
        int index = adapter.updateNote(note);
        adapter.notifyItemInserted(index - 1);
    }
}
