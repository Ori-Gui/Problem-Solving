import sys


def main():
    N = int(sys.stdin.readline().rstrip())
    sanggeun = list(map(int, sys.stdin.readline().rstrip().split()))

    M = int(sys.stdin.readline().rstrip())
    nums = list(map(int, sys.stdin.readline().rstrip().split()))

    sanggeun.sort()
    result = [0] * M

    for k in range(M):
        i, j = 0, N - 1
        while i <= j:
            mid = (i + j) // 2
            if sanggeun[mid] == nums[k]:
                result[k] = 1
                break
            elif sanggeun[mid] < nums[k]:
                i = mid + 1
            else:
                j = mid - 1

    print(*result)


if __name__ == "__main__":
    main()
