import org.springframework.context.annotation.Bean
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.jdbc.JdbcDaoImpl
import javax.sql.DataSource


//  This Spring security configuration enables basic HTTP authentication. HTTP authentication is required to access the APIs after this change.
//  The users inserted into the database using import.sql root/root can be used to authenticate.


@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
class WebSecurityConfig(val dataSource: DataSource) extends WebSecurityConfigurerAdapter {
  override def configure(http: HttpSecurity) = {
    http.authorizeRequests.antMatchers("/console", "/console/**", "/console/", "/swagger-ui.html", "/**/*.css", "/**/*.js", "/**/*.png", "/configuration/**", "/swagger-resources", "/v2/**").permitAll
    http.authorizeRequests.anyRequest.authenticated
    http.csrf.disable
    http.headers.frameOptions.disable
    http.httpBasic
  }

  @Bean
  override def userDetailsService: UserDetailsService = {
    val manager = new JdbcDaoImpl
    manager.setDataSource(dataSource)
    manager
  }

}