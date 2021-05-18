package br.com.zup.ecommerce;

import org.apache.kafka.clients.consumer.ConsumerRecord;

import java.util.Map;

public class FraudDetectorService {

    public static void main(String[] args) {
        var fraudDetectorService = new FraudDetectorService();
        try(var service = new KafkaService(FraudDetectorService.class.getSimpleName(),
                        "ECOMMERCE_NEW_ORDER",
                        fraudDetectorService::parse,
                        Order.class,
                Map.of())) {
            service.run();
        }
    }

    private void parse(ConsumerRecord<String, Order> record) {
        System.out.println("----------------");
        System.out.println("Processar");
        System.out.println(record.value());
        System.out.println(record.partition());
        System.out.println(record.offset());
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
