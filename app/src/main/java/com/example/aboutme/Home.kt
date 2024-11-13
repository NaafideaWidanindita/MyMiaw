package com.example.aboutme

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.aboutme.ui.theme.BlurYellow
import com.example.aboutme.ui.theme.SemiYellow

// Foto Semua Kucing
data class Cat(val id: String, val name: String, val imageRes: Int)

val cat = listOf(
    Cat("ID1", "Muzza,Meng", R.drawable.all1),
    Cat("ID2", "Ucup,Asep", R.drawable.all2),
    Cat("ID3", "Cici,Ucup,Upin & Sodara", R.drawable.all3),
    Cat("ID4", "Putih,Ucup,Asep", R.drawable.all4),
    Cat("ID5", "Cici,Gebetan Cici", R.drawable.all5),
    Cat("ID6", "Meng,Slingky,Muza,Black", R.drawable.all6),
    Cat("ID7", "Anak-anak Cici", R.drawable.all7),
    Cat("ID8", "Putih,Ucup,Asep", R.drawable.all8),
    Cat("ID9", "Anak-anak Slingky", R.drawable.all9),
    Cat("ID10", "Ucup,Asep,Ngek", R.drawable.all10),
    Cat("ID11", "Ucup,Upin", R.drawable.all11),
)

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen() {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("My Miaw 😊", color = Color.Black) },
                colors = TopAppBarDefaults.smallTopAppBarColors(containerColor = BlurYellow)
            )
        },
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(SemiYellow)
                .padding(innerPadding)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            HomeContent()
        }
    }
}

@Composable
fun HomeContent(){
    Text(
        text = "\n Selamat datang di My Miaw !! \n Ini adalah galeri mixx Miaw yang saya punya 😁 \n Buat profil masing masing Miaw lihat di List yak😊❣️",
        color = Color.Black,
        modifier = Modifier.padding(8.dp),
        style = MaterialTheme.typography.bodyMedium
    )

    Spacer(modifier = Modifier.height(16.dp))

    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        items(cat) { cat ->
            CatView(cat = cat)
        }
    }
}

@Composable
fun CatView(cat: Cat) {
    Card(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = Color.Gray),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(5.dp)
        ) {
            Image(
                painter = painterResource(id = cat.imageRes),
                contentDescription = cat.name,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(2.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .border(1.dp, Color.Gray, RoundedCornerShape(8.dp))
            )

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = "${cat.name}",
                color = Color.White,
                style = MaterialTheme.typography.bodySmall.copy(fontWeight = FontWeight.Bold),
                modifier = Modifier
                    .padding(4.dp)
                    .align(Alignment.CenterHorizontally)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    HomeScreen()
}
