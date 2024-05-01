# Super Quick Cheat Sheet for Kotlin

One day Kotlin will be as well known as Java ... today is not that day so this quick cheat sheet should be just
enough for the kata without having to learn the whole language.

## Classes

This is how to declare a class ...
```kotlin
class Account(var balance: Money = Money(0.0)) {
} 
```
... notice how we declare members of the class.

But if this is a domain object then we could also create a `data class`
```kotlin
data class Money(val amount: Double) {
}
```
... notice how we are using `val` to make the class immutable.

Classes in Kotlin are closed for extension by default, you need to open them up to create subclasses
```kotlin
open class Account(){
}
```

## Functions

The following is a simple method that takes a String and returns a String
```kotlin
    fun someMethod(anArgument: String): String{
        return "something"
    }
```

The following is another variant on the above
```kotlin
    fun someMethod(anArgument: String): String = "something"
```

Static methods work in a slightly different manner in that they use a companion object
```kotlin
class Account(){
    companion object{
        fun aNicerWayToCreateAnObject(){
            return Account()
        }
    }
}
```

## Tests

This is the wrong way ...
```kotlin
    @Test fun shouldBuildSomethingFromNothing(){
        assertThat(...)
    }
```

... notice the use of backticks for a much more descriptive test name ...
```kotlin
    @Test fun `should build something from nothing`(){
        asertThat(...)
    }
```

## Sugar coating
### Infix
If you have a member function that only requires a single parameter you can use `infix` to sugar coat it
Imagine a method as below:
```kotlin
class Money(val amount: Double){
    infix fun plus(val toAdd: Double){
        return Money(amount + toAdd)
    }
}
```
then we can call it it two ways:
````kotlin
@Test fun `do something sugar coated`(){
    // normal way to do it would be 
    someMoney.plus(10.0)

    // infix allows us to call it as below
    someMoney plus 10.0

}
````