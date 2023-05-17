import org.junit.jupiter.api.Test
import kotlin.system.measureTimeMillis

class ColorTest {
    @Test
    fun measurePerformanceEntries() {
        val timeEntries = measureTimeMillis {
            (1..10_000_000).forEach {
                findByRgbViaEntries("#FF7F00")
            }
        }
        println("entries: $timeEntries msec")

    }

    @Test
    fun measurePerformanceValues() {
        val timeValues = measureTimeMillis {
            (1..10_000_000).forEach {
                findByRgbViaValues("#FF7F00")
            }
        }
        println("values: $timeValues msec")
    }
}