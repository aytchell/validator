# Java Validator library

This library provides methods to validate user input of various type with fun and ease.
The original motivation for this library arose from a backend project where several more
or less complex json input types for a REST API had to be validated. Of course you can
use it for any API that needs to validate its input.

## Introduction

The basic concept goes like this:

```java
void someApiMethod(String aString, Long aValue) throws ValidationException {
    Validator.expect(aString, "aString").notNull().notBlank().lengthAtMost(255);
    Validator.expect(aValue, "aValue", "exta info").notNull().greaterThan(2048);

    // do work with 'aString' and 'aValue'
}
```

The arguments of `expect()` are the value to be checked and a name which will be
reported to the client (in case of a violation).
Each of the methods in the call chain performs a very specific check. If this check fails
a `ValidationException` will be thrown. This exception will do its best to
create a human readable expressive error message.

Examples of the produces error message are:
> Expecting that 'length of aString' (value: 312) is at most 255

> Expecting that 'aValue' (value: 12, info: extra info) is greater than 2048

Note that
 * The output will contain the actual (`12`) and the expected value (`2048`) as well as
   the expected relation (`is greater than`)
 * It will contain the name of the value (as given to `.expect()`)
 * The method `.expect()` takes an optional third parameter with extra information
 * You have to call `.notNull()` before any other check. This is enforced by
   the API and the used types.

## Maven

```xml
    <dependency>
        <groupId>com.github.aytchell</groupId>
        <artifactId>validator</artifactId>
        <version>2.3.0</version>
    </dependency>
```

## Basic validations

The currently supported 'basic' object types are
 * `Boolean`
 * `Short`, `Integer`, `Long`
 * `Double`
 * `String`
 * `LocalDate`, `LocalDateTime`, `ZonedDateTime`
 
 For each of these types a small set of useful checks is provided in a typesafe manner.
 A reasonably modern IDE will list you the available checks while typing the code.
 
 ## Checks of container types
 
 Furthermore you can validate `List`s and `Set`s (including their content) and the values
 stored in a `Map` (not yet the keys or complete entries). `List` and `Set` both provide 
 shortcut methods for validating numerical (`.eachNumericEntry()`) or string 
 (`.eachStringEntry()`) content:
 
 ```java
        final List<Long> longList = List.of(11L, 21L, 31L, 41L);

        try {
            Validator.expect(longList, "longList").notNull()
                    .eachNumericEntry(v -> v.notNull().lessEqThan(31, "2^{5}-1"));
        } catch (ValidationException e) {
            final String xx = e.getMessage();
            System.out.println(xx);
        }
 ```

The above code will print
> Expecting that 'longList[3].<41>' (value: 41) is less or equal than '2^{5}-1' (value: 31)

Note that:
 * Where appropriate, methods take an optional parameter "name of the value to compare with"
   (which in this example is `2^{5}-1`)
 * The output will contain the index of the failing `List` entry
   
Due to the way java generics work internally it is not possible to provide the correct
validator based on the `List`s type parameter. That's why you have to manually select if
you're comparing strings or values. (It will result in a `ClassCastException` if you chose
the wrong method. Sorry for that...)

## Advanced validations

In case the simple checks for `String`, `Long` et al are not enough for your sophisticated 
domain objects there are some more advanced validations:

### Custom predicate for numeric values
```java
    final Integer number = 51;

    Validator.expect(number, "number").notNull().passes(v -> (v % 2) == 0, "is even");
    Validator.expect(number, "number").notNull().passes(this::isEven, "is even");
    // --> Expecting that 'number' (value: 51) is even (but is not)
```

### Custom predicate for strings
```java
    final String pharaosName = "...";

    Validator.expect(pharaosName, "pharaosName").notNull()
            .passes(this::validUnicodeHieroglypghs, "is written in hieroglyphs");
    // --> Expecting that 'pharaosName' (value: '...') is written in hieroglyphs (but is not)
```

### Regular expressions for strings

```java
    final String emailAddress = "some_guy@github";
    final String emailRegex = "^[a-zA-Z0-9._-]+@[a-zA-Z0-9-]+\\.[a-zA-Z.]{2,18}$";
    final Pattern p = Pattern.compile(emailRegex, Pattern.CASE_INSENSITIVE);

    Validator.expect(emailAddress, "emailAddress").notNull().matches(p, "email address");
    // --> Expecting that 'emailAddress' (value: 'some_guy@github') matches regex 'email address' ↵
    //     (value: '^[a-zA-Z0-9._-]+@[a-zA-Z0-9-]+\.[a-zA-Z.]{2,18}$')
```


## Checks of custom types

In case you have a pojo coming in as a parameter (e.g. from a deserialized json) the above
mentioned checks won't help you. There is a 'CustomValidator' for these cases.
Assume you have a pojo `Thingy` like this
```java
    @Value
    private static class Thingy {
        String name;
        Integer value;
    }
```

then you can validate it this way:

```java
    final Thingy thing = new Thingy("name", 4711);

    Validator.expect(thing, "thing", "some extra info").notNull().passes(
            v -> {
                Validator.expect(v.name, "name").notNull().notBlank();
                Validator.expect(v.value, "value").notNull().greaterThan(12321);
            }
    );
```

