A, B, C = map(int, (input().split(' ')))
D = int(input())

C = C+D
for i in range(0, int(C/60)):
    if C >= 60:
        B += 1
        C -= 60

for i in range(0, int(B/60)):
    if B >= 60:
        A += 1
        B -= 60
        if A >= 24:
            A -= 24

print('%d %d %d' % (A,B,C))