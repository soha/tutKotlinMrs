package mrs

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.thymeleaf.extras.java8time.dialect.Java8TimeDialect

@Configuration
class ThymeleafConfig {
    @Bean
    fun java8TimeDialect(): Java8TimeDialect {
        return Java8TimeDialect()
    }
}