package com.notes.Dao;

import androidx.lifecycle.LiveData;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.notes.Model.Notes;

import java.util.List;

@androidx.room.Dao
public interface NotesDao {

    @Query("SELECT * FROM Notes_database ")
    LiveData<List<Notes>> getallNotes();

    @Query("SELECT * FROM Notes_database ORDER BY notes_priority DESC")
    LiveData<List<Notes>> highToLow();

    @Query("SELECT * FROM Notes_database ORDER BY notes_priority ASC")
    LiveData<List<Notes>> lowToHigh();

    //List<Notes> getallNotes();

    @Insert
    void insertNotes(Notes...notes);

    @Query("DELETE FROM Notes_database WHERE id=:id")
    void deleteNotes(int id);

    @Update
    void updateNotes(Notes notes);
}
