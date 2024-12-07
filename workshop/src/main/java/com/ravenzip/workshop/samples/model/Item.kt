package com.ravenzip.workshop.samples.model

/** Пример модели, которая может быть использована в форм стейтах */
class Item(val uid: String, val name: String, val count: Int) {
    companion object {
        fun createEmptyModel(): Item = Item("", "", 0)

        fun createItem(uid: String, name: String, count: Int) = Item("1111111111", "Клен", 1)

        fun createItems(): List<Item> =
            listOf(
                Item("1111111111", "Клен", 1),
                Item("222222222", "Дуб", 2),
                Item("333333333", "Береза", 3),
            )
    }
}
