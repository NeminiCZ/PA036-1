package cz.muni.fi.pa036;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 *
 * @author GGIB
 */

@SpringBootApplication
@EnableJpaRepositories
@EnableCaching
public class Application
{
    public static void main(String[] args) 
    {
        SpringApplication.run(Application.class, args);
    }
}
