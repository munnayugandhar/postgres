import scala.collection.JavaConversions._

class MessagePublisherTest extends org.scalatest.FunSpec {

    describe("sendMessage") {
      it("should send message to kafka producer") {
        val mockProducer = new MockKafkaProducer[String, String]()
        val SUT = new MessagePublisher(mockProducer, "topic")
        val input = List("value1", "value2", "value3")
        SUT.sendMessage(input)
        input.foreach(v => {
          mockProducer.receivedMessages.toList.map(m => {m.value()}).contains(v)
        })
      }

      it("should throw exception when kafka producer throws exception") {
        val mockProducer = new MockKafkaProducer[String, String]()
        val error = new Exception()
        mockProducer.throwError = error
        val SUT = new MessagePublisher(mockProducer, "topic")
        val input = List("value1", "value2", "value3")
        intercept[MessagePublishException] {
          SUT.sendMessage(input)
        }
      }

    }
}
