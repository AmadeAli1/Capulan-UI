package model.actores

import helpers.MarkupEnum
import helpers.TabItem

enum class JobArea(vararg val tabs: MarkupEnum) : MarkupEnum {
    GERENTE(TabItem.Produtos, TabItem.Encomendas, TabItem.Utilizadores, TabItem.Stocks, TabItem.Fornecedores),
    ADMIN(
        TabItem.Produtos, TabItem.Encomendas, TabItem.Utilizadores,
        TabItem.Admins,
        TabItem.Stocks,
        TabItem.Fornecedores
    ),
    BALCONISTA(TabItem.Produtos, TabItem.Encomendas, TabItem.Utilizadores);

    override fun getName(): String {
        return this.name
    }
}
