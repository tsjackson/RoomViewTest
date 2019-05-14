package com.example.roomviewtest;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface WordDao {
    @Insert(onConflict = OnConflictStrategy.ABORT)
    void insertWord(WordEntity word);

    @Query("SELECT * FROM WORD_TABLE ORDER BY word ASC")
    LiveData<List<WordEntity>> getAllWords();

    @Query("DELETE FROM WORD_TABLE")
    void deleteAll();
}
