package id.ac.unpas.pencatatanhargapakaian203040009.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class DataPakaian(
    @PrimaryKey val id: String,
    val brand: String,
    val warna: String,
    val ukuran: String,
    val harga: String
)
