enum class Color(val colorName: String, val rgb: String) {
    RED("Red", "#FF0000"),
    ORANGE("Orange", "#FF7F00"),
    YELLOW("Yellow", "#FFFF00"),
}

@OptIn(ExperimentalStdlibApi::class)
fun findByRgbViaEntries(rgb: String): Color? = Color.entries.find { it.rgb == rgb }

fun findByRgbViaValues(rgb: String): Color? = Color.values().find { it.rgb == rgb }
