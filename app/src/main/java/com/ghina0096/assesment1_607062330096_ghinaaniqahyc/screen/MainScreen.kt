package com.ghina0096.assesment1_607062330096_ghinaaniqahyc.screen

import android.app.DatePickerDialog
import android.content.Context
import android.content.Intent
import android.content.res.Configuration
import android.widget.DatePicker
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.ghina0096.assesment1_607062330096_ghinaaniqahyc.R
import com.ghina0096.assesment1_607062330096_ghinaaniqahyc.ui.theme.Assesment1_607062330096_GhinaAniqahYCTheme
import java.util.Calendar


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(navController: NavHostController) {
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
                        navController.navigate("aboutScreen")
                    }) {
                        Icon(
                            imageVector = Icons.Outlined.Info,
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
    val showDatePickerDialog = remember { mutableStateOf(false) }

    val genderOptions = listOf("Laki-laki", "Perempuan")

    if (showDatePickerDialog.value) {
        DatePickerDialog(
            context,
            { _: DatePicker, year: Int, month: Int, dayOfMonth: Int ->
                selectedDate = "%02d/%02d/%04d".format(dayOfMonth, month + 1, year)
                showDatePickerDialog.value = false
            },
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH)
        ).show()
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.buku),
            contentDescription = "Ilustrasi Buku",
            modifier = Modifier
                .size(150.dp)
                .padding(top = 8.dp)
        )

        Text(
            text = "Aplikasi ini digunakan untuk mencatat dan membagikan data diri kamu dengan mudah.",
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.padding(horizontal = 16.dp),
            color = Color.DarkGray
        )


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
            trailingIcon = {
                IconPicker(isError = namaError, unit = "✓")
            },
            keyboardOptions = KeyboardOptions.Default.copy(
                imeAction = ImeAction.Next,
                keyboardType = KeyboardType.Text
            ),
            modifier = Modifier.fillMaxWidth()
        )
        ErrorHint(
            isError = namaError,
            errorMessage = stringResource(id = R.string.error_nama_kosong)
        )

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
                IconButton(onClick = {
                    showDatePickerDialog.value = true
                }) {
                    Icon(
                        imageVector = Icons.Default.DateRange,
                        contentDescription = stringResource(id = R.string.icon_desc_tanggal)
                    )
                }
            },
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Next,
                keyboardType = KeyboardType.Number
            )
        )
        ErrorHint(
            isError = tanggalError,
            errorMessage = stringResource(id = R.string.error_tanggal_kosong)
        )

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
        ErrorHint(
            isError = genderError,
            errorMessage = stringResource(id = R.string.error_gender_kosong)
        )

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
                Text("Hasil Data Diri", fontWeight = FontWeight.Bold)
                Spacer(modifier = Modifier.height(8.dp))
                Text("Nama: $nama")
                Text("Tanggal Lahir: $selectedDate")
                Text("Jenis Kelamin: $selectedGender")

                Spacer(modifier = Modifier.height(16.dp))

                Button(
                    onClick = {
                        val message = """
                            Data Diri Saya:
                            - Nama: $nama
                            - Tanggal Lahir: $selectedDate
                            - Jenis Kelamin: $selectedGender
                        """.trimIndent()
                        shareData(context, message)
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFFCE93D8),
                        contentColor = Color.White
                    )
                ) {
                    Text("Bagikan Data")
                }
            }
        }
    }
}
@Composable
fun IconPicker(isError: Boolean, unit: String) {
    if (isError) {
        Icon(imageVector = Icons.Filled.Warning, contentDescription = null, tint = Color.Red)
    } else {
        Text(text = unit)
    }
}

@Composable
fun ErrorHint(isError: Boolean, errorMessage: String) {
    if (isError) {
        Text(text = errorMessage, color = Color.Red)
    }
}

private fun shareData(context: Context, message: String) {
    val shareIntent = Intent(Intent.ACTION_SEND).apply {
        type = "text/plain"
        putExtra(Intent.EXTRA_TEXT, message)
    }
    if (shareIntent.resolveActivity(context.packageManager) != null) {
        context.startActivity(shareIntent)
    }
}
@Composable
@Preview(showBackground = true)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES, showBackground = true)
fun MainScreenPreview() {
    Assesment1_607062330096_GhinaAniqahYCTheme {
        MainScreen(rememberNavController())
    }
}