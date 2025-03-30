package classes
import intarface.ReadingInLibrary
import intarface.TakeToHome

open class Book(id: Int, name: String, available: Boolean, private val countPages: Int, private val autor: String) :
    ObjectLibrary(id, name, available), ReadingInLibrary, TakeToHome {
    override fun getFullInfo() {
        println(
            "книга: ${this.name} (${this.countPages} стр.) автора: ${this.autor} с id: ${this.id} доступна:${yesOrNot(available)}"
        )
    }

    override fun readingInLibrary() {
        if (available) {
            println("Вы взяли книгу ${this.name} читать в зале")
            available = !available
        } else println("Эту книгу уже кто то взял")
    }

    override fun takeToHome() {
        if (available) {
            println("Вы взяли книгу домой ${this.name}")
            available = !available
        } else println("Эту книгу уже кто то взял")
    }
}