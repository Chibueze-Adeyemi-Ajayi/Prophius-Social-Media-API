package api.social.media.eurekaserver.SocialMediaAPIEurekaServer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class SocialMediaApiEurekaServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(SocialMediaApiEurekaServerApplication.class, args);
	}

}
