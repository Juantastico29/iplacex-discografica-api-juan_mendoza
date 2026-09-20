package cl.iplacex.proyecto_discografia;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;

@Configuration
public class MongoConfig {

    private static final String CONNECTION_STRING = "mongodb+srv://mendozapadilla_db_user:juanfe001@eva-u2-spring.brcuerv.mongodb.net/discografica-db?retryWrites=true&w=majority&appName=eva-u2-spring";

    @Bean
    public MongoClient mongoClient() {
        return MongoClients.create(CONNECTION_STRING);
    }

    @Bean
    public MongoTemplate mongoTemplate() {
        return new MongoTemplate(mongoClient(), "discografica-db");
    }
}
