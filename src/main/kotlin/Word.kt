class Word {

    data class Example(
        val word: String,
        val translation: String,
        var correctAnswerCounter: Int = 0,
    ) {
        override fun toString(): String {
            return "Слово: $word\nПеревод: $translation\nКоличество верных ответов: $correctAnswerCounter\n"
        }
    }

}