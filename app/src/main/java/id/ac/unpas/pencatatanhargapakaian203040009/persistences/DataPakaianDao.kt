package id.ac.unpas.pencatatanhargapakaian203040009.persistences

import androidx.lifecycle.LiveData
import androidx.room.*
import id.ac.unpas.pencatatanhargapakaian203040009.model.DataPakaian

@Dao
interface DataPakaianDao {
    @Query("SELECT * FROM DataPakaian")
    fun loadAll(): LiveData<List<DataPakaian>>
    @Query("SELECT * FROM DataPakaian WHERE id = :id")
    fun find(id: String): DataPakaian?
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(vararg items: DataPakaian)
    @Delete
    fun delete(item: DataPakaian)
}