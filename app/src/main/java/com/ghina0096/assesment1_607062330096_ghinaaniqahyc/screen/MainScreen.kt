package com.ghina0096.assesment1_607062330096_ghinaaniqahyc.screen

import android.icu.util.Calendar
import android.widget.DatePicker
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ghina0096.assesment1_607062330096_ghinaaniqahyc.R
import com.ghina0096.assesment1_607062330096_ghinaaniqahyc.ui.theme.Assesment1_607062330096_GhinaAniqahYCTheme

@OptIn(ExperimentalMaterial3Api::class)

@Composable
fun MainScreen() {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Data Diri") },
                colors = TopAppBarDefaults.mediumTopAppBarColors(
                    containerColor = Color(0xFFE1BEE7),
                    titleContentColor = Color.White
                ),
                actions = {
                    IconButton(onClick = {

                    }) {
                        Icon(
                            imageVector = androidx.compose.material.icons.Icons.Outlined.Info,
                            contentDescription = "Tentang Aplikasi",
                            tint = Color.White
                        )
                    }
                }
            )
        }
    ) { paddingValues ->
        ScreenContent(modifier = Modifier.padding(paddingValues))
    }
}

@Composable
fun ScreenContent(modifier: Modifier = Modifier) {
    var nama by remember { mutableStateOf("") }
    var selectedGender by remember { mutableStateOf("") }
    var selectedDate by remember { mutableStateOf("") }

    var showResult by remember { mutableStateOf(false) }
    var namaError by remember { mutableStateOf(false) }
    var tanggalError by remember { mutableStateOf(false) }
    var genderError by remember { mutableStateOf(false) }

    val context = LocalContext.current
    val calendar = Calendar.getInstance()
    android.app.DatePickerDialog(
        context,
        { _: DatePicker, year: Int, month: Int, dayOfMonth: Int ->
            selectedDate = "%02d/%02d/%04d".format(dayOfMonth, month + 1, year)
        },
        calendar.get(Calendar.YEAR),
        calendar.get(Calendar.MONTH),
        calendar.get(Calendar.DAY_OF_MONTH)
    )

    val genderOptions = listOf("Laki-laki", "Perempuan")

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        OutlinedTextField(
            value = nama,
            onValueChange = {
                nama = it
                namaError = false
                showResult = false
            },
            label = { Text("Nama") },
            isError = namaError,
            singleLine = true,
            keyboardOptions = KeyboardOptions.Default.copy(
                imeAction = ImeAction.Next,
                keyboardType = KeyboardType.Text
            ),
            modifier = Modifier.fillMaxWidth()
        )
        if (namaError) {
            Text(
                text = "Nama tidak boleh kosong",
                color = MaterialTheme.colorScheme.error,
                style = MaterialTheme.typography.labelSmall,
                modifier = Modifier.align(Alignment.Start)
            )
        }

        OutlinedTextField(
            value = selectedDate,
            onValueChange = {
                val digitsOnly = it.filter { char -> char.isDigit() }.take(8)
                selectedDate = buildString {
                    for (i in digitsOnly.indices) {
                        append(digitsOnly[i])
                        if ((i == 1 || i == 3) && i != digitsOnly.lastIndex) append('/')
                    }
                }
                showResult = false
                tanggalError = false
            },
                    label = { Text(stringResource(id = R.string.label_tanggal_lahir)) },
            isError = tanggalError,
            trailingIcon = {
                Icon(
                    imageVector = Icons.Default.DateRange,
                    contentDescription = stringResource(id = R.string.icon_desc_tanggal)
                )
            },
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Next,
                keyboardType = KeyboardType.Number
            )
        )
        if (tanggalError) {
            Text(
                text = "Tanggal lahir tidak boleh kosong",
                color = MaterialTheme.colorScheme.error,
                style = MaterialTheme.typography.labelSmall,
                modifier = Modifier.align(Alignment.Start)
            )
        }


        Text(text = "Jenis Kelamin", style = MaterialTheme.typography.bodyLarge)
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            genderOptions.forEach { gender ->
                Row(verticalAlignment = Alignment.CenterVertically) {
                    RadioButton(
                        selected = selectedGender == gender,
                        onClick = {
                            selectedGender = gender
                            genderError = false
                            showResult = false
                        }
                    )
                    Text(text = gender)
                }
            }
        }
        if (genderError) {
            Text(
                text = "Pilih jenis kelamin terlebih dahulu",
                color = MaterialTheme.colorScheme.error,
                style = MaterialTheme.typography.labelSmall,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
        }

        Button(
            onClick = {
                namaError = nama.isBlank()
                tanggalError = selectedDate.isBlank()
                genderError = selectedGender.isBlank()
                showResult = !(namaError || tanggalError || genderError)
            },
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFFF48FB1),
                contentColor = Color.White
            )
        ) {
            Text("Lihat Hasil")
        }

        if (showResult) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text("Hasil Data Diri", fontWeight = androidx.compose.ui.text.font.FontWeight.Bold)
                Spacer(modifier = Modifier.height(8.dp))
                Text("Nama: $nama")
                Text("Tanggal Lahir: $selectedDate")
                Text("Jenis Kelamin: $selectedGender")
            }
        }
    }
}


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun MainScreenPreview() {
    Assesment1_607062330096_GhinaAniqahYCTheme {
        MainScreen()
    }
}