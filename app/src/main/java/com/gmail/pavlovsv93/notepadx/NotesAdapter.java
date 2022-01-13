package com.gmail.pavlovsv93.notepadx;

import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.gmail.pavlovsv93.notepadx.domain.Notes;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class NotesAdapter extends RecyclerView.Adapter<NotesAdapter.NoteViewHolder> {

    private ArrayList<Notes> listData = new ArrayList<>();

    private OnClick onClick;

    public void setListData(Collection<Notes> notes) {
        listData.clear();
        listData.addAll(notes);
    }

    @NonNull
    @Override
    public NoteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_note, parent, false);
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
    public interface OnClick{
        void onClick(Notes note);
    }

    class NoteViewHolder extends RecyclerView.ViewHolder {

        private TextView title;
        private TextView massage;
        private TextView time;

        public NoteViewHolder(@NonNull View itemView) {
            super(itemView);

            title = itemView.findViewById(R.id.card_title);
            massage = itemView.findViewById(R.id.card_massage);
            time = itemView.findViewById(R.id.card_time);

            itemView.findViewById(R.id.card_view).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Notes note = listData.get(getAdapterPosition());
                    if(getOnClick() != null) {
                        getOnClick().onClick(note);
                    }
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
