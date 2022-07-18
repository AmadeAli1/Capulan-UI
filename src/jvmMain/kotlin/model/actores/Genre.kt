package model.actores

import helpers.MarkupEnum

enum class Genre : MarkupEnum {
    MASCULINO,
    FEMENINO;

    override fun getName(): String {
        return this.name
    }
}