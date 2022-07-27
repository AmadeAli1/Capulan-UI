package model.produto

data class Produto(
    val id: Int,
    val nome: String,
    val preco: Long,
    val quantidade: Long,
    val categoriaType: CategoriaType,
)