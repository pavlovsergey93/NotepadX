package com.gmail.pavlovsv93.notepadx.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentResultListener;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.gmail.pavlovsv93.notepadx.R;
import com.gmail.pavlovsv93.notepadx.domain.Notes;
import com.gmail.pavlovsv93.notepadx.ui.details.NoteDetailsFragment;
import com.gmail.pavlovsv93.notepadx.ui.list.NotesListFragment;
import com.google.android.material.navigation.NavigationView;

public class NotepadXActivity extends AppCompatActivity {

    private Notes secondNote;
    private static final String ARG_SECOND_NOTE = "ARG_SECOND_NOTE";

    private FragmentManager fm;

    private DrawerLayout drawerLayout;
    private Toolbar toolbar;
    private NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notepadx);

        if (savedInstanceState != null && savedInstanceState.containsKey(ARG_SECOND_NOTE)) {
            secondNote = savedInstanceState.getParcelable(ARG_SECOND_NOTE);
        }

        fm = getSupportFragmentManager();
        fm.beginTransaction()
                .replace(R.id.fragment_container, new NotesListFragment())
                .commit();

        toolbar = findViewById(R.id.toolbar_nm);
        drawerLayout = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.tools_title, R.string.tools_massage);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.menu_nav_setting:
                        Toast.makeText(NotepadXActivity.this, R.string.menu_setting, Toast.LENGTH_SHORT).show();
                        return true;
                    case R.id.menu_nav_info:
                        Toast.makeText(NotepadXActivity.this, R.string.menu_info, Toast.LENGTH_SHORT).show();
                        return true;
                }
                return false;
            }
        });

        //Слушатель нажатия на CardView, открытие переданой заметки в новой активити
        fm.setFragmentResultListener(NotesListFragment.KEY_NOTE, this, new FragmentResultListener() {
            @Override
            public void onFragmentResult(@NonNull String requestKey, @NonNull Bundle result) {
                secondNote = result.getParcelable(NotesListFragment.ARG_NOTE);

                NoteDetailsFragment.newInstance(secondNote).show(getSupportFragmentManager(), NoteDetailsFragment.TAG);

//                Intent intent = new Intent(NotepadXActivity.this, NoteDetailsActivity.class);
//                intent.putExtra(NoteDetailsActivity.EXTRA_NOTE, secondNote);
//                startActivity(intent);

            }
        });
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        if (secondNote != null) {
            outState.putParcelable(ARG_SECOND_NOTE, secondNote);
        }
    }
}