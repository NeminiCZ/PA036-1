package cz.muni.fi.pa036;

import org.springframework.boot.autoconfigure.cache.JCacheManagerCustomizer;
import org.springframework.stereotype.Component;

import javax.cache.CacheManager;
import javax.cache.configuration.MutableConfiguration;
import javax.cache.expiry.*;

import static java.util.concurrent.TimeUnit.SECONDS;

@Component
public class CacheSetup implements JCacheManagerCustomizer {

    private final int EXPIRATION_TIME = 10;

    @Override
    public void customize(CacheManager cacheManager) {
        MutableConfiguration configuration = new MutableConfiguration<>()
//                .setExpiryPolicyFactory(CreatedExpiryPolicy.factoryOf(new Duration(SECONDS, EXPIRATION_TIME)))
//                .setExpiryPolicyFactory(AccessedExpiryPolicy.factoryOf(new Duration(SECONDS, EXPIRATION_TIME)))
//                .setExpiryPolicyFactory(EternalExpiryPolicy.factoryOf())
                .setExpiryPolicyFactory(TouchedExpiryPolicy.factoryOf(new Duration(SECONDS, EXPIRATION_TIME)))
//                .setExpiryPolicyFactory(ModifiedExpiryPolicy.factoryOf(new Duration(SECONDS, EXPIRATION_TIME)))
                .setStoreByValue(false)
                .setStatisticsEnabled(true);

        cacheManager.createCache("author", configuration);
        cacheManager.createCache("book", configuration);
        cacheManager.createCache("publisher", configuration);
    }
}
