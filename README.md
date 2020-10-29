# Drama4J
A scripting tool to script your conditional scenarios in a very declarative and fluent way. Just like a drama, for Java. You can leisurely implement your own conditions.

## Usage

You can literally script like a charm as below

```java
Condition c1 = when(countdown(3000)) 
                .andThen(inputNumber(3))  
                    .thenDo(showText("Hi, ... you can begin.")) 
                .andThen(inputNumber(3))
                    .thenDo(showText("Hi, ... your input is 3.")) 
                .andThen(countdown(4000)) 
                    .thenDo(showText("What are you gonna do next...?")); 

Condition c2 = when(inputNumber(1))
                    .andThen(inputNumber(2))
                    .andThen(inputNumber(3))
                    .andThen(inputNumber(4))
                .thenDo(showText("HAHA!"));

Condition c3 = when(once(inputNumber(7)), once(inputNumber(8)), inputNumber(9))
                .thenDo(showText("7, 8, 9! HUH!?"));

when(c1, c2, c3)
    .andThen(countdown(2000))
        .thenDo(showText("Yes, you got it!"))
    .andThen(countdown(1000))
        .thenDo(showText("B-)"));
```
