package api.social.media.userserver.SocialMediaAPIUserServer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class SocialMediaApiUserServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(SocialMediaApiUserServerApplication.class, args);
	}

}