The error message will look like this
> Expecting that 'thing.value' (value: 4711, info: (about thing) some extra info) is greater than 12321

## Validating optional values

From time to time you have some input values which are optional but when
given have to comply to some rules. Or parameters which are optional depending on
other input values. You've already seen the method `.expect(...).notNull()` which
checks that a given input is not `null`.

The other available methods are:
 * `.ifNotNull()` all the following checks are only performed if the tested value is not `null`
 * `.ifTrue(boolean condition)` checks are only performed if the given condition is `true`
 * `.ifFalse(boolean condition)` checks are only performed if the given condition is `false`
 * `.ifGivenAndTrue(Boolean condition)` checks are only performed if the given `Boolean` is given
   (i.e. not `null`) and `true`:
   
   | value of condition | null | true | false |
   |--------------------|------|------|-------|
   | checks performed   |  no  |  yes |  no   |
 * `.ifNotGivenOrFalse(Boolean condition)`  checks are only performed if the given `Boolean` is not
   given (i.e. `null`) or `false`:
   
   | value of condition | null | true | false |
   |--------------------|------|------|-------|
   | checks performed   | yes  |  no  | yes   |

## Validating "multi level" pojos

The above mentioned CustomValidator is able to validate any kind of pojo no matter how many classes
are nested:

```java
    @Value
    private static class Wrapper {
        String name;
        List<Thingy> things;
    }
```

with this validation code

```java
        final Wrapper wrapper = new Wrapper("wrapper", List.of(
                new Thingy("seven", 7),
                new Thingy("five", 5),
                new Thingy("three", 3)
        ));

        Validator.expect(wrapper, "wrapper").notNull().passes(
                w -> {
                    Validator.expect(w.name, "name").notNull().notBlank();
                    Validator.expect(w.things, "things").notNull().eachCustomEntry(
                            t -> {
                                Validator.expect(t.name, "name").notNull().notBlank();
                                Validator.expect(t.value, "value").notNull().greaterThan(5);
                            }
                    );
                }
        );
```

will give you this error message:
> Expecting that 'wrapper.things[1].value' (value: 5) is greater than 5

## 'Recursive' validation

Taking the above example to the extreme you can also validate recursive structures:

```java
    @Value
    private static class Node {
        Integer value;
        List<Node> childNodes;
    }
```

```java
    public void processTree(Node root) throws ValidationException {
        // validate the input
        Validator.expect(root, "root").notNull().passes(CustomValidatorTest::isValidTree);

        // process the tree
        ...
    }

    private static void isValidTree(Node root) throws ValidationException {
        // demand that every contained value is less than 1000 ... business decision. Don't ask
        Validator.expect(root.value, "value").notNull().lessThan(1000);
        Validator.expect(root.childNodes, "childNodes").ifNotNull().eachCustomEntry(
                CustomValidatorTest::isValidTree);
    }
```

Called with this data:
```java
    void doSomething() throws ValidationException {
        final Node root = new Node(1, List.of(
                new Node(2, List.of(
                        new Node(3, List.of()),
                        new Node(4, List.of(
                                new Node(5, null),
                                new Node(6, null)
                        ))
                )),
                new Node(7, List.of(
                        new Node(8, List.of()),
                        new Node(9, List.of(
                                new Node(10, List.of(
                                        new Node(6546, null)
                                )),
                                new Node(12, null)
                        ))
                ))
        ));

        processTree(root);
    };
```

You will get this lovely error message:
> Expecting that 'root.childNodes[1].childNodes[1].childNodes[0].childNodes[0].value' (value: 6546) is less than 1000

which might look scary on first sight but gives the caller a good impression why his/her input
was rejected.

## Further examples

There are plenty of unit tests. Please consult them for further examples:
 * [BooleanValidatorTest](src/test/java/com/github/aytchell/validator/BooleanValidatorTest.java)
 * [CustomValidatorTest](src/test/java/com/github/aytchell/validator/CustomValidatorTest.java)
 * [DoubleValidatorTest](src/test/java/com/github/aytchell/validator/DoubleValidatorTest.java)
 * [ListValidatorTest](src/test/java/com/github/aytchell/validator/ListValidatorTest.java)
 * [LongValidatorTest](src/test/java/com/github/aytchell/validator/LongValidatorTest.java)
 * [MapValidatorTest](src/test/java/com/github/aytchell/validator/MapValidatorTest.java)
 * [SetValidatorTest](src/test/java/com/github/aytchell/validator/SetValidatorTest.java)
 * [StringValidatorTest](src/test/java/com/github/aytchell/validator/StringValidatorTest.java)
 * [ZonedDateTimeValidatorTest](src/test/java/com/github/aytchell/validator/ZonedDateTimeValidatorTest.java)

## License

Apache 2.0 License

Created and maintained by [Hannes Lerchl](mailto:hannes.lerchl@googlemail.com)

Feel free to send in pull requests. Please also add unit tests and adapt the README if appropriate.
