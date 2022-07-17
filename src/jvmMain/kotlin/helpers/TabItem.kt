package helpers

enum class TabItem : MarkupEnum {
    Admins,
    Encomendas,
    Utilizadores,
    Produtos,
    Stocks,
    Fornecedores;

    override fun getName(): String {
        return this.name
    }
}