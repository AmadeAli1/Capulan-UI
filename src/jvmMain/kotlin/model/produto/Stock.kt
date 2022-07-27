package model.produto

data class Stock(
    var id: Int? = null,
    var quantidade: Int? = null,
    var dataChegada: String? = null,
    var preco: Long? = null,
    var fornecedor: Int? = null,
)
