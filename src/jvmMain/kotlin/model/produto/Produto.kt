package model.produto

data class Produto(
    val id: Int? = null,
    var nome: String? = null,
    var preco: Long? = null,
    val quantidade: Long? = null,
    var categoriaType: CategoriaType? = null,
)