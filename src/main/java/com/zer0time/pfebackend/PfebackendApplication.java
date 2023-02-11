package com.zer0time.pfebackend;


import com.zer0time.pfebackend.shared.FileStorageProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
@EnableConfigurationProperties({
        FileStorageProperties.class
})
public class PfebackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(PfebackendApplication.class, args);
	}
        @Bean
        public BCryptPasswordEncoder bCryptPasswordEncoder (){
            return new BCryptPasswordEncoder();
            
        }
        
        @Bean
        public SpringApplicationContext springApplicationContext(){
            return new SpringApplicationContext();
        }
}
