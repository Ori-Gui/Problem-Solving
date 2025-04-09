import sys

def main():
    S = int(sys.stdin.readline().rstrip())
    N = 1
    while N * (N + 1) / 2 <= S:
        N += 1
    
    print(N - 1)
    
if __name__ == "__main__":
    main()
