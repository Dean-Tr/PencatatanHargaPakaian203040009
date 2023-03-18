package id.ac.unpas.pencatatanhargapakaian203040009.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.LiveData
import androidx.room.Room
import id.ac.unpas.pencatatanhargapakaian203040009.model.DataPakaian
import id.ac.unpas.pencatatanhargapakaian203040009.persistences.AppDatabase
import kotlinx.coroutines.flow.MutableStateFlow

@Composable
fun PencatatanPakaianScreen() {
    val context = LocalContext.current
    val db = Room.databaseBuilder(
        context, AppDatabase::class.java, "pencatatan-harga-baju"
    ).build()
    val dataPakaianDao = db.dataPakaianDao()

    val list: LiveData<List<DataPakaian>> = dataPakaianDao.loadAll()
    val items: List<DataPakaian> by list.observeAsState(initial = listOf())

    Column(modifier = Modifier.fillMaxWidth()) {

        FormHargaPakaian(dataPakaianDao)

        LazyColumn(modifier = Modifier.fillMaxWidth()) {
            items(items = items, itemContent = { item ->
                Row(
                    modifier = Modifier
                        .padding(15.dp)
                        .fillMaxWidth()
                ) {
                    Column(modifier = Modifier.weight(3f)) {
                        Text(text = "Brand", fontSize = 14.sp)
                        Text(text = item.brand, fontSize = 16.sp, fontWeight = FontWeight.Bold)
                    }
                    Column(modifier = Modifier.weight(3f)) {
                        Text(text = "Warna", fontSize = 14.sp)
                        Text(text = item.warna, fontSize = 16.sp, fontWeight = FontWeight.Bold)
                    }
                    Column(modifier = Modifier.weight(3f)) {
                        Text(text = "Ukuran", fontSize = 14.sp)
                        Text(text = item.ukuran, fontSize = 16.sp, fontWeight = FontWeight.Bold)
                    }
                    Column(modifier = Modifier.weight(3f)) {
                        Text(text = "Harga", fontSize = 14.sp)
                        Text(text = "Rp${item.harga}", fontSize = 16.sp, fontWeight = FontWeight.Bold)
                    }
                }
                Divider(modifier = Modifier.fillMaxWidth())
            })
        }
    }
}