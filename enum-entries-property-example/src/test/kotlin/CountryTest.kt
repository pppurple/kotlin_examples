import org.junit.jupiter.api.Test
import kotlin.system.measureTimeMillis

class CountryTest {
    @Test
    fun measurePerformanceEntries() {
        val timeEntries = measureTimeMillis {
            (1..10_000_000).forEach {
                findByCodeViaEntries("BR")
            }
        }
        println("entries: $timeEntries msec")

    }

    @Test
    fun measurePerformanceValues() {
        val timeValues = measureTimeMillis {
            (1..10_000_000).forEach {
                findByCodeViaValues("BR")
            }
        }
        println("values: $timeValues msec")
    }
}