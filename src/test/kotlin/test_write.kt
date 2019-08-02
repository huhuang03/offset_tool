import java.io.File

const val PATH = "/Users/th/tmp/test.txt"


fun main() {
    val f = File(PATH)
    f.printWriter().use {
        it.println("abcd")
    }

    f.printWriter().use {
        it.println("00")
    }
}

