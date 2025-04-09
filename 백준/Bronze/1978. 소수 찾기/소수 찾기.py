N = int(input())
num_list = list(map(int, input().split()))
cnt = len(num_list)

for i in num_list:
    for k in range(2, 101):
        if i == 1 or ((i != k) and (i % k == 0)):
            cnt -= 1
            break

print(cnt)
