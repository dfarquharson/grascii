# Grascii
Make Ascii-Art Graphs out of a Simple Graph Format (.grascii)

## Examples

### Simple Input
```
A=0=>B
B=1=>C
```

### Simple Output
```
+---+
| A 00
+---+0
+---+0
| B 00
|   11
+---+1
+---+1
| C 11
+---+
```

### More Input
```
A=0=>B
A=1=>C
```

### More Output
```
+---+
| A 111
|   001
+---+01
+---+01
| B 001
+---+11
+---+1
| C 11
+---+
```

### Yet Another Input
```
A=0=>B
B=1=>C
A=2=>C
```

### Yet Another Output
```
+---+
| A 222
|   002
+---+02
+---+02
| B 002
|   112
+---+12
+---+12
| C 112
|   222
+---+
```

### Bigger Input
```
A=0=>B
B=1=>C
C=2=>D
D=3=>E
A=4=>C
A=5=>D
```

### Bigger Output
```
+---+
| A 5555
|   4445
|   0045
+---+045
+---+045
| B 0045
|   1145
+---+145
+---+145
| C 1145
|   4445
|   2255
+---+25
+---+25
| D 225
|   555
|   33
+---+3
+---+3
| E 33
+---+
```

## Current Limitations
- Single character node names
- Limit of 10 edges (0-9)
- Can cause edge blockages on more complicated graphs (epic fail that demands a more fundamental edge-drawing rethink)

## TODO
- [x] hello world
- [x] hello ScalaTest
- [x] monoidal graph combination
- [x] parse `*.grascii` files into in-memory Graph (talk about the abandonment of parser combinators and the reasons for that)
- [ ] generate ascii art graphs from in-memory Graph
- [ ] ScalaCheck usage (especially on the draw function, as it asserts strong properties on an infinite domain and range)
- [ ] code coverage
- [ ] write alternative implementation in clojure
- [ ] write alternative implementation in python (toolz, hypothesis, pytest, pytest-cov)

## Actual TODO
- [ ] place nodes on the grid (non-overlapping)
- [ ] draw actual graphs on the grid
- [x] dump out the ascii art

## Libraries
|Library|Purpose|GitHub|Docs|
|-------|-------|------|----|
|circe|json serialization|https://github.com/circe/circe|https://circe.github.io/circe/|

## Notes
- From the perspective of mechanical sympathy, this implementation is, to use the scientific term: "shit".

## The Story
I like ascii art. I like graphs. Why not make nice little visual representations of graphs with ascii art?
Sounds fun! Let's do it!
In the beginning, I was pretty fixated on this idea of a fixed 2D grid where each cell was a single character.
That's still mostly what this is, but my mind has been E X P A N D E D in the process as well.
I began to notice a pattern of relying heavily on the 2D "Coordinate" of a given "Cell",
and thus were some primordial `case class`es born.
I also found myself realizing that I was looking up those `Coordinate`s in the 2D array A LOT,
essentially treating that `Grid` as a `Map`, indexed by `Coordinate`.
So, again I made that implicit structure explicit, and `GridMap` was born.

TL;DR: Programming really is "just Category Theory". Define your "Objects" and "Morphisms" and you're done :)

P.S. There's _always_ a Monoid Masher in there somewhere ;)

## Functional Programming
- Data and Functions (Objects and Morphisms)
- Referentially Transparent (No implicit params or implicit effects. No implicit anything!)
- Immutable State
- Not just about avoiding mutable state, but also about avoiding _incidental_ state
  - This might even be _more_ important than immutability!

## Testing
- Is awesome
- Is a design technique
- Provides rapid feedback
- Is an experiment playground
- Is a rapid fire version of the scientific method
- Is incredibly fun!
- Should test desired behavior, not arbitrary implementation details
- Unless those arbitrary implementations details leak, in which case they matter
- But don't do that
- Don't forget to have fun though :)

## Call Outs
- Refactoring with Types: https://github.com/dfarquharson/grascii/commit/1d1332257b979b1803f2253c7ba232ddfe2d3bc2
