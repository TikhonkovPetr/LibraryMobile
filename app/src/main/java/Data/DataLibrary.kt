package Data
import classes.*

class DataLibrary{
    private val month = Month()
    private val allObjectLibrary: MutableList<ObjectLibrary> = mutableListOf(
        Book(1, "Ночь перед рождеством", true, 96, "Николай Гоголь"),
        Disk(2, "Пираты Карибского моря: проклятие Чёрной жемчужины", true, 0),
        Disk(3, "Расомаха", true, 1),
        Newspaper(4, "Комсомольская правда", true, 1235, month.monthes[4].toString()),
        Book(5, "Анна Каренина", true, 864, "Лев Толстой"),
        Disk(6, "Астрал", true, 1),
        Newspaper(7, "РБК (газета)", true, 96, month.monthes[2].toString()),
        Newspaper(8, "Известия", true, 121, month.monthes[10].toString()),
        Book(9, "Мёртвые души", true, 352, "Николай Гоголь"),
        Book(10, "Обломов", true, 544, "Иван Гончаров"),
        Newspaper(11, "Правда", true, 121, month.monthes[10].toString()),
        Book(12, "Метро 2033", true, 352, "Дмитрий Глуховский"),
        Book(13, "48 законов власти", true, 544, "Роберт Грин"),
        Newspaper(14, "Известия", true, 121, month.monthes[10].toString()),
        Book(15, "Мёртвые души", true, 352, "Николай Гоголь"),
        Book(16, "Обломов", true, 544, "Иван Гончаров"),
        Newspaper(17, "Правда", true, 121, month.monthes[10].toString()),
        Book(18, "Метро 2033", true, 352, "Дмитрий Глуховский"),
        Book(19, "48 законов власти", true, 544, "Роберт Грин")
    )

    fun getAllObject(): MutableList<ObjectLibrary> {
        return allObjectLibrary
    }
}