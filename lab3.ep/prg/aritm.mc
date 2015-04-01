# TEST PROGRAM 
# arithmetic 

# Input (one.mtx two.mtx must be in this dir)
# Matrices are square no test of bad dimensions
# Test mul, transp, exp manually for bad dimensions

a one.mtx <
b two.mtx <

# Simple
a b + _ >
a b - _ >
a b * _ >
a 2 ^ _ >
a ! _ >

# Intermediate value
a b + a b + + _ >
a a a + + _ >

# CORRECT LINE
a b * a b + - 2 ^ _ >

# Io has value
#c _ < d _ < + _ >

# Bad expr
#a 
#a +
#a a
#a + +
#_ >


