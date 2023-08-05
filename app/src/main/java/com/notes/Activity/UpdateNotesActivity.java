package com.notes.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.notes.Model.Notes;
import com.notes.R;
import com.notes.ViewModel.NotesViewModel;
import com.notes.databinding.ActivityUpdateNotesBinding;

import java.util.Date;

public class UpdateNotesActivity extends AppCompatActivity {

    ActivityUpdateNotesBinding binding;
    String priority = "1";
    String stitle, ssubtitle, snotes, sprority;
    NotesViewModel notesViewModel;
    int iid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityUpdateNotesBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        notesViewModel = ViewModelProviders.of(this).get(NotesViewModel.class);

        iid = getIntent().getIntExtra("id", 0);
        stitle = getIntent().getStringExtra("title");
        ssubtitle = getIntent().getStringExtra("subtitle");
        sprority = getIntent().getStringExtra("prority");
        snotes = getIntent().getStringExtra("note");

        binding.uptite.setText(stitle);
        binding.upsubtitle.setText(ssubtitle);
        binding.upnote.setText(snotes);

        if (sprority.equals("1")) {
            binding.greenPriority.setImageResource(R.drawable.ic_done);
        } else if (sprority.equals("2")) {
            binding.yellowPriority.setImageResource(R.drawable.ic_done);
        } else if (sprority.equals("3")) {
            binding.redPriority.setImageResource(R.drawable.ic_done);
        }

        binding.greenPriority.setOnClickListener(v -> {

            binding.greenPriority.setImageResource(R.drawable.ic_done);
            binding.yellowPriority.setImageResource(0);
            binding.redPriority.setImageResource(0);

            priority = "1";
        });

        binding.yellowPriority.setOnClickListener(v -> {

            binding.greenPriority.setImageResource(0);
            binding.yellowPriority.setImageResource(R.drawable.ic_done);
            binding.redPriority.setImageResource(0);

            priority = "2";
        });

        binding.redPriority.setOnClickListener(v -> {

            binding.greenPriority.setImageResource(0);
            binding.yellowPriority.setImageResource(0);
            binding.redPriority.setImageResource(R.drawable.ic_done);

            priority = "3";
        });

        binding.updateNotesBtn.setOnClickListener(v -> {

            String title = binding.uptite.getText().toString();
            String subtitle = binding.upsubtitle.getText().toString();
            String notes = binding.upnote.getText().toString();

            UpdateNotes(title, subtitle, notes);


        });
    }

    private void UpdateNotes(String title, String subtitle, String notes) {

        Date date = new Date();
        CharSequence sequence = DateFormat.format("MMM d, yyy", date.getTime());

        Notes updateNotes = new Notes();

        updateNotes.id = iid;
        updateNotes.notesTitle = title;
        updateNotes.notesSubtitle = subtitle;
        updateNotes.notes = notes;
        updateNotes.notesPriority = priority;
        updateNotes.noteDate = sequence.toString();

        notesViewModel.updateNotes(updateNotes);

        Toast.makeText(UpdateNotesActivity.this, "Notes Updated Successfuly", Toast.LENGTH_SHORT).show();
        finish();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.delete_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if (item.getItemId() == R.id.delete) {

            BottomSheetDialog sheetDialog = new BottomSheetDialog(UpdateNotesActivity.this, R.style.BottomSheetStyle);

            View view = LayoutInflater.from(UpdateNotesActivity.this).inflate(R.layout.delete_bottom_sheet, (LinearLayout) findViewById(R.id.bottomsheet));

            sheetDialog.setContentView(view);

            TextView yes, no;

            yes = view.findViewById(R.id.delete_yes);
            no = view.findViewById(R.id.delete_no);

            yes.setOnClickListener(v -> {

                notesViewModel.deleteNotes(iid);
                finish();

            });

            no.setOnClickListener(v -> {

                sheetDialog.dismiss();
            });

            sheetDialog.show();

        }

        return true;
    }
}