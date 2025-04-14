n, x = map(int, input().split())
a = list(map(int, input().split()))
answer = list()
for i in a:
    if i < x:
        answer.append(str(i))
print(" ".join(answer))