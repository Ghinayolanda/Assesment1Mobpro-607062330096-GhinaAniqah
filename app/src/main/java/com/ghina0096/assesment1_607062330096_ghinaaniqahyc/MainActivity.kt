package com.ghina0096.assesment1_607062330096_ghinaaniqahyc

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.ghina0096.assesment1_607062330096_ghinaaniqahyc.navigation.SetupNavGraph
import com.ghina0096.assesment1_607062330096_ghinaaniqahyc.screen.MainScreen
import com.ghina0096.assesment1_607062330096_ghinaaniqahyc.ui.theme.Assesment1_607062330096_GhinaAniqahYCTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Assesment1_607062330096_GhinaAniqahYCTheme {
                SetupNavGraph()
                }
            }
        }
    }

