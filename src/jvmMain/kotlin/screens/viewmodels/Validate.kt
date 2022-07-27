package screens.viewmodels

class Validate {
    var isValid = true
    fun <T> validate(field: T?): Validate {
        if (isValid) {
            isValid = field != null
        }
        return this
    }
}