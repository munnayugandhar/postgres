import java.util.Properties

import org.apache.kafka.clients.producer._
import dataRetrieveFromPostgres.rs

object ProducerExample extends App {

  val props = new Properties()
  props.put("bootstrap.servers", "localhost:9092")
  props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer")
  props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer")
  props.put("zk.connect", "localhost:2181")

  val producer = new KafkaProducer[String, String](props)
  val Topic: String = "Kafkatopic"



  while(rs.next())
  {
      val record = new ProducerRecord[String, String]("Kafkatopic", rs.getString("USER_EXPRESSION_QUERY"))
      producer.send(record)
  }

}

