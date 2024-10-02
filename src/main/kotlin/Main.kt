fun main() {
    val wordProcessor = WordProcessor("words.txt")
    val dictionary = wordProcessor.loadWords()

    val languageTrainer = LanguageTrainer()
    languageTrainer.showMainMenu(dictionary)
}