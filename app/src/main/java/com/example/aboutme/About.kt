package com.example.aboutme

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.aboutme.ui.theme.BlurYellow
import com.example.aboutme.ui.theme.SemiYellow

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AboutScreen() {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("About Me 🧕", color = Color.Black) },
                colors = TopAppBarDefaults.smallTopAppBarColors(containerColor = BlurYellow)
            )
        },
        modifier = Modifier.background(SemiYellow)
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(SemiYellow)
                .padding(innerPadding)
                .padding(16.dp)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(8.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.profil),
                    contentDescription = "Profile Picture",
                    modifier = Modifier
                        .width(120.dp)
                        .height(120.dp)
                        .padding(end = 16.dp)
                        .clip(CircleShape)
                        .border(2.dp, Color.DarkGray, CircleShape)
                        .aspectRatio(1f)
                )

                Column(
                    verticalArrangement = Arrangement.Center
                ) {
                    Text("Nama : Naafi'dea Widanindita", color = Color.Black)
                    Text("Email: deawida10@gmail.com", color = Color.Black)
                    Text("Phone: 085-XXX-XXXX", color = Color.Black)
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Politeknik Negeri Bali", color = Color.Black)
                Text("Jurusan Teknologi Informasi", color = Color.Black)
            }

            Spacer(modifier = Modifier.height(24.dp))

            Text(
                text = """     Hai! Nama saya Naafidea Widanindita, biasa dipanggil Dea. Saya sangat senang bisa mendapatkan kesempatan untuk mengikuti Program Study Independen di Infinite Learning, di program Mobile Development, saya berharap pengalaman ini bisa memberikan pengalaman positif dan bermanfaat untuk masa depan saya.
                    
Ya, begitulah kira-kira, semangat selalu! 😊""",
                color = Color.Black,
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.padding(8.dp)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AboutScreenPreview() {
    AboutScreen()
}
