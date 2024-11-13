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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.aboutme.ui.theme.BlurYellow
import com.example.aboutme.ui.theme.SemiYellow

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailScreen(itemId: String, navController: NavHostController) {
    val cat = cats.firstOrNull { it.id == itemId }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Detail Miaw ${cat?.name ?: "Unknown"} 😸", color = Color.Black) },
                colors = TopAppBarDefaults.smallTopAppBarColors(containerColor = BlurYellow),
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = null, tint = Color.Black)
                    }
                },
            )
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(SemiYellow)
                .padding(top = 80.dp, start = 16.dp, end = 16.dp, bottom = 16.dp)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            DetailContent(cat = cat)
        }
    }
}

@Composable
fun DetailContent(cat: CatItem?) {
    // Profil Miaw
    val descriptions = mapOf(
        "ID1" to "\n   Asep itu dulu waktu kecil dibuang sama tetanggaku bareng Ucup dan Putih di depan jalan rumahku,trus dia meong meong kerasssss banget,terus pas aku temuin dia udah kedinginan di bawah mobil gitu😭😭.Kasian banget trus akhirnya ku pungut. \n   Terus beranjak besar si asep ini nakal bgd,dia ngajak putih lari larian di jalan akhirnya siputih ke tabrak🫂🙏,terus sekarang asep jadi yg paling nurut diantara semua kucingku. Kayaknya dia trauma deh😭",
        "ID2" to "\n   Ucup itu dulu waktu kecil dibuang sama tetanggaku bareng Asep dan Putih di depan jalan rumahku,trus dia meong meong kerasssss banget,terus pas aku temuin dia udah kedinginan di bawah mobil gitu😭😭.Kasian banget trus akhirnya ku pungut. \n   Ucup kucing paling normall sih dari yg aku punya,dia nurut bgd,ngeong-ngeong pas laper aja xixi..😁,ucuppp juga jadi pengganti ibu sekaligus ayah buat Upin karena waktu Bu Cici masih hidup,Ucup selalu ikut nyusu ke Cici bareng Upin dan sodaranya🫂",
        "ID3" to "\n   Upin satu satunya anak Cici yang hidup sampai hari ini.Setelah kematian cici karena di keroyok anjing,satu persatu anaknya mati,tapi upin berhasil bertahan sampe sekarang ❣️,\n   tapi gatau kenapa kayaknya Upin ada kelainan deh,jadi badannya sampe sekarang kecill terus gabisa tambah besar padahal dia udah 2 tahun lebih.",
        "ID4" to "\n   Bing-Bing awalnya kucing jalanan yang dipungut sama tetanggaku,tapi semenjak tetanggaku ini punya anjing, si Bing-Bing jadi gapernah di urus,bahkan yang dikasi makan cuma anjingnya tetanggaku ini doang. \n   Terus akhirnya Bing-Bing selalu main ke rumahku,kukasi makan. Dan Sekarang dia kuangkat jadi kucingkuu 😸😁",
        "ID5" to "\n   Slingky ini kucing ke 2 ku setelah Muzza,dia kucing teman adikku,tapi karena kucingnya udah banyak akhirnya dia dikasi ke aku😁❤️,umurnya kurang lebih sama seperti Muzza,kalau Muzza masih hidup mungkin dia juga udah punya anak seperti slingky.😭 \n   Oiyaa... dulu bulunya Slingky itu jelekkkk bgddddd izz,kalo kutunjukin foto kecilnya pasti kek kucing gk ke urus,tapi sekarang dah makin mbulll dan cantiqq dia,anaknya juga dah banyak",
        "ID6" to "\n   Cici ini kucing adikku yg tinggal di bali,aku gatau banyak cerita tentang dia.Dia meninggal karena dikeroyok anjing😭,dan anaknya yg masih hidup sampai sekarang si Upin😭😭 Sehat sehat selalu Upin.",
        "ID7" to "\n   Muzza ini kucing pertama yang aku punya ❤️,dia hasil persilangan kucing kampung dan persia.Dari pemilik sebelumnya katanya ibunya mati setelah melahirkan Muzza dan saudara saudaranya,akhirnya mereka di berikan ke orang orang yang mau ,karena pemilik sebelumnya tidak bisa merawat. \n   Akhirnya dia jadi punya aku. 😁😁🫂\n   Sampai pada akhirnya saat umurnya jalan 2 tahun ,dia mulai jarang pulang (mulai kenal dunia malam),trus pulang pulang ternyata dia sakit dan meninggal saat tidur di pangkuanku😭🫂",
        "ID8" to "\n   Blacky duluuu juga dari temen adekku,aku lupaa kenapa dia dikasi ke aku,apa krn dia item yak😭,tapiii blacky Gantengggg bgd,meski kecilnya kayak gaada buluuu,gapunya aliss,,,besarnya dia bulunya tebell klimis kayak di iklan-iklan shampo👨🏿‍🦱. \n   Tapii abis umurnya setahunan dia mulai jarang pulang mungkin dia udah nemu pasangannya🫂dan dia lupain aku,kadang aku cuma liat dia lewat dpn rumah,tapi dia gamau diajak masuk.",
        "ID9" to "\n   Nala ,,dia dulu ku dapet dari grup adopsi kucing fb, pemilik sebelumnya baik banget mau nganter ke rumah,dari awal sampe ternyata si Nala ini cantikkk bgd, bulunya bagus dia nurut ❤️🙏,\n   nala dia 3 bersaudara ,nala aku aku adopsi,kelly diadopsi temenku,dan satu lagi tanpa nama dia di adopt ustadzku(gatau dikasi nama siapa)",
        "ID10" to "\n   Bochil ini kucing adikku yg tinggal di bali, kasian bgd katanya dari lahir matanya satu gabisa liat,dia juga pernah beberapa kali mau dibuang sama ayahku😭,tapi diambil lagi karena kasian😭😁. \n   Tapi sekarang dia malah ngilang gatau kemana.",
    )

    if (cat != null) {
        Image(
            painter = painterResource(id = cat.imageRes),
            contentDescription = cat.name,
            modifier = Modifier
                .size(200.dp)
                .padding(8.dp)
                .clip(RoundedCornerShape(8.dp))
                .border(1.dp, Color.Gray, RoundedCornerShape(8.dp))
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Nama Miaww
        Text(
            "Nama: ${cat.name}",
            color = Color.Black,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold)

        Spacer(modifier = Modifier.height(8.dp))

        val description = descriptions[cat.id.toString()] ?: "No description available."

        // Deskripsi Miaw
        Column(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalAlignment = Alignment.Start
        ) {
            Text(
                "$description",
                color = Color.Black)
        }
    } else {
        Text(
            "Cat not found.",
            color = Color.Black)
    }
}

@Preview(showBackground = true)
@Composable
fun DetailScreenPreview() {
    val navController = rememberNavController()
    DetailScreen(itemId = "ID1", navController = navController)
}
