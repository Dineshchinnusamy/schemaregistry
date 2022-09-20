
import io.confluent.kafka.serializers.KafkaAvroSerializer;
import model.Product;
import org.apache.kafka.clients.admin.*;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.KafkaFuture;
import org.apache.kafka.common.acl.AccessControlEntry;
import org.apache.kafka.common.acl.AclBinding;
import org.apache.kafka.common.acl.AclOperation;
import org.apache.kafka.common.acl.AclPermissionType;
import org.apache.kafka.common.resource.PatternType;
import org.apache.kafka.common.resource.Resource;
import org.apache.kafka.common.resource.ResourceType;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.*;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collector;

public class SchemaRegistryProducer {
    public static void main(String[] args) {
        Properties properties = new Properties();
        properties.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9093");
        properties.setProperty("ssl.keystore.location", "C:/Tools/kafka_2.12-2.8.0/security/kafka.server.keystore.jks");
        properties.setProperty("ssl.keystore.password", "serversecret");
        properties.setProperty("ssl.key.password", "serversecret");
        properties.setProperty("ssl.truststore.location", "C:/Tools/kafka_2.12-2.8.0/security/kafka.server.truststore.jks");
        properties.setProperty("ssl.truststore.password", "serversecret");
        properties.setProperty("security.protocol", "SSL");


        try (AdminClient client = AdminClient.create(properties)) {
            short s = 1;
            // NewTopic topic = new NewTopic("newtopic1234", 1, s);
          //  CreateTopicsResult result = client.createTopics(Arrays.asList(new NewTopic("newtopic12345", 1, s)));
            AclBinding aclBinding = new AclBinding(new Resource(ResourceType.GROUP,"group"), new AccessControlEntry("User:CN=producer", "*",AclOperation.READ, AclPermissionType.ALLOW));
//            AclBinding aclBinding1 = new AclBinding(new Resource(ResourceType.TOPIC,"newtopic1234"), new AccessControlEntry("User:CN=producer", "*",AclOperation.DESCRIBE, AclPermissionType.ALLOW));
//            AclBinding aclBinding2 = new AclBinding(new Resource(ResourceType.TOPIC,"newtopic1234"), new AccessControlEntry("User:CN=producer", "*",AclOperation.CREATE, AclPermissionType.ALLOW));

            client.createAcls(Arrays.asList(aclBinding));

      //client.listTopics().names().get();
        } catch (Exception exception) {
            exception.printStackTrace();
        }

    }



}


