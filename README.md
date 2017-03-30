# streamInJS

## Why ?
This project is made for scholar purpose by three students of Université de Paris-Est Marne-la-Vallée (UPEM).
The project is then to be evaluated by our teacher. 

## What?
The goal of this course was to learn how to interpret languages and what were the different steps of optimisation available.
To be as efficient as possible, we worked on a simplified version of JavaScript called SmallJS.
Its grammar is poor as it is not what matters in this project.

The goal is to add to the language the possibility to use streams with anonymous function without changing the grammar.
The VM has to generate bytecode to be interpreted when a stream operation is read.
The result of the execution has to be something similar to the stream APIs available in several languages.

## How do I launch it?

Just run the method main() in the class fr.umlv.smalljs.Main and give the path of your JS file as an argument.

## Functions available

generate : function creating an infinite stream with a given function in parameters
enumerate : creates an infinite stream by iterating a given function to a given element

limit: method used to cut a stream after the Xth element, X being a parameter
skip: similar to limit but keeps the part of the stream after the X index
map: method that applies a function to all the elements of a stream
filter : method that filters the streams using a predicate applied to all elements of the stream (a predicate must return a boolean value)

forEach : consumes the stream and apply a given function to all its element
findFirst : consumes the stream and return its first element

## Remarks

Our streams accept 'undefined' as a value.  
Do not alter or consume infinite streams without using limit or skip on it.  
The grammar of SmallJS is not made to be toyed with, so try to stick to the style shown in our samples. For example, only the "//" commentary style will be parsed.  
You may find some fancy behaviours if you try enough. 

Once again, the goal was more to learn and practice than to develop the next JS interpreter. 