package model.location

import helpers.MarkupEnum

enum class Region(val dressCode: String) : MarkupEnum {
    MOZAMBIQUE("TROPICAL"),
    PORTUGAL("INVERNO"),
    SAOTOME("TROPICAL"),
    BRAZIL("MISTO");

    override fun getName(): String {
        return this.name
    }
}