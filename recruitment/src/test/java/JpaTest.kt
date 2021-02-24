import org.lxc.recruitment.MySpringBootApplication
import org.lxc.recruitment.entity.UserEntity
import org.lxc.recruitment.repository.UserRepository
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner

/**
 * 注意版本冲突问题
 */
@RunWith(SpringRunner::class)
@SpringBootTest(classes = [MySpringBootApplication::class])
class JpaTest {
    @Autowired
    lateinit var userRepository: UserRepository

    @Test
    fun insert() {
        val user = UserEntity()
        user.account = "zhangsan"
        user.password = "123456"
        user.problem = "你的名字叫什么?"
        user.answer = "张三"
        user.type = "1"
        userRepository.save(user)
    }

    @Test
    fun test() {
        val users = userRepository.findAll()
        println(users)
    }

}