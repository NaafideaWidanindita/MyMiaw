package com.example.aboutme

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.aboutme.ui.theme.BlurYellow
import com.example.aboutme.ui.theme.SemiYellow

// Profil Masing-masing kucingkuuu❣️😊😁
data class CatItem(val id: String, val name: String, val imageRes: Int)

val cats = listOf(
    CatItem("ID1", "Asep", R.drawable.cat_asep),
    CatItem("ID2", "Ucup", R.drawable.cat_ucup),
    CatItem("ID3", "Upin", R.drawable.cat_upin),
    CatItem("ID4", "Bing-bing", R.drawable.cat_bingbing),
    CatItem("ID5", "Slingky", R.drawable.cat_slingky),
    CatItem("ID6", "Cici", R.drawable.cat_cici),
    CatItem("ID7", "Muzza", R.drawable.cat_muzza),
    CatItem("ID8", "Blacky", R.drawable.cat_blacky),
    CatItem("ID9", "Nala", R.drawable.cat_nala),
    CatItem("ID10", "Bochil", R.drawable.cat_bochil)
)

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListScreen(navController: NavController) {
    var query by remember { mutableStateOf("") }

    val filteredItems = cats.filter {
        it.name.contains(query, ignoreCase = true)
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("My Cat List 😸", color = Color.Black) },
                colors = TopAppBarDefaults.smallTopAppBarColors(containerColor = BlurYellow)
            )
        },
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(SemiYellow)
                .padding(innerPadding)
                .padding(16.dp)
        ) {
            ListContent(
                query = query,
                onQueryChange = { query = it },
                filteredItems = filteredItems,
                navController = navController
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListContent(
    query: String,
    onQueryChange: (String) -> Unit,
    filteredItems: List<CatItem>,
    navController: NavController
) {
    TextField(
        value = query,
        onValueChange = onQueryChange,
        label = { Text("Search", color = Color.DarkGray) },
        colors = TextFieldDefaults.textFieldColors(
            containerColor = BlurYellow,
            focusedLabelColor = Color.Black,
            focusedTextColor = Color.Black,
            cursorColor = Color.Gray,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent
        ),
        leadingIcon = {
            Icon(
                painter = painterResource(id = R.drawable.ic_search),
                contentDescription = "Search Icon",
                tint = Color.Gray
            )
        },
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
    )

    Spacer(modifier = Modifier.height(16.dp))

    // Ini untuk LazyColumn
    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(max = 320.dp)
    ) {
        items(filteredItems) { cat ->
            ListItemView(
                cat = cat,
                onClick = {
                    navController.navigate("detail/${cat.id}")
                }
            )
        }
    }

    Spacer(modifier = Modifier.height(16.dp))

    // Ini untuk LazyRow
    LazyRow(
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(max = 200.dp)
    ) {
        items(filteredItems) { cat ->
            ListItemView(cat = cat, onClick = {
                navController.navigate("detail/${cat.id}")
            })
        }
    }
}

@Composable
fun ListItemView(cat: CatItem, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
            .clickable { onClick() },
        colors = CardDefaults.cardColors(containerColor = Color.Gray),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .padding(5.dp)
                .clickable { onClick() }
        ) {
            Image(
                painter = painterResource(id = cat.imageRes),
                contentDescription = cat.name,
                modifier = Modifier
                    .size(64.dp)
                    .padding(2.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .border(1.dp, Color.Gray, RoundedCornerShape(8.dp))
            )

            Spacer(modifier = Modifier.width(8.dp))

            Text(
                text = "Cat Profil ${cat.name}",
                color = Color.White,
                style = MaterialTheme.typography.bodySmall.copy(fontWeight = FontWeight.Bold),
                modifier = Modifier.padding(4.dp)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ListScreenPreview() {
    ListScreen(navController = rememberNavController())
}
