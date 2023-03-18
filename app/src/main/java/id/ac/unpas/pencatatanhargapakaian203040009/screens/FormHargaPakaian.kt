package id.ac.unpas.pencatatanhargapakaian203040009.screens

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.benasher44.uuid.uuid4
import id.ac.unpas.pencatatanhargapakaian203040009.model.DataPakaian
import id.ac.unpas.pencatatanhargapakaian203040009.persistences.DataPakaianDao
import id.ac.unpas.pencatatanhargapakaian203040009.ui.theme.Purple700
import id.ac.unpas.pencatatanhargapakaian203040009.ui.theme.Teal200
import kotlinx.coroutines.launch

@Composable
fun FormHargaPakaian(dataPakaianDao: DataPakaianDao) {
    val brand = remember { mutableStateOf(TextFieldValue("")) }
    val warna = remember { mutableStateOf(TextFieldValue("")) }
    val ukuran = remember { mutableStateOf(TextFieldValue("")) }
    val harga = remember { mutableStateOf(TextFieldValue("")) }

    val scope = rememberCoroutineScope()

    val context = LocalContext.current

    Column(
        modifier = Modifier
            .padding(10.dp)
            .fillMaxWidth()
    ) {

        OutlinedTextField(
            label = { Text(text = "Brand") },
            value = brand.value,
            onValueChange = { brand.value = it },
            modifier = Modifier
                .padding(4.dp)
                .fillMaxWidth(),
            placeholder = { Text(text = "GUCCI") }
        )

        OutlinedTextField(
            label = { Text(text = "Warna") },
            value = warna.value,
            onValueChange = { warna.value = it },
            modifier = Modifier
                .padding(4.dp)
                .fillMaxWidth(),
            keyboardOptions = KeyboardOptions(
                capitalization = KeyboardCapitalization.Characters,
                keyboardType = KeyboardType.Text
            ),
            placeholder = { Text(text = "Merah") }
        )

        OutlinedTextField(
            label = { Text(text = "Ukuran") },
            value = ukuran.value,
            onValueChange = { ukuran.value = it },
            modifier = Modifier
                .padding(4.dp)
                .fillMaxWidth(),
            keyboardOptions = KeyboardOptions(
                capitalization = KeyboardCapitalization.Characters,
                keyboardType = KeyboardType.Text
            ),
            placeholder = { Text(text = "S, M, L, XL, XXL") }
        )

        OutlinedTextField(
            label = { Text(text = "Harga") },
            value = harga.value,
            onValueChange = { harga.value = it },
            modifier = Modifier
                .padding(4.dp)
                .fillMaxWidth(),
            keyboardOptions = KeyboardOptions(
                keyboardType =
                KeyboardType.Decimal
            ),
            placeholder = { Text(text = "5000000") }
        )

        val loginButtonColors = ButtonDefaults.buttonColors(
            backgroundColor = Purple700,
            contentColor = Teal200
        )
        val resetButtonColors = ButtonDefaults.buttonColors(
            backgroundColor = Teal200,
            contentColor = Purple700
        )

        Row(
            modifier = Modifier
                .padding(4.dp)
                .fillMaxWidth()
        ) {
            Button(
                modifier = Modifier.weight(5f),
                onClick = {
                    val id = uuid4().toString()
                    val item = DataPakaian(
                        id,
                        brand.value.text,
                        warna.value.text,
                        ukuran.value.text,
                        harga.value.text
                    )
                    if (brand.value.text.isBlank()) {
                        Toast.makeText(context, "Brand harus diisi", Toast.LENGTH_LONG).show()
                    } else if (warna.value.text.isBlank()) {
                        Toast.makeText(context, "Warna harus diisi", Toast.LENGTH_LONG).show()
                    } else if (ukuran.value.text.isBlank()) {
                        Toast.makeText(context, "Ukuran harus diisi", Toast.LENGTH_LONG).show()
                    } else if (harga.value.text.isBlank()) {
                        Toast.makeText(context, "Harga harus diisi", Toast.LENGTH_LONG).show()
                    } else {
                        scope.launch {
                            dataPakaianDao.insertAll(item)
                        }
                        brand.value = TextFieldValue("")
                        warna.value = TextFieldValue("")
                        ukuran.value = TextFieldValue("")
                        harga.value = TextFieldValue("")
                    }

                }, colors = loginButtonColors
            ) {
                Text(
                    text = "Simpan",
                    style = TextStyle(
                        color = Color.White,
                        fontSize = 18.sp
                    ), modifier = Modifier.padding(8.dp)
                )
            }

            Button(
                modifier = Modifier.weight(5f),
                onClick = {
                    brand.value = TextFieldValue("")
                    warna.value = TextFieldValue("")
                    ukuran.value = TextFieldValue("")
                    harga.value = TextFieldValue("")
                }, colors = resetButtonColors
            ) {
                Text(
                    text = "Reset",
                    style = TextStyle(
                        color = Color.White,
                        fontSize = 18.sp
                    ), modifier = Modifier.padding(8.dp)
                )
            }
        }
    }
}