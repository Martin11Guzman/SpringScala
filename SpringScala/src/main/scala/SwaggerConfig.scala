import javax.tools.DocumentationTool.DocumentationTask

import jdk.nashorn.internal.ir.RuntimeNode.Request
import org.springframework.context.annotation.Bean
import org.springframework.web.context.support.RequestHandledEvent
import springfox.documentation.RequestHandler
import springfox.documentation.builders.{PathSelectors, RequestHandlerSelectors}
import springfox.documentation.spi.DocumentationType
import springfox.documentation.spring.web.plugins.Docket

class SwaggerConfig {
  @Bean
  def api(): Docket = {
    new Docket(DocumentationType.SWAGGER_2).select.apis(RequestHandlerSelectors.any).paths(PathSelectors.any).build
  }

}
