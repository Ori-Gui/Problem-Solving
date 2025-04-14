n = int(input())
a = list(map(int, input().split()))
find = int(input())
count = 0

for i in a:
    if i == find:
        count += 1
print(count)