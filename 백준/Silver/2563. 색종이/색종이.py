import sys

def main():
    paper = [[0 for _ in range(100)] for _ in range(100)]

    N = int(sys.stdin.readline().rstrip())
    for _ in range(N):
        x, y = map(int, sys.stdin.readline().rstrip().split())
        for i in range(x, x + 10):
            for j in range(y, y + 10):
                paper[i][j] = 1

    print(sum(map(sum, paper)))

if __name__ == "__main__":
    main()
