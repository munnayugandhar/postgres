import java.sql.DriverManager

object postgresConnection extends App {
  println("Postgres connector")

  classOf[org.postgresql.Driver]
  val con_st = "jdbc:postgresql://localhost:5432/postgres?user=postgres&password=postgres"
  val conn = DriverManager.getConnection(con_st)
  try {
    val stm = conn.createStatement

    val rs = stm.executeQuery("SELECT custID from first_table")

    while(rs.next) {
      println(rs.getString("custID"))
    }
  } finally {
    conn.close()
  }
}