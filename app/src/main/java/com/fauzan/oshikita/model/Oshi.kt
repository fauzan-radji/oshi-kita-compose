package com.fauzan.oshikita.model

data class Oshi(
    val name: String,
    val nicknames: List<String>,
    val fanbase: String,
    val generation: Int,
    val jiko: String,
    val description: String,
    val photoUrl: String,
)

val dummyOshi = listOf(
    Oshi(
        name = "Adzana Shaliha Alifyaa",
        nicknames = listOf("Ashel", "Acel"),
        fanbase = "Ashelytic",
        generation = 9,
        jiko = "Aku datang bagaikan embun pagi yang menyejukkan suasana hari, aku Ashel!",
        description = "Adzana Shaliha atau akrab dipanggil Ashel ini merupakan anggota JKT48 dari generasi kesembilan. Ashel adalah keponakan dari basis Slank, Ivanka. Gadis yang mempunyai hobi senam ini juga pernah menjadi pemandu sorak. Ashel pertama kali diperkenalkan sebagai member JKT48 pada acara Joy Kick! Tears Handshake Festival pada 1 Desember 2019. Setelah melalui tes tahap pertama dan tahap kedua sebagai syarat kenaikan kelas, Ashel dipromosikan ke Akademi Kelas A pada tanggal 25 Januari 2020. Pada 12 Maret 2021 ia dipromosikan ke tim utama JKT48.",
        photoUrl = ""
    ),
    Oshi(
        name = "Angelina Christy",
        nicknames = listOf("Christy", "Kitoy", "Toya"),
        fanbase = "Christyzer",
        generation = 7,
        jiko = "Peduli dan berbaik hati, siapakah dia? Chris.. (ty!) Halo semuanya aku Christy.",
        description = "Angelina Christy (lahir 05 Desember 2005), dikenal dengan nama Christy JKT48, adalah seorang penyanyi asal Indonesia yang tergabung sebagai anggota grup idola JKT48. Ia merupakan anggota generasi ketujuh JKT48 yang diperkenalkan pada 29 September 2018. Jikoushokai salam perkenalan dari Christy di JKT48 adalah \"Peduli dan berbaik hati, siapa dia? Chriiiis-ty!\". Christy memiliki basis penggemar yang disebut Christyzer.",
        photoUrl = "" // Add Christy's photo URL here
    ),
    Oshi(
        name = "Azizi Shafaa Asadel",
        nicknames = listOf("Zee", "Azizi", "Zizoy", "Zoya"),
        fanbase = "Zeemotion",
        generation = 7,
        jiko = "Si gadis tomboi yang semangatnya meletup-letup! Panggil aku Zee!",
        description = "Azizi Shafaa Asadel (lahir 16 Mei 2004), dikenal dengan nama mononim Zee JKT48, adalah seorang penyanyi dan aktris asal Indonesia yang tergabung sebagai anggota grup idola JKT48. Ia merupakan anggota generasi ketujuh JKT48 yang diperkenalkan pada 29 September 2018.",
        photoUrl = "" // Add Zee's photo URL here
    ),
    Oshi(
        name = "Fiony Alveria Tantri",
        nicknames = listOf("Fiony", "Pio", "Ce Pio"),
        fanbase = "Symfiony",
        generation = 8,
        jiko = "Seperti simfoni yang menenangkan hati, halo aku Fiony!",
        description = "Fiony Alveria Tantri (lahir 4 Februari 2002), juga dikenal sebagai Fiony JKT48, adalah seorang penyanyi Indonesia dan anggota grup idola JKT48. Ia dikenal juga sebagai salah satu pelawak di JKT48 dikarenakan sifatnya. Ia juga terkenal dengan trio ubur-ubur bersama Zee dan Christy. Trio ini terkenal akan ilmu \"filsuf\" atau pertanyaan yang tak bisa dijawab dan random. Ia juga dijuluki random sebab tingkah lakunya yang tak masuk akal. Ia dan Freya sering dijuluki duo F. Ia dan Freya sering melakukan hal-hal lucu dan kocak. Diketahui, Ia kuliah di salah satu universitas di Jakarta dengan jurusan Desain Komunikasi Visual. Ia merupakan anggota generasi kedelapan JKT48 yang diperkenalkan pada 27 April 2019. Walaupun ia adalah gen kedelapan, ia sering disebut gen 7,5 sebab dekat dan menjadi salah satu center di formasi JKT48 New Era.",
        photoUrl = "" // Add Fiony's photo URL here
    ),
    Oshi(
        name = "Raden Roro Freyanashifa Jayawardana",
        nicknames = listOf("Freya", "Fre"),
        fanbase = "Freyanation",
        generation = 7,
        jiko = "Gadis koleris yang suka berimajinasi, terangi harimu dengan senyuman karamelku. Halo aku Freya!",
        description = "Raden Roro Freyanashifa Jayawardana (Jawa: ꦬꦢꦺꦤ꧀ꦬꦫ ꦦ꦳ꦿꦺꦪꦤꦯꦶꦥ꦳ꦗꦪꦮꦂꦝꦤ; pengucapan bahasa Jawa: [Radèn Rårå Frèyanaśifajåyåwardhånå]; lahir 13 Februari 2006), secara mononim dikenal sebagai Freya JKT48, adalah penyanyi dan penari asal Indonesia dan anggota grup idola JKT48 generasi ketujuh yang diperkenalkan pada 2018.",
        photoUrl = "" // Add Freya's photo URL here
    ),
    Oshi(
        name = "Gita Sekar Andarini",
        nicknames = listOf("Gita"),
        fanbase = "Gitroops",
        generation = 6,
        jiko = "Diam bukan berarti tak memperhatikanmu. Aku Gita.",
        description = "Gita Sekar Andarini (lahir 30 Juni 2001) yang dipanggil Gita (juga dikenal sebagai Gita JKT48) adalah salah seorang penyanyi Indonesia dan anggota dari grup idola JKT48[1] Jikoushokai (salam perkenalan) dari Gita di JKT48 adalah \"Diam bukan berarti tidak memperhatikanmu, aku Gita!\". Gita memiliki basis penggemar yang disebut Gitroops.",
        photoUrl = "" // Add Gita's photo URL here
    ),
    Oshi(
        name = "Marsha Lenathea Lapian",
        nicknames = listOf("Marsha", "Matcha", "Anime", "Maeng"),
        fanbase = "MarshaOshi",
        generation = 9,
        jiko = "Seperti pizza yang selalu dinanti-nantikan semua orang, selalu nantikan aku ya! Halo aku Marsha!",
        description = "Marsha Lenathea Lapian (lahir 9 Januari 2006), juga dikenal sebagai Marsha JKT48, adalah seorang penyanyi Indonesia dan anggota grup idola JKT48. Ia merupakan anggota generasi kesembilan JKT48 yang diperkenalkan pada 1 Desember 2019.",
        photoUrl = "" // Add Marsha's photo URL here
    ),
    Oshi(
        name = "Reva Fidela Adel Pantjoro",
        nicknames = listOf("Adel", "Dedel", "Reva"),
        fanbase = "Adellion",
        generation = 8,
        jiko = "Bagaikan kucing yang kalem, tapi selalu memikat hati kamu! Hai aku Adel.",
        description = "Reva Fidela Adel Pantjoro (lahir di Jakarta, 14 Juli 2006) yang biasa dipanggil Adel atau Reva (juga dikenal sebagai Adel JKT48 (selama menjadi anggota JKT48) adalah salah seorang penyanyi Indonesia dan anggota JKT48 yang berasal dari Jakarta, Indonesia. Adel merupakan anggota JKT48 generasi ke-8 yang diperkenalkan pada 27 April 2019. Jikoshoukai atau salam perkenalannya adalah \"Bagai kucing yang kalem, tapi selalu memikat hati kamu, hai aku Adel\"",
        photoUrl = "" // Add Adel's photo URL here
    ),
    Oshi(
        name = "Shani Indira Natio",
        nicknames = listOf("Shani"),
        fanbase = "Inshanity",
        generation = 3,
        jiko = "Semanis coklat selembut sutra. Hai aku Shani.",
        description = "Shani Indira Natio, S.I.Kom (lahir 5 Oktober 1998), secara mononim dikenal sebagai Shani JKT48, adalah seorang penyanyi dan penari Indonesia yang merupakan anggota grup idola JKT48. Ia merupakan anggota generasi ketiga JKT48 yang terpilih menjadi Kapten bersama Jinan Safa Safira sebagai Wakil Kapten pertama JKT48 New Era sejak tanggal 18 Desember 2021. Ia mengumumkan kelulusannya saat JKT48 Summer Festival yang digelar di Tennis Outdoor Senayan pada Minggu, 2 Juli 2023.",
        photoUrl = "" // Add Shani's photo URL here
    ),
    Oshi(
        name = "Yessica Tamara",
        nicknames = listOf("Chika", "Chikuy"),
        fanbase = "Never ending to Achieve Victory for Yessica (NAVY)",
        generation = 7,
        jiko = "Memberikan kebahagiaan lewat gummy smile-ku. Halo semuanya aku Chika.",
        description = "Yessica Tamara (lahir 24 September 2002) yang akrab disapa Chika JKT48 adalah salah seorang anggota dari grup idola JKT48 dari generasi ketujuh. Sebelum restrukturisasi Chika berada di Tim KIII. Chika memiliki adik dari generasi ketujuh yaitu Angelina Christy. Jikoushokai (salam perkenalan) dari Chika di JKT48 adalah \"Memberikan kebahagiaan lewat gummy smile-ku. Halo, aku Chika!\". Chika memiliki basis penggemar yang disebut NAVY(Neverending to Achieve Victory for Yessica)",
        photoUrl = "" // Add Chika's photo URL here
    )
)