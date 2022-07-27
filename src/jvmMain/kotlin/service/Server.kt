package service

enum class Server(
    val host: String, val username: String,
    val password: String, val server: String,
) {

    PORTUGAL(
        host = "jdbc:oracle:thin:@192.168.1.104:1521:XE",
        username = "Portugal",
        password = "portugal",
        server = "http://localhost:8080/api/mz/"
    ),
    BRAZIL(
        host = "jdbc:oracle:thin:@192.168.1.186:1521:XE",
        username = "Brazil",
        password = "brazil",
        server = "http://localhost:8080/api/br/"
    ),
    SAOTOME(
        host = "jdbc:oracle:thin:@192.168.1.137:1521:XE",
        username = "SaoTome",
        password = "saotome",
        server = "http://localhost:8080/api/st/"
    ),
    MOZAMBIQUE(
        host = "jdbc:oracle:thin:@192.168.1.199:1521:XE",
        username = "Moz",
        password = "moz",
        server = "http://localhost:8080/api/mz/"
    ),

}