n = int(input())
pentagon = 1
for i in range(1, n+1):
    pentagon += (3*i+1)
print(pentagon % 45678)