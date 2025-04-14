T = int(input())

for _ in range(T):
    H, W, N = map(int, input().split())
    y = str(N % H) if N % H != 0 else str(H)
    x = str(N // H + 1) if N % H != 0 else str(N // H)
    print(y + "0" + x if int(x) < 10 else y + x)
