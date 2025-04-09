n = int(input())

a = (n-2)
b = (1000000000-2) // 6
k = 1

for i in range(1, b):
    if n == 2:
        print(2)
        break
    if a == 0 or n == 1:
        print(i)
        break
    a = (n-2) // (6*k)
    k += (i + 1)
        