package ru.yandex.yandexlavka.ratelimiter;

import io.github.bucket4j.Bandwidth;
import io.github.bucket4j.Bucket;
import io.github.bucket4j.Refill;

import java.time.Duration;
import java.util.concurrent.ConcurrentHashMap;

public class RateLimiter {
    int rpm = 10;
    public ConcurrentHashMap <String, Bucket> bucketMap = new ConcurrentHashMap<>();
    private Bucket createBucket () {
        return Bucket.builder()
                .addLimit(Bandwidth.classic(rpm, Refill.intervally(rpm, Duration.ofMinutes(1))))
                .build();
    }
    public Bucket getBucket (String bName){
        return bucketMap.computeIfAbsent(bName, t->this.createBucket());
    }
}
