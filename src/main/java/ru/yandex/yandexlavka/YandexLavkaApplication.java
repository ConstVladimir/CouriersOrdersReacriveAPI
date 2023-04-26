package ru.yandex.yandexlavka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import ru.yandex.yandexlavka.ratelimiter.RateLimiter;

@SpringBootApplication
public class YandexLavkaApplication {
    @Bean
    public RateLimiter getRateLimiter(){
        return new RateLimiter();
    }
    public static void main(String[] args) {
        SpringApplication.run(YandexLavkaApplication.class, args);
    }

}
