package com.example.kalkulatorsederhana

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.kalkulatorsederhana.ui.theme.KalkulatorSederhanaTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            KalkulatorSederhanaTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    CalculatorScreen(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun CalculatorScreen(modifier: Modifier = Modifier) {
    var num1 by remember { mutableStateOf("") }
    var num2 by remember { mutableStateOf("") }
    var operator by remember { mutableStateOf("") }
    var result by remember { mutableStateOf("") }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(
            text = "=== KALKULATOR SEDERHANA ===",
            style = MaterialTheme.typography.headlineSmall
        )

        OutlinedTextField(
            value = num1,
            onValueChange = { num1 = it },
            label = { Text("Masukkan angka pertama") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = operator,
            onValueChange = { operator = it },
            label = { Text("Masukkan operator (+, -, *, /)") },
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = num2,
            onValueChange = { num2 = it },
            label = { Text("Masukkan angka kedua") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier.fillMaxWidth()
        )

        Button(
            onClick = {
                val n1 = num1.toDoubleOrNull()
                val n2 = num2.toDoubleOrNull()

                if (n1 == null || n2 == null) {
                    result = "Error: Masukkan angka yang valid"
                    return@Button
                }

                val calculationResult = when (operator) {
                    "+" -> (n1 + n2).toString()
                    "-" -> (n1 - n2).toString()
                    "*" -> (n1 * n2).toString()
                    "/" -> {
                        if (n2 != 0.0) (n1 / n2).toString()
                        else "Error: Tidak bisa dibagi dengan nol!"
                    }
                    else -> "Operator tidak valid!"
                }
                result = "Hasil: $calculationResult"
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Hitung")
        }

        if (result.isNotEmpty()) {
            Text(
                text = result,
                style = MaterialTheme.typography.titleLarge,
                color = if (result.startsWith("Error")) MaterialTheme.colorScheme.error else MaterialTheme.colorScheme.primary
            )
        }
    }
}
