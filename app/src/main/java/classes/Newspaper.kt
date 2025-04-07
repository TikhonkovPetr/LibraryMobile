package classes
import intarface.ReadingInLibrary

class Newspaper(id: Int, name: String, available: Boolean, val issueNumber: Int, val monthCreate: String) :
    ObjectLibrary(id, name, available), ReadingInLibrary {
    override fun getFullInfo(): String {
        return "Описание газеты: выпуск: ${this.issueNumber} месяца ${this.monthCreate} газеты ${this.name} с id: ${this.id} доступен: ${yesOrNot(available)}"
    }

    override fun readingInLibrary() {
        if (available) {
            println("Вы взяли газету ${this.name} читать в зале")
            available = !available
        } else println("Эту газету уже кто то взял читать")
    }
}