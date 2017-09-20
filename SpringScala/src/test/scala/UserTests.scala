import java.awt.PageAttributes.MediaType
import java.util

import com.google.common.net.HttpHeaders
import com.sun.org.apache.xerces.internal.impl.dv.util.Base64
import junit.framework.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.http.HttpEntity
import org.springframework.test.context.junit4.SpringRunner

@RunWith(classOf[SpringRunner])
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class UserTests {
  @Autowired
  var template: TestRestTemplate = _
  @Test def testPostCreateUser() = {
    val headers = new HttpHeaders
    headers.add("Authorization", "Basic " + new String(Base64.encodeBase64(("root" + ":" + "root").getBytes)))
    headers.setContentType(MediaType.APPLICATION_JSON)
    headers.setAccept(util.Arrays.asList(MediaType.APPLICATION_JSON))
    val user = new Users
    user.setId(101)
    user.setUsername("Test")
    user.setPassword("Test")
    user.setEnabled(true)
    val entity = new HttpEntity(user, headers)
    val result = template.postForObject("/api/users", entity, classOf[String])
    println(result)
  }
}
