import sys

def main():
    N, M = map(int, sys.stdin.readline().split())
    d = set()
    b = set()
    
    for _ in range(N):
        n = sys.stdin.readline().strip()
        d.add(n)
    for _ in range(M):
        m = sys.stdin.readline().strip()
        b.add(m)
    
    result = sorted(list(d & b))
    print(len(result))
    print(*result, sep='\n')
    
if __name__ == "__main__":
    main()
