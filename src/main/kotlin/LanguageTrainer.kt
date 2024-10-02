class LanguageTrainer {

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
                1 -> learnWords(dictionary)
                2 -> showStatistics(dictionary)
                0 -> {
                    println("Завершение программы")
                    break
                }
                else -> println("Неверный ввод")
            }
        }
    }

    private fun learnWords(dictionary: MutableList<Word>) {
        val wordsToLearn: MutableList<Word> = mutableListOf()

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

            val wordsCount = minOf(4, wordsToLearn.size)
            val shuffledList: List<Word> = wordsToLearn.shuffled()
            val chosenWords: List<Word> = shuffledList.take(wordsCount)
            val rightWordIndex = (0 until wordsCount).random()
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

            if (currentInput == 0) break

            if (currentInput !in 1..wordsCount) {
                println("Неверный ввод")
                continue
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

    private fun showStatistics(dictionary: List<Word>) {
        val knownWords: List<Word> = dictionary.filter { it.correctAnswerCounter >= 3 }
        val percent = (knownWords.size * 100) / dictionary.size
        println("Выучено ${knownWords.size} из ${dictionary.size} слов | $percent%")
    }
}