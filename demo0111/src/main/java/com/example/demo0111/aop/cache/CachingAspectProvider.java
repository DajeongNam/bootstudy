package com.example.demo0111.aop.cache;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.CodeSignature;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.Arrays;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;

@Slf4j
@Aspect
@Component
public class CachingAspectProvider {

    private final CustomCacheManager cacheManager;

    public CachingAspectProvider(CustomCacheManager memoryCustomCacheManager) {
        this.cacheManager = memoryCustomCacheManager;
    }

    @Around("@annotation(com.example.demo0111.aop.cache.LookAsideCaching) && @annotation(target)")
    public Object handlerLookAsideCaching(ProceedingJoinPoint joinPoint, LookAsideCaching target) throws Throwable {
        if (StringUtils.isEmpty(target.value())) {
            return joinPoint.proceed();
        }

        var cacheName = target.value();
        var cacheKey = getKey(joinPoint, target);

        return findInCaches(cacheName, cacheKey)
                .orElseGet(()->{
                    try {
                        Object data = joinPoint.proceed();
                        putInCache(cacheName, cacheKey, data).thenAccept(isSave -> {
                            if (isSave) {
                                log.info("");
                            } else {
                                log.error("");
                            }
                        });
                        return data;
                    } catch (Throwable throwable) {
                        log.error("error...");
                        throw new RuntimeException("error...");
                    }
                });
    }

    private String getKey(ProceedingJoinPoint joinPoint, LookAsideCaching target) {
        if ("NONE".equals(target.key())) {
            return "simpleKey";
        } else {
            CodeSignature codeSignature = (CodeSignature) joinPoint.getSignature();
            var keyIndex = Arrays.asList(codeSignature.getParameterNames()).indexOf(target.key());
            return String.valueOf(joinPoint.getArgs()[keyIndex]);
        }
    }

    private Optional<Object> findInCaches(final String cacheName, final String cacheKey) {
        return cacheManager.getCache(cacheName).lookup(cacheKey);
    }

    private CompletionStage<Boolean> putInCache(final String cacheName, final String cacheKey, final Object data) {
        // TODO: 비동기로 동작하지만, Common Pool의 쓰레드를 사용함..
        // Common pool 을 사용하지 않도록 개선해야 함. 스터디 7주차 쯤, 비동기, 병렬 프로그래밍 스터디 예정
        return CompletableFuture.supplyAsync(()->cacheManager.getCache(cacheName).put(cacheKey, data));
    }
}
