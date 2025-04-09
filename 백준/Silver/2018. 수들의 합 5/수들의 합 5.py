import sys


def main():
    N = int(sys.stdin.readline().rstrip())
    cnt = 1  # 자기 자신

    for i in range(1, N // 2 + 1):
        total = 0
        j = i
        while total < N:
            total += j
            j += 1
        if total == N:
            cnt += 1

    print(cnt)


if __name__ == "__main__":
    main()
