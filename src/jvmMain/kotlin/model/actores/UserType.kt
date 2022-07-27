package model.actores

import helpers.MarkupEnum
import helpers.TabItem
import helpers.TabItem.*

enum class UserType(vararg val tabs: TabItem) : MarkupEnum {
    CLIENTE(Produtos, Encomendas),
    FUNCIONARIO(Produtos, Encomendas, Utilizadores),
    GERENTE(Produtos, Encomendas, Utilizadores, Stocks, Fornecedores),
    ADMIN(Produtos, Encomendas, Utilizadores, Stocks, Fornecedores),
    BALCONISTA(Produtos, Encomendas, Utilizadores);

    override fun getName(): String {
        return this.name
    }
}

