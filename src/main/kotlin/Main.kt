import java.io.File

fun main() {

    val dictionary: MutableList<Word> = mutableListOf()

    val words: File = File("words.txt")
    val wordsCollection = words.readLines()

    wordsCollection.forEach { line ->
        val binaryList = line.split("|")
        val correctAnswerCounter = if (binaryList.size > 2) binaryList[2].toIntOrNull() ?: 0 else 0
        val word = Word(binaryList[0], binaryList[1], correctAnswerCounter)
        dictionary.add(word)
    }

    for (word in dictionary) {
        println(word)
    }

}

data class Word(
    val word: String,
    val translation: String,
    var correctAnswerCounter: Int = 0,
) {
    override fun toString(): String {
        return "Слово: $word\nПеревод: $translation\nКоличество верных ответов: $correctAnswerCounter\n"
    }
}