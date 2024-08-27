import java.io.File

fun main() {

    val dictionary: MutableList<Word> = mutableListOf()

    val words: File = File("words.txt")
    val wordsCollection = words.readLines()

    wordsCollection.forEach { line ->
        val binaryList = line.split("|")
        val word = Word(binaryList[0], binaryList[1], binaryList[2] ?: 0)
        dictionary.add(word)
    }

    for (word in dictionary) {
        println(word)
    }

}

data class Word(
    val word: String,
    val translation: String,
    private var correctAnswerCounter: Comparable<*> = 0,
) {
    override fun toString(): String {
        return "Слово: $word\nПеревод: $translation\nКоличество верных ответов: $correctAnswerCounter\n"
    }
}