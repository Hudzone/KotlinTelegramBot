import java.io.File

class LanguageTrainer(private val dictionary: List<Word.Example>) {

    fun learnWords(dictionary: List<Word.Example>) {
        val wordsToLearn: MutableList<Word.Example> = mutableListOf()

        while (true) {
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

            val shuffledList: List<Word.Example> = wordsToLearn.shuffled()
            val wordsCount = minOf(4, wordsToLearn.size) // Получаем количество слов для выбора
            val chosenWords: List<Word.Example> = shuffledList.take(wordsCount)

            // Обратите внимание на этот диапазон
            val rightWordIndex = (0 until wordsCount).random()
            val rightWord: Word.Example = chosenWords[rightWordIndex]

            println("Как будет по английски: ${rightWord.translation}")

            chosenWords.forEachIndexed { counter, word ->
                println("${counter + 1}. ${word.word}")
            }
            println("0. Меню")

            print("Ваш ответ: ")

            val currentInput: Int = try {
                readln().toInt()
            } catch (e: NumberFormatException) {
                println("Неверный ввод")
                continue
            }

            if (currentInput == 0) {
                break
            }

            val answer: Word.Example = chosenWords[currentInput - 1]

            if (answer.translation == rightWord.translation) {
                rightWord.correctAnswerCounter++
                println("Ответ верный!")
                saveToFile(dictionary)
            } else {
                println("Ответ неверный! Правильный ответ - ${rightWord.word}")
            }
        }
    }

    fun showStatistics() {
        val knownWords: List<Word.Example> = dictionary.filter { it.correctAnswerCounter >= 3 }
        val percent = (knownWords.size * 100) / dictionary.size
        println("Выучено ${knownWords.size} из ${dictionary.size} слов | $percent%")
    }

    private fun getUserInput(): Int {
        print("Ваш ответ: ")
        return try {
            readln().toInt()
        } catch (e: NumberFormatException) {
            println("Неверный ввод")
            -1
        }
    }

    private fun saveToFile(dictionary: List<Word.Example>) {
        val updatedFile = File("words.txt")
        val lines = dictionary.map { "${it.word}|${it.translation}|${it.correctAnswerCounter}" }
        updatedFile.writeText(lines.joinToString("\n"))
    }
}