package model.produto

data class Encomenda(
    val idEncomenda: Long,
    val idUsuario: Long,
    val quantidade: Long,
    val estado: CategoriaType,
    val nome: String,
    val cliente: String,
    val date: String,
    val preco: Long,
    )