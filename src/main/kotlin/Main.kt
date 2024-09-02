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

    showMainMenu(dictionary)

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

fun showMainMenu(dictionary: MutableList<Word>) {

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
            1 -> {

                val wordsToLearn: MutableList<Word> = mutableListOf()

                while (true) {

                    val rightWordIndex = (0..3).random()
                    wordsToLearn.clear()

                    dictionary.forEach { word ->
                        if (word.correctAnswerCounter < 3) {
                            wordsToLearn.add(word)
                        }
                    }

                    if (wordsToLearn.isEmpty()) {
                        println("Все слова выучены!")
                        break
                    }

                    val shuffledList: List<Word> = wordsToLearn.shuffled()
                    val chosenWords: List<Word> = shuffledList.take(4)
                    val rightWord: Word = chosenWords[rightWordIndex]

                    println("Как будет по английски: ${rightWord.translation}")

                    chosenWords.forEachIndexed { counter, word ->
                        println("${counter + 1}. ${word.word}")
                    }

                    print("Правильный ответ (для выхода введите 0): ")

                    val currentInput: Int = try {
                        readln().toInt()
                    } catch (e: NumberFormatException) {
                        println("Неверный ввод")
                        continue
                    }

                    if (currentInput == 0) {
                        break
                    }

                    val answer: Word = chosenWords[currentInput - 1]

                    if (answer.translation == rightWord.translation) {
                        rightWord.correctAnswerCounter++
                        println("Ответ верный!")
                    } else {
                        println("Ответ неверный!")
                    }
                }
            }

            2 -> {
                val knownWords: List<Word> = dictionary.filter { it.correctAnswerCounter >= 3 }
                val percent = (knownWords.size * 100) / dictionary.size
                println("Выучено ${knownWords.size} из ${dictionary.size} слов | $percent%")
            }

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