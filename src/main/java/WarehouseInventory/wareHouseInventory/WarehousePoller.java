package WarehouseInventory.wareHouseInventory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@EnableScheduling
@Component
public class WarehousePoller {
    private WebClient client =
            WebClient.create("http://localhost:7634/warehouse");

    private final WarehouseRespository repository;
    private final RedisConnectionFactory connectionFactory;

    WarehousePoller(RedisConnectionFactory connectionFactory, WarehouseRespository repository) {
        this.connectionFactory = connectionFactory;
        this.repository = repository;
    }

    @Scheduled(fixedRate = 1000)
    private void pollWarehouse() {
        connectionFactory.getConnection().serverCommands().flushDb();

        client.get()
                .retrieve()
                .bodyToFlux(Warehouse.class)
                .filter(warehouse -> !warehouse.getName().isEmpty())
                .toStream()
                .forEach(repository::save);

        repository.findAll().forEach(System.out::println);

    }
}
