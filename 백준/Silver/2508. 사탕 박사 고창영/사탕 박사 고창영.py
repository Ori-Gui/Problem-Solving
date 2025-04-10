import sys


def main():
    t = int(sys.stdin.readline().rstrip())

    for _ in range(t):
        cnt = 0
        candy = []
        new_line = sys.stdin.readline().rstrip()
        r, c = map(int, sys.stdin.readline().rstrip().split())
        for _ in range(r):
            candy.append(sys.stdin.readline().rstrip())

        for i in range(r):
            for j in range(c):
                if j + 2 < c:
                    if candy[i][j] == ">" and candy[i][j + 1] == "o" and candy[i][j + 2] == "<":
                        cnt += 1
                if i + 2 < r:
                    if candy[i][j] == "v" and candy[i + 1][j] == "o" and candy[i + 2][j] == "^":
                        cnt += 1
        print(cnt)


if __name__ == "__main__":
    main()
