package api.social.media.gateway.APIGateway;

// import org.springframework.boot.context.properties.ConfigurationProperties;
// import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
// import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.context.annotation.Configuration;
// import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;

import api.social.media.gateway.APIGateway.Shared.Post;

// import javax.sql.DataSource;

// @Component
@Configuration
public class Config {
    @Bean
    @LoadBalanced
    public RestTemplate gRestTemplate () {
        return new RestTemplate();
    }
    @Bean
    public Post getPost () {
        return new Post();
    }
    // @Bean
    // public DataSource dataSource() {
    //     return DataSourceBuilder
    //             .create()
    //             .url("postgresql://dpg-ckl8g8rj89us73d25dqg-a.oregon-postgres.render.com/prophius_db")
    //             .username("prophius_db_user")
    //             .password("lllga7wZtXOUByNDKQForVORE7uS3m1o")
    //             .driverClassName("org.postgresql.Driver")
    //             .build();
    // }
}
