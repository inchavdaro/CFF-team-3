package ccf.project.config;

import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Profile("!test")
@EnableCaching
@Configuration
public class CacheConfig {
}
