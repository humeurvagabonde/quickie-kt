package cocktail.quickie

import java.util.*

/**
 - Developpé par Jetbrains depuis 2010
 - langage statique, tournant sur la JVM (Java, Groovy, Scala, Ceylon, Clojure)
 - "moderne" = concis, pragmatique
 - interroperable (plus que Scala)
 */

/* *********************************************** */
// parametres nommes + valeur par défaut
// pas de point-virgule
// reecriture sous forme d'expression
fun printCurly(collection: Collection<Int>): String {
    return collection.joinToString(prefix = "{", postfix = "}")
}

// forme Expression
// type de retour inféré
fun methodeParametres(name: String, number: Int = 42, toUpperCase: Boolean = false) =
        (if (toUpperCase) name.toUpperCase() else name) + number

fun exempleAppelMethode(): String {
    return (methodeParametres("a") +
            methodeParametres("b", number = 1) +
            methodeParametres("c", toUpperCase = true) +
            methodeParametres(name = "d", number = 2, toUpperCase = true))
}

/* *********************************************** */
// multiligne
fun multilineStringTemplate() =
        """
You can use raw strings to write multiline text.
There is no escaping here, so raw strings are useful for writing regex patterns,
you don't need to escape a backslash by a backslash.
String template entries (${42}) are allowed here.
"""

/* *********************************************** */
// when
fun getDefaultLocale2(deliveryArea: String) = when (deliveryArea.toLowerCase()) {
    "germany", "austria" -> Locale.GERMAN
    "usa", "great britain" -> Locale.ENGLISH
    "france" -> Locale.FRENCH
    else -> Locale.ENGLISH
}


/* *********************************************** */
// Nullability ; Elvis operator ; Definition d'une classe
class PersonalInfo (val email: String?)
class Client (val personalInfo: PersonalInfo?)

interface Mailer {
    fun sendMessage(email: String, message: String)
}

fun sendMessageToClient(client: Client?, message: String?, mailer: Mailer) {
    val email = client?.personalInfo?.email
    if (email != null && message != null) {
        mailer.sendMessage(email, message)
    }
}

/*
public void sendMessageToClient(@Nullable Client client, @Nullable String message, @NotNull Mailer mailer) {
        if (client == null || message == null) return;

        PersonalInfo personalInfo = client.getPersonalInfo();
        if (personalInfo == null) return;

        String email = personalInfo.getEmail();
        if (email == null) return;

        mailer.sendMessage(email, message);
    }
*/

/* *********************************************** */
// Data class ; construction
// Shop.kt
// Extension
val Shop.allOrderedProducts: Set<Product> get() {
    return customers.flatMap { it.orderedProducts }.toSet()
}

fun callExtensions(): Set<Product> = shop.allOrderedProducts

/* *********************************************** */
// type alias / higher order function
typealias Operation = (Int, Int) -> Int
fun addValue(operation: Operation): Int {
    return operation(10,20)
}

val mult: Operation = { i: Int, j: Int -> i * j }
fun appelAddValue() {
    addValue(mult) //This will print 200
    addValue {num1, num2 -> num1 - num2} //This will print -10
}

/* *********************************************** */
// Co-routine (RxJava)
// Protocol oriented programming (utilisation des interfaces avec proprietes + fonctions)