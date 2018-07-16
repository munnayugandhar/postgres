import java.sql.DriverManager
import java.util.Properties

import org.apache.kafka.clients.producer._

object ProducerExample extends App {


  val con_st = "jdbc:postgresql://localhost:5432/postgres?user=postgres&password=postgres"
  val conn = DriverManager.getConnection(con_st)
  val stm = conn.createStatement
  val rs = stm.executeQuery("SELECT USER_EXPRESSION_QUERY from unmatched_feb")

  val props = new Properties()
  props.put("bootstrap.servers", "localhost:2181")
  props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer")
  props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer")
  props.put("zk.connect", "localhost:2181")
  val producer = new KafkaProducer[String, String](props)
  val Topic: String = "Kafkatopic"



  while(rs.next())
  {
      val record = new ProducerRecord[String, String]("Kafkatopic", rs.getString("USER_EXPRESSION_QUERY"))
     // println(record, callback)

      producer.send(record)
    }
    conn.close()
  }

