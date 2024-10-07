import java.io.File

fun main() {

    val dictionary: MutableList<Word.Example> = mutableListOf()

    val words: File = File("words.txt")
    val wordsCollection = words.readLines()

    wordsCollection.forEach { line ->
        val binaryList = line.split("|")
        val correctAnswerCounter = if (binaryList.size > 2) binaryList[2].toIntOrNull() ?: 0 else 0
        val word = Word.Example(binaryList[0], binaryList[1], correctAnswerCounter)
        dictionary.add(word)
    }

    showMenu(dictionary)

}

fun showMenu(dictionary: List<Word.Example>) {
    val trainer = LanguageTrainer(dictionary)

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
            1 -> trainer.learnWords(dictionary)
            2 -> trainer.showStatistics()
            0 -> {
                println("Завершение программы")
                return
            }
            else -> println("Неверный ввод")
        }
    }
}
