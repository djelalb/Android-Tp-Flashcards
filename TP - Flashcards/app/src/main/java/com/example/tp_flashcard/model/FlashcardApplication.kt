package com.example.tp_flashcard.model

import android.app.Application
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.tp_flashcard.data.AppDatabase
import com.example.tp_flashcard.data.FlashcardDao
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class FlashcardApplication : Application() {
    companion object {
        lateinit var database: AppDatabase
            private set
    }

    override fun onCreate() {
        super.onCreate()
        database = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java,
            "flashcards-db"
        )
            .addCallback(object : RoomDatabase.Callback() {
                override fun onCreate(db: SupportSQLiteDatabase) {
                    super.onCreate(db)
                    CoroutineScope(Dispatchers.IO).launch {
                        val dao: FlashcardDao = database.flashcardDao()
                        dao.insertCategories(PrepopulateData.categories)
                        dao.insertFlashcards(PrepopulateData.flashcards)
                    }
                }
            })
            .build()
    }
}
