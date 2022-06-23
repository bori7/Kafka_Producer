//package intg.com.learnkafka.controller;
//
//import com.codenotfound.kafka.controller.LibraryEventsController;
//import com.codenotfound.kafka.domain.Book;
//import com.codenotfound.kafka.domain.LibraryEvent;
//import com.codenotfound.kafka.producer.LibraryEventProducer;
//import org.apache.kafka.clients.consumer.Consumer;
//import org.apache.kafka.clients.consumer.ConsumerConfig;
//import org.apache.kafka.clients.consumer.ConsumerRecords;
//import org.apache.kafka.common.serialization.IntegerDeserializer;
//import org.apache.kafka.common.serialization.StringDeserializer;
//import org.junit.jupiter.api.AfterEach;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.Timeout;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.web.client.TestRestTemplate;
//import org.springframework.http.*;
//import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
//import org.springframework.kafka.test.EmbeddedKafkaBroker;
//import org.springframework.kafka.test.context.EmbeddedKafka;
//import org.springframework.kafka.test.utils.KafkaTestUtils;
//import org.springframework.test.context.TestPropertySource;
//
//import java.util.HashMap;
//import java.util.Map;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//
//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = {LibraryEventsController.class, LibraryEventProducer.class })
//@EmbeddedKafka(topics = {"library-events"}, partitions = 3)
//@TestPropertySource(properties = {"spring.kafka.producer.bootstrap-servers=${spring.embedded.kafka.brokers}",
//        "spring.kafka.admin.properties.bootstrap.servers=${spring.embedded.kafka.brokers}"})
////@AutoConfigureMockMvc
//@EnableAutoConfiguration
//public class LibraryEventsControllerIntegrationTest {
//
//    @Autowired
//    TestRestTemplate restTemplate;
//
//
////    @Autowired
////    EmbeddedKafkaBroker embeddedKafkaBroker;
//    EmbeddedKafkaBroker embeddedKafkaBroker = new EmbeddedKafkaBroker(3, true, 3, "library-events" );
//
//    private Consumer<Integer, String> consumer;
//
//    @BeforeEach
//    void setUp() {
//        Map<String, Object> configs = new HashMap<>(KafkaTestUtils.consumerProps("group1", "true", embeddedKafkaBroker));
//        configs.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "latest");
//        consumer = new DefaultKafkaConsumerFactory<>(configs, new IntegerDeserializer(), new StringDeserializer()).createConsumer();
//        embeddedKafkaBroker.consumeFromAllEmbeddedTopics(consumer);
//    }
//
//    @AfterEach
//    void tearDown() {
//        consumer.close();
//    }
//
//    @Test
//    @Timeout(5)
//    void postLibraryEvent() throws InterruptedException {
//        //given
//        Book book = Book.builder()
//                .bookId(123)
//                .bookAuthor("Dilip")
//                .bookName("Kafka using Spring Boot")
//                .build();
//
//        LibraryEvent libraryEvent = LibraryEvent.builder()
//                .libraryEventId(null)
//                .book(book)
//                .build();
//        HttpHeaders headers = new HttpHeaders();
//        headers.set("content-type", MediaType.APPLICATION_JSON.toString());
//        HttpEntity<LibraryEvent> request = new HttpEntity<>(libraryEvent, headers);
//
//        //when
//        ResponseEntity<LibraryEvent> responseEntity = restTemplate.exchange("/v1/libraryevent", HttpMethod.POST, request, LibraryEvent.class);
//
//        //then
//        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
//
//
//
//        ConsumerRecords<Integer, String> consumerRecords = KafkaTestUtils.getRecords(consumer);
//        Thread.sleep(3000);
//        assert consumerRecords.count() == 1;
//        consumerRecords.forEach(record-> {
//            String expectedRecord = "{\"libraryEventId\":null,\"libraryEventType\":\"NEW\",\"book\":{\"bookId\":123,\"bookName\":\"Kafka using Spring Boot\",\"bookAuthor\":\"Dilip\"}}";
//            String value = record.value();
//            assertEquals(expectedRecord, value);
//        });
//
//
//    }
//
//    @Test
//    @Timeout(5)
//    void putLibraryEvent() throws InterruptedException {
//        //given
//        Book book = Book.builder()
//                .bookId(456)
//                .bookAuthor("Dilip")
//                .bookName("Kafka using Spring Boot")
//                .build();
//
//        LibraryEvent libraryEvent = LibraryEvent.builder()
//                .libraryEventId(123)
//                .book(book)
//                .build();
//        HttpHeaders headers = new HttpHeaders();
//        headers.set("content-type", MediaType.APPLICATION_JSON.toString());
//        HttpEntity<LibraryEvent> request = new HttpEntity<>(libraryEvent, headers);
//
//
//        //when
//        ResponseEntity<LibraryEvent> responseEntity = restTemplate.exchange("/v1/libraryevent", HttpMethod.PUT, request, LibraryEvent.class);
//
//        //then
//        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
//
//
//        ConsumerRecords<Integer, String> consumerRecords = KafkaTestUtils.getRecords(consumer);
//        Thread.sleep(3000);
//        assert consumerRecords.count() == 2;
//        consumerRecords.forEach(record-> {
//            if(record.key()!=null){
//                String expectedRecord = "{\"libraryEventId\":123,\"libraryEventType\":\"UPDATE\",\"book\":{\"bookId\":456,\"bookName\":\"Kafka using Spring Boot\",\"bookAuthor\":\"Dilip\"}}";
//                String value = record.value();
//                assertEquals(expectedRecord, value);
//            }
//        });
//
//
//    }
//}