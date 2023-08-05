package com.notes.Adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.notes.Activity.UpdateNotesActivity;
import com.notes.MainActivity;
import com.notes.Model.Notes;
import com.notes.R;

import java.util.ArrayList;
import java.util.List;

public class NotesAdapter extends RecyclerView.Adapter<NotesAdapter.ViewHolder> {

    MainActivity mainActivity;
    List<Notes> notes;
    List<Notes> allNotesitem;

    public NotesAdapter(MainActivity mainActivity, List<Notes> notes) {

        this.mainActivity = mainActivity;
        this.notes = notes;
        allNotesitem = new ArrayList<>(notes);
    }

    public void searchNotes(List<Notes> filterredName) {
        this.notes = filterredName;
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        return new ViewHolder(LayoutInflater.from(mainActivity).inflate(R.layout.item_notes, parent, false));
    }

    @Override
    public void onBindViewHolder(NotesAdapter.ViewHolder holder, int position) {

        Notes note = notes.get(position);

        if (note.notesPriority.equals("1")) {
            holder.notesPriority.setBackgroundResource(R.drawable.green_shape);
        } else if (note.notesPriority.equals("2")) {
            holder.notesPriority.setBackgroundResource(R.drawable.yellow_shape);
        } else if (note.notesPriority.equals("3")) {
            holder.notesPriority.setBackgroundResource(R.drawable.red_shape);
        }

        holder.title.setText(note.notesTitle);
        holder.subtitle.setText(note.notesSubtitle);
        holder.notesDate.setText(note.notes);

        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(mainActivity, UpdateNotesActivity.class);

            intent.putExtra("id", note.id);
            intent.putExtra("title", note.notesTitle);
            intent.putExtra("subtitle", note.notesSubtitle);
            intent.putExtra("prority", note.notesPriority);
            intent.putExtra("note", note.notes);

            mainActivity.startActivity(intent);

        });

    }

    @Override
    public int getItemCount() {
        return notes.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        TextView title, subtitle, notesDate;
        View notesPriority;

        public ViewHolder(View itemView) {
            super(itemView);

            title = itemView.findViewById(R.id.notestitle);
            subtitle = itemView.findViewById(R.id.notestitle);
            notesDate = itemView.findViewById(R.id.notesdate);
            notesPriority = itemView.findViewById(R.id.notesPriority);

        }
    }

}
