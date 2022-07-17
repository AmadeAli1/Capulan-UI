package helpers
import model.actores.UserType

/**
 * @author Amade ali <p>Interface de Marcacao para enums que podem ser usados para uso de compose generico</p>
 * @see UserType
 * @see TabItem
 */
interface MarkupEnum {
    fun getName(): String
}