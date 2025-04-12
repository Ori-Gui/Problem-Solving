import sys
import math

def main():
    A, B, V = map(int, sys.stdin.readline().split())
    print(math.ceil((V - A) / (A - B)) + 1)
    
if __name__ == "__main__":
    main()
