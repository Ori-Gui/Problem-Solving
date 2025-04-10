from collections import Counter

A = int(input())
B = int(input())
C = int(input())

init_counter = Counter(['0', '1', '2', '3', '4', '5', '6', '7', '8', '9'])
counter = Counter(str(A*B*C)) + init_counter

for i in range(10):
    print(counter['%d' %(i)]-1)