package ru.yandex.yandexlavka;

import com.github.benmanes.caffeine.cache.Caffeine;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.caffeine.CaffeineCache;
import org.springframework.cache.caffeine.CaffeineCacheManager;
import org.springframework.cache.concurrent.ConcurrentMapCache;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.context.annotation.Bean;
import ru.yandex.yandexlavka.ratelimiter.RateLimiter;

import javax.cache.CacheManager;
import java.util.Arrays;
import java.util.Collection;
import java.util.concurrent.TimeUnit;

@SpringBootApplication
@EnableCaching
public class YandexLavkaApplication {
/*    @Bean
    public RateLimiter getRateLimiter(){
        return new RateLimiter();
}*/
/*    @Bean
    public Caffeine caffeineConfig() {
        return Caffeine.newBuilder()
                .expireAfterWrite(60, TimeUnit.MINUTES);
    }
    @Bean
    public CaffeineCacheManager cacheManager(Caffeine caffeine, CaffeineCache cache) {
        CaffeineCacheManager caffeineCacheManager = new CaffeineCacheManager();

        caffeineCacheManager.setCaffeine(caffeine);
        return caffeineCacheManager;
    }
    @Bean
    public CaffeineCache cacheA() {
        return new CaffeineCache("rate-limit-buckets",
                Caffeine.newBuilder()
                        .expireAfterAccess(1, TimeUnit.HOURS)
                        .build());
    }*/
    public static void main(String[] args) {
        SpringApplication.run(YandexLavkaApplication.class, args);
    }
}
