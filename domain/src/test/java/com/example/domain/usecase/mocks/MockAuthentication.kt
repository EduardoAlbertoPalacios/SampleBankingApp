import Information.FAKE_EMAIL
import Information.FAKE_IMAGE
import Information.FAKE_NAME
import Information.FAKE_PASSWORD
import Information.FAKE_SURNAME
import com.example.domain.entities.AuthEntity
import com.example.domain.entities.UserEntity

fun mockAuthEntity() = AuthEntity(
    email = FAKE_EMAIL,
    password = FAKE_PASSWORD,
)

fun mockUserEntity() = UserEntity(
    name = FAKE_NAME,
    surname = FAKE_SURNAME,
    email = FAKE_EMAIL,
    password = FAKE_PASSWORD,
    imageUrl = FAKE_IMAGE
)

private object Information {
    const val FAKE_NAME = "Eduardo"
    const val FAKE_SURNAME = "Palacios"
    const val FAKE_EMAIL = "edupalacios@hotmail.com"
    const val FAKE_PASSWORD = "Android231."
    const val FAKE_IMAGE = "TestImage.jpg"
}
