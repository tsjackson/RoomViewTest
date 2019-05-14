package com.example.roomviewtest;

import android.app.Application;
import android.widget.Toast;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class ViewModel extends AndroidViewModel {

    private WordRepository wordRepository;
    private LiveData<List<WordEntity>> allWords;
    public static final int NEW_WORD_ACTIVITY_REQUEST_CODE = 1;

    public ViewModel( Application application) {
        super(application);
        wordRepository = new WordRepository(application);
        allWords = wordRepository.getAllResults();
    }

    public LiveData<List<WordEntity>> getAllWords() {
        return allWords;
    }

    public void insert(WordEntity wordEntity){
        wordRepository.insert(wordEntity);

    }

}
