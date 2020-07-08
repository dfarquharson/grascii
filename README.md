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

## Libraries
|Library|Purpose|GitHub|Docs|
|-------|-------|------|----|
|circe|json serialization|https://github.com/circe/circe|https://circe.github.io/circe/|

## Notes
- From the perspective of mechanical sympathy, this implementation is, to use the scientific term: "shit".