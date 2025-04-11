package com.ghina0096.assesment1_607062330096_ghinaaniqahyc.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
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
    val context = LocalContext.current
    var nama by remember { mutableStateOf("") }
    var selectedGender by remember { mutableStateOf("") }
    val genderOptions = listOf(
        stringResource(id = R.string.gender_laki_laki),
        stringResource(id = R.string.gender_perempuan)
    )
    var selectedDate by remember { mutableStateOf("") }
    var showResult by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(stringResource(id = R.string.app_name)) },
                colors = TopAppBarDefaults.mediumTopAppBarColors(
                    containerColor = Color(0xFFE1BEE7),
                    titleContentColor = Color.White
                )
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
                .padding(paddingValues),
            verticalArrangement = Arrangement.spacedBy(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            OutlinedTextField(
                value = nama,
                onValueChange = {
                    nama = it
                    showResult = false
                },
                label = { Text(stringResource(id = R.string.label_nama)) },
                singleLine = true,
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Next,
                    keyboardType = KeyboardType.Text
                ),
                modifier = Modifier.fillMaxWidth()
            )

            OutlinedTextField(
                value = selectedDate,
                onValueChange = {
                    val digits = it.filter { char -> char.isDigit() }
                    val formatted = buildString {
                        for (i in digits.indices) {
                            append(digits[i])
                            if ((i == 1 || i == 3) && i != digits.lastIndex) {
                                append('/')
                            }
                        }
                    }
                    selectedDate = formatted
                    showResult = false
                },
                label = { Text(stringResource(id = R.string.label_tanggal_lahir)) },
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

            Text(
                text = stringResource(id = R.string.label_jenis_kelamin),
                style = MaterialTheme.typography.bodyLarge
            )

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
                                showResult = false
                            }
                        )
                        Text(text = gender)
                    }
                }
            }

            Button(
                onClick = {
                    showResult = true
                }
            ) {
                Text(stringResource(id = R.string.lihat_hasil))
            }

            if (showResult) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = stringResource(id = R.string.hasil_data_diri),
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = stringResource(id = R.string.hasil_nama, nama),
                        textAlign = TextAlign.Center
                    )
                    Text(
                        text = stringResource(id = R.string.hasil_tanggal_lahir, selectedDate),
                        textAlign = TextAlign.Center
                    )
                    Text(
                        text = stringResource(id = R.string.hasil_jenis_kelamin, selectedGender),
                        textAlign = TextAlign.Center
                    )
                }
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