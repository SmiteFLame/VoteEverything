package toy.vote.main.config

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.jdbc.DataSourceBuilder
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.jpa.repository.config.EnableJpaRepositories
import org.springframework.orm.jpa.JpaTransactionManager
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter
import org.springframework.transaction.PlatformTransactionManager
import javax.sql.DataSource

@Configuration
@EnableJpaRepositories(
    basePackages = ["toy.vote.main.datasource.user"],
    entityManagerFactoryRef = "userEntityManager",
    transactionManagerRef = "userTransactionManager"
)
class UserConfiguration {
    @Bean
    @ConfigurationProperties(prefix = "spring.datasource-user")
    fun userDataSource(): DataSource = DataSourceBuilder.create().build()

    @Bean
    fun userEntityManager(): LocalContainerEntityManagerFactoryBean =
        (LocalContainerEntityManagerFactoryBean()).apply {
            dataSource = userDataSource()
            setPackagesToScan("toy.vote.main.datasource.user")
            jpaVendorAdapter = HibernateJpaVendorAdapter()
        }

    @Bean
    fun userTransactionManager(userDataSource: DataSource?): PlatformTransactionManager? {
        val jpaTransactionManager = JpaTransactionManager()
        jpaTransactionManager.entityManagerFactory = userEntityManager().`object`
        jpaTransactionManager.dataSource = userDataSource
        return jpaTransactionManager
    }
}
