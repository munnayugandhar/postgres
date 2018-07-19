import java.sql.Connection

class UserExpressionRepository(conn: Connection)  {
    private val stm = conn.createStatement

    def getUserExpressions: List[String] = {
        val rs = stm.executeQuery("SELECT USER_EXPRESSION_QUERY from unmatched_feb")
        var expressions = List()
        while (rs.next()) {
            expressions += rs.getString("USER_EXPRESSION_QUERY")
        }
        expressions
    }
}
