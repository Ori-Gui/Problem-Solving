import sys

def main():
    S = set()
    M = int(sys.stdin.readline().rstrip())

    for _ in range(M):
        op = sys.stdin.readline().rstrip().split()
        
        if len(op) == 2:
            op[1] = int(op[1])
            
        if op[0] == "add":
            S.add(op[1])
        elif op[0] == "remove":
            S.discard(op[1])
        elif op[0] == "check":
            print(1 if op[1] in S else 0)
        elif op[0] == "toggle":
            if op[1] in S:
                S.discard(op[1])
            else:
                S.add(op[1])
        elif op[0] == "all":
            S = set(range(1, 21))
        elif op[0] == "empty":
            S.clear()
        
if __name__ == "__main__":
    main()
