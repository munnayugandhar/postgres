import java.sql.DriverManager

object postgresConnection extends App {

  println("Postgres connector")

  classOf[org.postgresql.Driver]
  val con_st = "jdbc:postgresql://localhost:5432/postgres?user=postgres&password=postgres"
  val conn = DriverManager.getConnection(con_st)
  val stm = conn.createStatement

}