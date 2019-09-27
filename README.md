# [F19] Compiler Construction - Assignment 2

*Innopolis University, 2019*<br>
*Compiler Construction, 3rd year, 1st semester*<br>

**Author:**<br>
Gleb Petrakov

## Description

This is the second assignment of the Compiler Construction course, Innopolis University.

Task: implement AST builder and calculator.


## Grammar
```
expression -> relation
relation -> term [ ( "<" | ">" | "=" ) term ] 
term -> factor { ( "+" | "-" ) factor } 
factor -> primary { "*" primary }
primary -> integer | "(" expression ")"
integer -> Any integer number (literal constant)
```

*Metasymbols:*<br>
`[ ]` group optional elements (repeated 0 or 1 time).<br>
`{ }` group elements that can be repeated 0 or more times | denotes alternatives.<br>
`( )` simply group alternatives.<br>


## Running


### GitHub Actions
You can see output in GitHub Actions tab of the repository.<br>
Workflow triggered on push only (GitHub limitation).


### Gradle

Run `./gradlew run` (Mac OS/Unix) or `gradlew.bat run` (Windows).<br>
Gradle will execute the program with testing file "Test1.kt"

