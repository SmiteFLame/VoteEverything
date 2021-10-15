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
    basePackages = ["toy.vote.main.datasource.vote"],
    entityManagerFactoryRef = "voteEntityManager",
    transactionManagerRef = "voteTransactionManager"
)
class VoteConfiguration {
    @Bean
    @ConfigurationProperties(prefix = "spring.datasource-vote")
    fun voteDataSource(): DataSource = DataSourceBuilder.create().build()

    @Bean
    fun voteEntityManager(): LocalContainerEntityManagerFactoryBean =
        (LocalContainerEntityManagerFactoryBean()).apply {
            dataSource = voteDataSource()
            setPackagesToScan("toy.vote.main.datasource.vote")
            jpaVendorAdapter = HibernateJpaVendorAdapter()
        }

    @Bean
    fun voteTransactionManager(userDataSource: DataSource?): PlatformTransactionManager? {
        val jpaTransactionManager = JpaTransactionManager()
        jpaTransactionManager.entityManagerFactory = voteEntityManager().`object`
        jpaTransactionManager.dataSource = userDataSource
        return jpaTransactionManager
    }
}
