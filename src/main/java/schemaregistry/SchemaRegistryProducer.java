package schemaregistry;

import java.util.Properties;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;

import com.model.Product;

import io.confluent.kafka.serializers.KafkaAvroSerializer;

public class SchemaRegistryProducer {

	public static void main(String[] args) {
        Properties properties= new Properties();
        properties.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,"localhost:9092");
        properties.setProperty(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG,"org.apache.kafka.common.serialization.StringSerializer");
        properties.setProperty(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, KafkaAvroSerializer.class.getName());
        properties.setProperty("schema.registry.url","http://localhost:8081");
        Product product= new Product("TV",11,"samsung");
        KafkaProducer<String, Product> producer = new KafkaProducer<String, Product>(properties);

        ProducerRecord<String,Product> record= new ProducerRecord<String,Product>("schemaregistry",product);
            producer.send(record);
            System.out.println(record);
        producer.close();
    }
	

}
