import java.sql.{Connection, DriverManager}
import java.util.Properties


import org.apache.kafka.clients.consumer.KafkaConsumer
import org.apache.kafka.clients.producer.KafkaProducer
import org.postgresql.Driver

class Application extends App {

  val getDbConnection = () =>  {
    classOf[Driver]
    val con_st = "jdbc:postgresql://localhost:5432/postgres?user=postgres&password=postgres"
    DriverManager.getConnection(con_st)
  }

  val expressionRepo = new UserExpressionRepository(getDbConnection())

  def getKafkaProducer = () => {
    val props = new Properties()
    props.put("bootstrap.servers", "localhost:9092")
    props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer")
    props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer")
    props.put("zk.connect", "localhost:2181")
    new KafkaProducer[String, String](props)
  }
  def getKafkaConsumer = () => {
    val props = new Properties()
    props.put("bootstrap.servers", "localhost:9092")
    props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer")
    props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer")
    props.put("zk.connect", "localhost:2181")
    new KafkaConsumer[String, String](props)
  }
  val producer = new MessagePublisher(getKafkaProducer(), "Kafkatopic")

  val l = expressionRepo.getUserExpressions
  producer.sendMessage(l)

  val consumer = new MessageReceiver(getKafkaConsumer(), "Kafkatopic")
}