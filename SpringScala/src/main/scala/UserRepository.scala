import org.springframework.data.jpa.repository.support.CrudMethodMetadata
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
trait UserRepository extends CrudRepository[Users, Long] {
  def findUserByUsername(username: String): Users

}
