N, M = map(int, (input().split(' ')))

cut_chocolate = 0

if N >= M:
    cut_chocolate += M-1
    cut_chocolate += (N-1)*M
else:
    cut_chocolate += N-1
    cut_chocolate += (M-1)*N

print(cut_chocolate)