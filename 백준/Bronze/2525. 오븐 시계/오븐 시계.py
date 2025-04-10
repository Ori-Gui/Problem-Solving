A, B = map(int, (input().split(' ')))
C = int(input())

B = B+C
for i in range(0, int(B/60)):
    if B >= 60:
        A += 1
        B = B - 60
        if A >= 24:
            A = A - 24

print('%d %d' % (A,B))