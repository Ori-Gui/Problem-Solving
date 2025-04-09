import sys
from itertools import combinations

def main():
    N = int(sys.stdin.readline().rstrip())
    max_num = 0
    winner = 0
    for k in range(N):
        cards = list(map(int, sys.stdin.readline().rstrip().split()))
        comb = list(combinations(cards, 3))
        for i in range(len(comb)):
            if sum(comb[i]) % 10 >= max_num:
                max_num = sum(comb[i]) % 10
                winner = k + 1
    print(winner)
    
if __name__ == "__main__":
    main()
