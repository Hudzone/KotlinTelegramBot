import java.io.File


fun main() {

    val words: File = File("words.txt")
    val wordsCollection = words.readLines()

    wordsCollection.forEach { line ->
        println(line)
    }

}