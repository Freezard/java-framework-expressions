# TEST PROGRAM 
# assignments

# Input (one.mtx two.mtx must be in this dir)
a one.mtx <
b two.mtx < 

# Compund correct expr
c a b + =
c _ >
d a b + a b + + = 
d _ >
e a a a + + =
e _ >
f a b + = _ >

# Bad leftvalue
#_ a =     
#a b + c =
