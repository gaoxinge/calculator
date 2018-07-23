# calculator

A java implementation of calculator based on [shunting-yard algorithm](https://en.wikipedia.org/wiki/Shunting-yard_algorithm):

- a learning project
- an extensionable plugin

## how to use

```
> gradle build
> java -jar ./build/libs/calculator.jar
```

## function

### operator

|variable|operand number|precedence|associative|function|
|--------|--------------|----------|-----------|--------|
|+       |unary         |-         |right      |pos     |
|-       |unary         |^         |right      |neg     |
|**      |binary        |^         |right      |pow     |
|*       |binary        |-         |left       |mul     |
|/       |binary        |^         |left       |div     |
|+       |binary        |-         |left       |add     |
|-       |binary        |^         |left       |sub     |

### function

- random
- add
- sub
- mul
- div
- pow
- log
- pos
- neg
- abs
- max
- min
- asin
- acos
- atan
- sinh
- cosh
- tanh
- sin
- cos
- tan

## TODO

- more rigorous args check for function
- unit test

## reference

- [shunting-yard algorithm](https://github.com/gaoxinge/calculator/issues/2)
- [architecture](https://github.com/gaoxinge/calculator/issues/1)