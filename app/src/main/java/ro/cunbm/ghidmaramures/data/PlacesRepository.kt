package ro.cunbm.ghidmaramures.data

import ro.cunbm.ghidmaramures.R

/**
 * Sursa unică de date a aplicației (date scrise direct în cod).
 * Ecranul de detaliu primește doar id-ul și citește restul de aici.
 *
 * Imaginile (res/drawable-nodpi) sunt de pe Wikimedia Commons, licențe CC BY / CC BY-SA.
 * place_muzeul_mineralogie, place_cavnic, place_sarmale și place_mici sunt imagini
 * generice (oraș / mâncare), de înlocuit cu poze ale locului exact.
 */
object PlacesRepository {

    val places: List<Place> = listOf(
        // ---------- Muzee ----------
        Place(
            1, "Muzeul Satului Maramureșean", Category.MUZEE, "Sighetu Marmației",
            "Muzeu în aer liber cu gospodării țărănești, porți maramureșene și o biserică " +
                "de lemn, aduse din satele zonei.",
            R.drawable.place_muzeul_satului
        ),
        Place(
            2, "Memorialul Victimelor Comunismului și al Rezistenței", Category.MUZEE, "Sighetu Marmației",
            "Amenajat în fosta închisoare politică din Sighet, documentează represiunea " +
                "regimului comunist din România.",
            R.drawable.place_memorial_sighet
        ),
        Place(
            3, "Muzeul Județean de Istorie și Arheologie Maramureș", Category.MUZEE, "Baia Mare",
            "Colecții de arheologie, istorie și numismatică din zona Maramureșului, " +
                "în centrul vechi al orașului.",
            R.drawable.place_muzeul_istorie
        ),
        Place(
            4, "Muzeul Județean de Mineralogie", Category.MUZEE, "Baia Mare",
            "Colecție de minerale și flori de mină din zăcămintele Maramureșului, " +
                "legată de tradiția minieră a zonei.",
            R.drawable.place_muzeul_mineralogie
        ),

        // ---------- Sport ----------
        Place(
            5, "Domeniul schiabil Cavnic", Category.SPORT, "Cavnic",
            "Pârtii de schi de diferite dificultăți, deservite de teleschiuri, " +
                "la aproximativ 30 km de Baia Mare.",
            R.drawable.place_cavnic
        ),
        Place(
            6, "Stațiunea Borșa (schi, drumeții)", Category.SPORT, "Borșa",
            "Stațiune montană cu pârtii de schi iarna și punct de plecare pentru " +
                "drumeții în sezonul cald.",
            R.drawable.place_borsa
        ),
        Place(
            7, "Parcul Național Munții Rodnei", Category.SPORT, "Borșa / Moisei",
            "Arie protejată cu trasee montane marcate, inclusiv spre Vârful Pietrosul Rodnei.",
            R.drawable.place_muntii_rodnei
        ),

        // ---------- Restaurante ----------
        // TODO: verificați / înlocuiți cu localurile voastre preferate.
        Place(
            8, "Restaurant Millennium", Category.RESTAURANTE, "Baia Mare",
            "Restaurant în Piața Libertății, în centrul vechi al orașului.",
            R.drawable.place_piata_libertatii
        ),
        Place(
            9, "Restaurant 2 (de completat)", Category.RESTAURANTE, "Baia Mare",
            "Descriere scurtă: specific, zonă, ce merită încercat.",
            R.drawable.place_sarmale
        ),
        Place(
            10, "Restaurant 3 (de completat)", Category.RESTAURANTE, "Baia Mare",
            "Descriere scurtă: specific, zonă, ce merită încercat.",
            R.drawable.place_mici
        )
    )

    fun getById(id: Int): Place? = places.firstOrNull { it.id == id }
}
