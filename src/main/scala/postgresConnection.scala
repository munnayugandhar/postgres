import java.sql.DriverManager

object postgresConnection extends App {
  println("Postgres connector")

  classOf[org.postgresql.Driver]
  val con_st = "jdbc:postgresql://localhost:5432/postgres?user=postgres&password=postgres"
  val conn = DriverManager.getConnection(con_st)
  try {

    val stm = conn.createStatement

    val rs = stm.executeQuery("SELECT USER_EXPRESSION_QUERY from unmatched_feb")
    while(rs.next) {
      println(rs.getString("USER_EXPRESSION_QUERY"))
    }
  } finally {
    conn.close()
  }
}