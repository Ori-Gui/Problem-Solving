import sys

def main():
    K = int(sys.stdin.readline())
    result = []
    
    for _ in range(K):
        n = int(sys.stdin.readline())
        
        if n != 0:
            result.append(n)
        else:
            result.pop()
            
    print(sum(result))
    
if __name__ == "__main__":
    main()
