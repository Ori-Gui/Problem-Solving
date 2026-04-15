N, M = map(int, input().split())
arr = list()
for i in range(N*2):
    arr.append(list(map(int, input().split())))
    
arr_sum = []
for i in range(N):
    temp_list = []
    for j in range(M):
        temp_list.append(str(arr[i][j] + arr[i+N][j]))
    arr_sum.append(temp_list)

for k in arr_sum:
    print(" ".join(k))
    