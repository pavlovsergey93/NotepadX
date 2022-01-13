package com.gmail.pavlovsv93.notepadx;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.gmail.pavlovsv93.notepadx.domain.Notes;

import java.util.ArrayList;
import java.util.Collection;

public class NotesAdapter extends RecyclerView.Adapter<NotesAdapter.NoteViewHolder> {

    public NotesAdapter(Fragment fragment) {
        this.fragment = fragment;
    }

    public interface OnClick {
        void onClick(Notes note);
        void onLongClick(Notes note);
    }

    private Fragment fragment;

    private ArrayList<Notes> listData = new ArrayList<>();

    private OnClick onClick;

    public void setListData(Collection<Notes> notes) {
        listData.clear();
        listData.addAll(notes);
    }

    public int addNotes(Notes note) {
        listData.add(note);
        return listData.size();
    }

    public int updateNote(Notes note) {
        for (int i = 0; i < listData.size(); i++) {
            if (listData.get(i).getId().equals(note.getId())){
                listData.set(i,note);
                return i;
            }
        }
        return 1;
    }

    @NonNull
    @Override
    public NoteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.item_note, parent, false);
        return new NoteViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull NoteViewHolder holder, int position) {
        Notes note = listData.get(position);

        holder.getTitle().setText(note.getTitle());
        holder.getMassage().setText(note.getMassage());
        holder.getTime().setText(note.getTime());

    }

    @Override
    public int getItemCount() {
        return listData.size();
    }

    public OnClick getOnClick() {
        return onClick;
    }

    public void setOnClick(OnClick onClick) {
        this.onClick = onClick;
    }

    class NoteViewHolder extends RecyclerView.ViewHolder {

        private TextView title;
        private TextView massage;
        private TextView time;

        public NoteViewHolder(@NonNull View itemView) {
            super(itemView);

            CardView cardView = itemView.findViewById(R.id.card_view);

            fragment.registerForContextMenu(cardView);

            title = itemView.findViewById(R.id.card_title);
            massage = itemView.findViewById(R.id.card_massage);
            time = itemView.findViewById(R.id.card_time);

            //Однократное нажатие на Card
            cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Notes note = listData.get(getAdapterPosition());
                    if (getOnClick() != null) {
                        getOnClick().onClick(note);
                    }
                }
            });

            //Длительное нажатие на Card, для вызова контекстного меню
            cardView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    cardView.showContextMenu();
                    Notes note = listData.get(getAdapterPosition());
                    if(getOnClick() != null){
                        getOnClick().onLongClick(note);
                    }
                    return true;
                }
            });


        }

        public TextView getTitle() {
            return title;
        }

        public TextView getMassage() {
            return massage;
        }

        public TextView getTime() {
            return time;
        }

    }

}
