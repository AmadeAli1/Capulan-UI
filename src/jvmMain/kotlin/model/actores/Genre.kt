package model.actores

import helpers.MarkupEnum

enum class Genre : MarkupEnum {
    MASCULINO,
    FEMININO;

    override fun getName(): String {
        return this.name
    }
}