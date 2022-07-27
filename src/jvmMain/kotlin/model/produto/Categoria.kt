package model.produto

data class Categoria(
    val categoriaType: CategoriaType,
    val nome: String,
    var id: Int = 0
)
