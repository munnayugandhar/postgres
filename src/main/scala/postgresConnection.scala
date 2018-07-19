import java.sql.DriverManager

class postgresConnection extends App {

    classOf[org.postgresql.Driver]
    val con_st = "jdbc:postgresql://localhost:5432/postgres?user=postgres&password=postgres"
    val conn = DriverManager.getConnection(con_st)
    val stm = conn.createStatement
    val rs = stm.executeQuery("SELECT USER_EXPRESSION_QUERY from unmatched_feb")
}