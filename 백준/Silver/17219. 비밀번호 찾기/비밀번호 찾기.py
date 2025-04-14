import sys

def main():
    N, M = map(int, sys.stdin.readline().rstrip().split())
    pw = {}
    for _ in range(N):
        site, password = sys.stdin.readline().rstrip().split()
        pw[site] = password
    for _ in range(M):
        print(pw[sys.stdin.readline().rstrip()])
        
if __name__ == "__main__":
    main()
