#/bin/bash

# Script to run framework

# No check of args here

# Args are factory class, tokens class and an optional 
# "program"-file (collections of expressions to create a final result).
java edu.gu.hajo.rpn.Main ${1} ${2} ${3}
 
 exit 0