package id.ac.unpas.pencatatanhargapakaian203040009.persistences

import androidx.room.Database
import androidx.room.RoomDatabase
import id.ac.unpas.pencatatanhargapakaian203040009.model.DataPakaian

@Database(entities = [DataPakaian::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun dataPakaianDao(): DataPakaianDao
}