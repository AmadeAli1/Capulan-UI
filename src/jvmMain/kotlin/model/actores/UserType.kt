package model.actores

import screens.home.TabItem
import screens.home.TabItem.*

enum class UserType(vararg val  tabs: TabItem) {
    CLIENTE(Produtos, Encomendas),
    FUNCIONARIO(Produtos, Encomendas, Utilizadores),
    GERENTE(Produtos, Encomendas, Utilizadores, Stocks, Fornecedores),
    ADMIN(Produtos, Encomendas, Utilizadores, Admins, Stocks, Fornecedores)
}