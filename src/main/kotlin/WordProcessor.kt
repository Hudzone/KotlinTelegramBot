import java.io.File

class WordProcessor(private val filePath: String) {

    fun loadWords(): MutableList<Word> {
        val dictionary: MutableList<Word> = mutableListOf()
        val words: File = File(filePath)
        val wordsCollection = words.readLines()

        wordsCollection.forEach { line ->
            val binaryList = line.split("|")
            val correctAnswerCounter = if (binaryList.size > 2) binaryList[2].toIntOrNull() ?: 0 else 0
            val word = Word(binaryList[0], binaryList[1], correctAnswerCounter)
            dictionary.add(word)
        }
        return dictionary
    }
}