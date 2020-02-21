class Scale(private val tonic: String) {

    private val sharpChromatic = listOf("A", "A#", "B", "C", "C#", "D", "D#", "E", "F", "F#", "G", "G#")
    private val flatChromatic = listOf("A", "Bb", "B", "C", "Db", "D", "Eb", "E", "F", "Gb", "G", "Ab")
    private val tonicUseSharps = listOf("G", "D", "A", "E", "B", "F#", "e", "b", "f#", "c#", "g#", "d#")
    private val tonicUseFlats = listOf("F", "Bb", "Eb", "Ab", "Db", "Gb", "d", "g", "c", "f", "bb", "eb")

    fun chromatic(): List<String> {
        return interval(CHROMATIC_INTERVALS)
    }

    fun interval(intervals: String): List<String> {
        return when (tonic) {
            in tonicUseSharps -> generateMusicalScale(tonic, sharpChromatic, intervals)
            in tonicUseFlats -> generateMusicalScale(tonic, flatChromatic, intervals)
            else -> generateMusicalScale(tonic, sharpChromatic, intervals)
        }
    }

    private fun generateMusicalScale(tonic: String, baseChromatic: List<String>, intervals: String): List<String> {

        val index = baseChromatic.indexOf(tonic.capitalize())
        val chromatic =
                baseChromatic.takeLast(CHROMATIC_LENGTH - index) + baseChromatic.take(index)

        val musicalScale = mutableListOf<String>()

        intervals.map {
            when(it) {
                'm' -> 1
                'M' -> 2
                else -> 3
            }
        }.fold(0) { accumulator, number ->
            musicalScale.add(chromatic[accumulator])
            accumulator + number
        }

        return musicalScale
    }

    companion object {
        const val CHROMATIC_INTERVALS = "mmmmmmmmmmmm"
        const val CHROMATIC_LENGTH = 12
    }

}
