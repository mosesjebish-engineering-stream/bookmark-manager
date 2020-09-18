package com.mosesjebish.bookmarkmanager;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;

@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
//@EnableResourceServer
//@EnableGlobalMethodSecurity(prePostEnabled = true)
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public OpenAPI customOpenAPI() {
        Contact contact = new Contact();
        contact.setName("Jebish Moses");
        contact.setUrl("https://github.com/mosesjebish");
        contact.setEmail("mosesjebish@gmail.com");
        return new OpenAPI()
                .info(new Info()
                                .title("Bookmark Manager")
                                .version("1.0")
                                .description("Spring Boot API for Bookmark Management")
                                .termsOfService("http://swagger.io/terms/")
                        .contact(contact));
    }


}
