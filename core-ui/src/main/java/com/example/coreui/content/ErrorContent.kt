package com.example.coreui.content

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CloudOff
import androidx.compose.material.icons.filled.ErrorOutline
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@Composable
fun ErrorContent(
    type: String,
    title: String,
    message: String,
    code: String,
    onRetry: (() -> Unit)?
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp), // Kenarlardan boşluk
        verticalArrangement = Arrangement.Center, // Dikeyde ortala
        horizontalAlignment = Alignment.CenterHorizontally // Yatayda ortala
    ) {
        // 1. DİNAMİK İKON (Hata tipine göre değişir)
        Icon(
            imageVector = getErrorIcon(type),
            contentDescription = null,
            modifier = Modifier.size(120.dp),
            tint = MaterialTheme.colorScheme.error // Tema rengi (Genelde Kırmızı)
        )

        Spacer(modifier = Modifier.height(24.dp))

        // 2. HATA BAŞLIĞI
        Text(
            text = title,
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onSurface,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(8.dp))

        // 3. HATA MESAJI (Kullanıcı Dostu)
        Text(
            text = message,
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.onSurfaceVariant, // Biraz daha gri
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(32.dp))

        // 4. TEKRAR DENE BUTONU
        onRetry?.let {
            Button(
                onClick = onRetry,
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.error
                )
            ) {
                Text(text = "Tekrar Dene")
            }
        }


        Spacer(modifier = Modifier.height(16.dp))

        // 5. TEKNİK BİLGİ (Opsiyonel - Developer veya Support için)
        // Kullanıcı bunu görmek zorunda değil ama screenshot atarsa işe yarar.
        Text(
            text = "Code: ${code} | Type: ${type}",
            style = MaterialTheme.typography.labelSmall,
            color = Color.Gray.copy(alpha = 0.5f),
            fontFamily = androidx.compose.ui.text.font.FontFamily.Monospace
        )
    }
}


private fun getErrorIcon(errorType: String): ImageVector {
    return when (errorType) {
        "NETWORK" -> Icons.Default.CloudOff
        "SERVER" -> Icons.Default.Warning
        else -> Icons.Default.ErrorOutline
    }
}