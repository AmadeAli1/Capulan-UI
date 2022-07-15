package model.produto

data class Categoria(
    val name: CategoriaType,
    val type: String,
    var id: Int = 0
) {
}
