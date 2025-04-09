X = int(input())
cnt = 0

for i in format(X, 'b'):
    if i == '1':
        cnt += 1

print(cnt)
