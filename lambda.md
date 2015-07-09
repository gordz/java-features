# Lambdas

## Functional Programming Versus OO
Functional programming - we abstract over behaviour. Thinking in terms of immutable values and functions that translate between them.
OO Programming - abstracting over data.

Functional programming - functions as data - functions as first class citizens. Immutability - we pass values and get back results , without side effects.

A higher-order function is a function that either takes another function as an argument or returns a function as its result (e.g map, filter, collect, Comparators.comparing etc).

Lambdas and the steam API allows us to focus on the 'what', and not the 'how'. We focus on what the transformations / operatios are, rather that how they are applied.

Side effect free functions / pure functions - these are easy to reason about, we can tell what they do based on the return types, and we end up with less focus on the "how" - aim to write side effect free code

Functions with no side effects don’t change the state of anything else in the program or the outside world - aim to mimimise side effects in your code.

## Lambda Expressions
Compact way of passing around behaviour.

## Anonymous Inner Classes
Provide a way to pass around code as data - we want to pass some behaviour into a method - lambdas improve this - cleaner syntax, remove boiler platte. Anonymous classes are unweildy, as we end up passing
an object that wraps the behaviour.

Lambda provide a concise way to represent code as data - passing behaviours. Represent a block of code that can be assigned to a SAM interface.

# Lambdas
Javac interfers the type of a lambda expression from its context.

The target type of a lambda expression is the type of the context in
which the lambda expression appears—for example, a local variable
that it’s assigned to or a method parameter that it gets passed into.

A lambda expression is a method without a name that is used to pass around be‐
havior as if it were data.

# Java 8 Effectively Final and Lambdas
Lambdas can refer to variables in the enclosing scope that are either explicitly final, or effectively final (they are not assigned again).
Referring to a non final variable will cause compilation to fail.

# SAM -> Functional INterfaces
a A SAM is a functional interface - an interface with a single abstract method.
SAMs are used to represent an action - they encode a piece of behaviour. 
The method name is ignored when a lambda is assigned to a target type SAM - only the method parameters and return type matter.

It doesn’t matter what the single method on the interface is called — it’ll get matched up to your lambda expression as long as it has a compatible
method signature.

A functional interface is an interface with a single abstract method that is used as the type of a lambda expression.

The type of a lambda expression is inferred based on its context.

# Java 7 Target Type Inference
e.g Map<string,String> map = new HashMap<>();

The compiler can infer the generic arguments / types based on the target.

# Streams API
Allows us to write collection processing code at a higher level of abstraction - provides functional programming abstractions/operations  collections - abstracting the behaviour.

- Fluently conveys what we are doing - without boiler plate required for things such as iteration, which often obscures the task/behaviour being implemented.
- Avoid boiler plate iteration - less focus on the how, more focus on the what.
- Ability to easily parallelize loop code. 
- Internal iteration - traditional Java code exposes an iterator that is manipulated manually (external iteration) - forEach method hides this, and provides internal iteration. 

- External iteration style code is hard to parallelize - it is serial in nature.

A Stream is a tool for building up complex operations on collections using a functional approach.

Operations ona  stream derived from a collection will not modify that collection.

Lazy stream operations (e.g filter() ) do not execute until a terminal operation is invoked. Many of the stream operations are lazy.

The general pattern is to form a sequence of lazy operations chained together and then to have a single eager operation at the end that generates your result. We can string together lots
of different operations over our collection and iterate over the collection only once. By waiting until we know more about what result and operations are
needed, we can perform the computations more efficiently.

Stream operations are often chained together.

Consider exposing collections of domain models as streams - this better encapsulates the domain models data structures - no risk of exposing the internals to modification. Java 8 style is to expose
collections as streams.

- Internal iteration is a way of iterating over a collection that delegates more controlover the iteration to the collection.
• A Stream is the internal iteration analogue of an Iterator .
• Many common operations on collections can be performed by combining methods on Stream with lambda expressions.

## Refactoring
Replace getters on domain pojos that return collection classes (Sets, List etc), with methods that return a stream.

for loops can be replaced with forEach as an intermediate setp when refactoring lambdas.

## Stream Antipatterns (Dont do these)
Rather than chaining the method calls, you could force the evaluation of each function individually following a sequence of steps -> harder to read, as less efficient, as intermediate collections are created at each step, and makes code hard to parallelize/


