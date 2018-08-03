import org.apache.kafka.clients.producer._

class MessagePublisher(producer: Producer[String, String], topic: String)  {
    def sendMessage(messages: List[String])  {
        messages.foreach(m => {
          val record = new ProducerRecord[String, String](topic,m)
          producer.send(record, (_: RecordMetadata, exception: Exception) => {
            if (exception != null) {
              throw new MessagePublishException()
            }
          })
        })
    }
}
