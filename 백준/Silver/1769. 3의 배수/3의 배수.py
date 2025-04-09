import sys

def main():
    X = int(sys.stdin.readline().rstrip())
    cnt = 0
    
    while X >= 10:
        X = sum(map(int, str(X)))
        cnt += 1
    
    print(cnt)
    print('YES' if X % 3 == 0 else 'NO')
    
if __name__ == "__main__":
    main()
