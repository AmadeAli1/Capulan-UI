package model.produto

import helpers.MarkupEnum

enum class CategoriaType : MarkupEnum {
    CAMISA,
    BERMUDA,
    CHAPEU,
    VESTIDO,
    SAPATO;

    override fun getName(): String {
        return this.name
    }
}