## collect(toList()) 
An eager operation that generates a list from the values in a Stream

## map
Apply a function to each element in the stream, returning a new stream containing the transformed elements. The argument and result can be different types. Replaces a value in a Stream with a new
value. 

Takes just a lambda and whose purpose is to apply the function to every element in the Stream , returning a new Stream .

## filter
Filter (remove) items from the stream according to a predictate (test function). Itsm passing the test will be retained (where the predicate returns true).

## flapMap
Takes a function that operations on each element of the stream, that returns a new stream, and concatenates/flattens the resulting streams into a single stream.

Returns a stream consisting of the results of replacing each element of this stream with the contents of a mapped stream produced by applying the provided mapping function to each element.
Each mapped stream is closed after its contents have been placed into the resulting stream.

flatMap() takes a function that takes each element of the stream, and returns a stream. The resulting mapped streams are then flattened into a single stream. Flattens a stream of streams, product when the 
map function produces a stream.

# min
Find the minimum value in a stream, using a comparator. IntStream / LongStream provide their own min() functions that dont require a comparator.

# max
Find the maximum in a stream, given a comparator.

# reduce
Given a collection, fold/reduce the items in the collection using a reduction function, to produce a single result. Given a collection , produce a single result.

This follows the pattern, where the accumulator and the combine function vary with the implementation of the pattern.

Object accumulator = initialValue;
for(Object element : collection) {
	accumulator = combine(accumulator, element);
}

# Lambda Refactorings
- if statement in a for loop - good candidate for a filter.
- Move static methods on utility classes to interfaces where appropriate - move methods related to a concept to a more appropriate location.

# Primtive Type Specialisations
Streams API / Functional API differentiates between boxed and unboxed primitives - can allow improvements in performance and memory usage (e.g avoid cost of boxing / unboxing, and size associated
with boxed types ).

Stream functions mapToLong, mapToInt, mapToFloat, mapToDouble provide the ability to work / map to primitive types, and avoid the overhead associated with boxing and unboxing.

Specialised streams also exist for primitives, again to avoid performance overheads of boxing / unboxing.

Use primitive specialised streams and functions where possible for performance. Additional functionality is also available on these streams.

# Inference
The parameter types of a lambda are inferred from the target type, and the inference follows these rules:
• If there is a single possible target type, the lambda expression infers the type from
the corresponding argument on the functional interface.
• If there are several possible target types, the most specific type is inferred.
• If there are several possible target types and there is no most specific type, you must
manually provide a type.

# @FunctionalInterface
Apply to any interface that is to be used as a functional interface. Compiler will peform checks to ensure it is a SAM. Usually indicates the implementation does not maintain state - it is a function.

## BiFunction
A specialised BinaryOperation, with both input args being the same type.

# Default Method
Feature to allow methods on interfaces - allow changes to interface without breaking implementations. Default methods can call other methods defined in the interface - good fit for strategy pattern.
Default methods can only modify their child classes by calling methods on them, as interfaces have no state/instance fields.

Default methods can facilitate multiple inheritance. Interfaces give you functionality but no fields / state

# Static Methods on Interfaces
Allow utility methods (static methods) to be located in more appropriate locations - on interfaces instead of separate utilty classes - meth

When there’s a good semantic reason for a method to relate to a concept, it
should always be put in the same class or interface rather than hidden in a utility class
to the side - motivation behind allowing static methods on interfaces.

# Optional
The goal of Optional is twofold. First, it encourages the coder to make ap‐
propriate checks as to whether a variable is null in order to avoid bugs. Second, it
documents values that are expected to be absent in a class’s API. 

# Summary
- A significant performance advantage can be had by using primitive specializedlambda expressions and streams such as IntStream .
• Default methods are methods with bodies on interfaces prefixed with the keyword default.
• The Optional class lets you avoid using null by modeling situations where a value may not be present.

# Best Practices
Prefer Optional and Stream.empty() over null.

# General 
Java type erasure replaces type parameters with Object - as a result we cannot use primitives - the best we can do is to use boxed types (e.g Integer, Float etc).
Boxed types have a memory overhead associated with them, as we are dealing with an object.
There is a computational overhead associated with boxing and unboxing - need to consider this.

Overloaded method - same name but different signature. 

