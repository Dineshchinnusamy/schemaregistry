package schemaregistry;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Properties;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import com.model.Product;

import io.confluent.kafka.serializers.KafkaAvroDeserializer;

public class SchemaRegistryConsumer {

	public static void main(String[] args) {
		Properties properties = new Properties();
		properties.setProperty(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
		properties.setProperty(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringDeserializer");
		properties.setProperty(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, KafkaAvroDeserializer.class.getName());
		properties.setProperty("schema.registry.url", "http://localhost:8081");
		properties.setProperty(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
		properties.setProperty(ConsumerConfig.GROUP_ID_CONFIG, "group1");
		
		KafkaConsumer<String, Product> consumer = new KafkaConsumer<>(properties);
		consumer.subscribe(Arrays.asList("schemaregistry"));
		while (true) {
			ConsumerRecords<String, Product> records = consumer.poll(1000);
			// we get the each record's object using for loop
			for (ConsumerRecord<String, Product> record : records) {
				//Product value = record.value();
				System.out.println(record.toString());
			}
			//consumer.commitSync();

		}

	}

}
