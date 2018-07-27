import java.util

import org.apache.kafka.clients.consumer.KafkaConsumer

class MessageReceiver(consumer: KafkaConsumer[String, String], topic: String) {
  def receiveMessages() = {
    consumer.subscribe(util.Collections.singletonList("Kafkatopic"))
    while (true) {
      val records = consumer.poll(100)
      for (record <- records) {
        println(record)
      }
    }
  }
}
