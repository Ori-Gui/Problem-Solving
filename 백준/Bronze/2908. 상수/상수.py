A, B = map(int, input().split())

A = int("".join(str(A))[::-1])
B = int("".join(str(B))[::-1])

print(max(A, B))