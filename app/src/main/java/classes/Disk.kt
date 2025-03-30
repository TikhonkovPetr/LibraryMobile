package classes
import intarface.TakeToHome

class Disk(id: Int, name: String, available: Boolean, private val typeDiscID: Int) : ObjectLibrary(id, name, available),
    TakeToHome {
    private val typeDisc: Map<Int, String> = mapOf(0 to "DVD", 1 to "CD")
    override fun getFullInfo() {
        println("${this.typeDisc[typeDiscID]} ${this.name} доступен: ${yesOrNot(available)}")
    }

    override fun takeToHome() {
        if (available) {
            println("Вы взяли домой диск ${this.name}")
            available = !available
        } else println("Диска нет в библиотеке")
    }
}