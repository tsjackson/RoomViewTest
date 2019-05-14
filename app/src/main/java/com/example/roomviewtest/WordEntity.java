package com.example.roomviewtest;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "WORD_TABLE")
public class WordEntity {
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "word")
    public String word;

    public WordEntity(String word){
        this.word = word;
    }
    public String getWord(){
        return word;
    }
}
