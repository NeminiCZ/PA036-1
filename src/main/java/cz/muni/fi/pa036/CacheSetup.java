package cz.muni.fi.pa036;

import org.springframework.boot.autoconfigure.cache.JCacheManagerCustomizer;
import org.springframework.stereotype.Component;

import javax.cache.CacheManager;
import javax.cache.configuration.MutableConfiguration;
import javax.cache.expiry.Duration;
import javax.cache.expiry.TouchedExpiryPolicy;

import static java.util.concurrent.TimeUnit.SECONDS;

@Component
public class CacheSetup implements JCacheManagerCustomizer {

    private final int EXPIRATION_TIME = 10;



    @Override
    public void customize(CacheManager cacheManager) {
        MutableConfiguration configuration = new MutableConfiguration<>()
                .setExpiryPolicyFactory(TouchedExpiryPolicy.factoryOf(new Duration(SECONDS, EXPIRATION_TIME)))
                .setStoreByValue(false)
                .setStatisticsEnabled(true);

        cacheManager.createCache("author", configuration);
        cacheManager.createCache("book", configuration);
        cacheManager.createCache("publisher", configuration);
    }
}
