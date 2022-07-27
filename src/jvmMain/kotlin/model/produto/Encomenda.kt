package model.produto

data class Encomenda(
    val idEncomenda: Long = 0,
    val idUsuario: Long = 0,
    var quantidade: Long? = null,
    val estado: EncomendaEstado = EncomendaEstado.PENDENTE,
    val nome: String = "",
    val cliente: String = "",
    val date: String? = null,
    var preco: Long? = null,
)
