package br.com.zup.ecommerce;

import java.math.BigDecimal;
import java.util.UUID;
import java.util.concurrent.ExecutionException;

public class NewOrderMain {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        try (var orderDispatcher = new KafkaDispatcher<Order>()) {
            try (var emailDispatcher = new KafkaDispatcher<String>()) {
                // Producer de Ecommerce
                var userId = UUID.randomUUID().toString();
                var orderId = UUID.randomUUID().toString();
                var amount = new BigDecimal(Math.random() * 5000 + 1);

                var order = new Order(userId, orderId, amount);
                orderDispatcher.send("ECOMMERCE_NEW_ORDER", userId, order);

                // Produce de Enviar Email
                var email = "Obrigador por comprar com a gente";
                emailDispatcher.send("ECOMMERCE_SEND_EMAIL", userId, email);
            }
        }
    }
}
