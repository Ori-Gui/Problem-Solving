import sys

def main():
    N = int(sys.stdin.readline().rstrip())
    x_list = [0] * 10001
    
    for _ in range(N):
        x = int(sys.stdin.readline().rstrip())
        x_list[x] += 1
    
    for i in range(10001):
        if x_list[i] != 0:
            for _ in range(x_list[i]):
                print(i)

if __name__ == "__main__":
    main()
