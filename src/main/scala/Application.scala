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
  val prop = new Properties()
    prop.put("bootstrap.servers", "localhost:9092")
    prop.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer")
    prop.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer")
    prop.put("zk.connect", "localhost:2181")
  val expressionRepo = new UserExpressionRepository(getDbConnection())

  def getKafkaProducer = () => {
    new KafkaProducer[String, String](prop)
  }
  def getKafkaConsumer = () => {
    new KafkaConsumer[String, String](prop)
  }
  val producer = new MessagePublisher(getKafkaProducer(), "Kafkatopic")

  val l = expressionRepo.getUserExpressions
  producer.sendMessage(l)

  val consumer = new MessageReceiver(getKafkaConsumer(), "Kafkatopic")
}