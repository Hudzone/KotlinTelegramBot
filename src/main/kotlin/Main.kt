import java.io.File

fun main() {

    mainMenu()

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

fun mainMenu() {

    while (true) {

        println(
            """
            1 - Учить слова
            2 - Статистика
            0 - Выход
        """.trimIndent()
        )

        print("Ваш выбор: ")
        val userInput: Int = try {
            readln().toInt()
        } catch (e: NumberFormatException) {
            println("Неверный ввод")
            continue
        }

        when (userInput) {
            1 -> println("Тут будет функционал тренажера")
            2 -> println("Тут будет статистика")
            0 -> {
                println("Завершение программы")
                break
            }

            else -> {
                println("Неверный ввод")
                continue
            }
        }

    }

}