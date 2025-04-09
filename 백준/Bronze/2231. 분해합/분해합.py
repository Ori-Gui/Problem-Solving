N = int(input())

for i in range(1, N+1):
    i_sum = sum(list(map(int, str(i))))
    
    if i + i_sum == N:
        print(i)
        break
    if i == N:
        print(0)