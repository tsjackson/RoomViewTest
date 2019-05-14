package com.example.roomviewtest;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = {WordEntity.class}, version = 1)
public abstract class RoomDatabase extends androidx.room.RoomDatabase {

    private static volatile RoomDatabase INSTANCE;
    public abstract WordDao wordDao();
    private static RoomDatabase.Callback sRoomDataBaseCallback =

            new RoomDatabase.Callback(){

        @Override
                public void onOpen(@NonNull SupportSQLiteDatabase db){
            super.onOpen(db);
            new PopulateDBAsync(INSTANCE).execute();
        }
            };
    private static class PopulateDBAsync extends AsyncTask<Void,Void,Void>{
        private final WordDao mDao;

        PopulateDBAsync(RoomDatabase db){
            mDao = db.wordDao();
        }
        @Override
        protected Void doInBackground(final Void... params) {
            mDao.deleteAll();
            WordEntity word = new WordEntity("Hello");
            mDao.insertWord(word);
            word = new WordEntity ("World");
            mDao.insertWord(word);
            return null;
        }
    }
    private static RoomDatabase.Callback sRoomDatabaseCallback =
            new RoomDatabase.Callback(){

                @Override
                public void onOpen (@NonNull SupportSQLiteDatabase db){
                    super.onOpen(db);
                    new PopulateDBAsync(INSTANCE).execute();
                }
            };

    static RoomDatabase getInstance(final Context context){
        if(INSTANCE == null){
            synchronized (RoomDatabase.class){
                if(INSTANCE == null){
                    INSTANCE = Room.databaseBuilder(
                            context.getApplicationContext(),
                            RoomDatabase.class,
                            "word_db")
                            .addCallback(sRoomDatabaseCallback)
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}
