import org.apache.kafka.clients.producer._

class MessagePublisher(producer: Producer[String, String], topic: String)  {
    def sendMessage(messages: List[String])  {
        messages.foreach(m => {
          val record = new ProducerRecord[String, String](topic,m)
          print("Before Send")
          producer.send(record, (_: RecordMetadata, exception: Exception) => {
            print("Sleeping for 10 sec")
            Thread.sleep(10000)
            if (exception != null) {
              throw new MessagePublishException()
            }
            print("Woke up")
          })
        })
      print("After Send")
    }
}
