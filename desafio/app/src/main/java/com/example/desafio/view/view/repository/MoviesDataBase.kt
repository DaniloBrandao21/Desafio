package com.example.desafio.view.view.repository

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.desafio.view.view.model.MoviesModel

@Database (entities = [MoviesModel::class], version = 1)
abstract class MoviesDataBase() : RoomDatabase() {

    abstract fun moviesDAO(): MoviesDAO
    companion object {
        private lateinit var INSTANCE: MoviesDataBase
        fun getDataBase(context: Context): MoviesDataBase {
            if (!::INSTANCE.isInitialized) {
                synchronized(MoviesDataBase::class) {
                    INSTANCE = Room.databaseBuilder(context, MoviesDataBase::class.java, "moviesDB")
                        .addMigrations(MIGRATION_1_2)
                        .allowMainThreadQueries()
                        .build()
                }
            }
            return INSTANCE
        }

        private val MIGRATION_1_2: Migration = object : Migration(1,2){
            override fun migrate(database: SupportSQLiteDatabase) {
             database.execSQL("Delete from movies")
            }

        }
    }
}