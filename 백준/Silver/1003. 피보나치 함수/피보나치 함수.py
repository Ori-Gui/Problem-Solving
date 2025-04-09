import sys

def fibonacci(n, cnt):
    if n == 0:
        cnt[n] = (1, 0)
    elif n == 1:
        cnt[n] = (0, 1)
    elif cnt[n] != (0, 0):
        return cnt[n]
    else:
        a = fibonacci(n - 1, cnt)
        b = fibonacci(n - 2, cnt)
        cnt[n] = (a[0] + b[0], a[1] + b[1])
    return cnt[n]

def main():
    T = int(sys.stdin.readline())
    
    for _ in range(T):
        N = int(sys.stdin.readline())
        cnt = [(0, 0)] * (N + 1)

        result = fibonacci(N, cnt)
        print(result[0], result[1])
          
if __name__ == "__main__":
    main()
