import postgresConnection.stm

object dataRetrieveFromPostgres {

  val rs = stm.executeQuery("SELECT USER_EXPRESSION_QUERY from unmatched_feb")

}
