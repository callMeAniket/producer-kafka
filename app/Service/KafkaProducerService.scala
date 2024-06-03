package Service

import javax.inject.{Inject, Singleton}
import com.typesafe.config.Config
import java.util.Properties
import org.apache.kafka.clients.producer.{KafkaProducer, ProducerConfig, ProducerRecord}

@Singleton
class KafkaProducerService @Inject()(config: Config) {

  private val kafkaConfig = config.getConfig("kafka")

  // Kafka producer properties
  private val props = new Properties()
  props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaConfig.getString("bootstrap.servers"))
  props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringSerializer")
  props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringSerializer")

  // Initialize Kafka producer
//  private val kafkaProducer = new KafkaProducer[String, String](props)

  // Method to send a message to Kafka
  def sendMessage(topic: String, message: String): Unit = {
    val record = new ProducerRecord[String, String](topic, message)
    //val sendResult = kafkaProducer.send(record)
  }

  // Method to close the Kafka producer
  def close(): Unit = {
//    kafkaProducer.close()
  }
}
