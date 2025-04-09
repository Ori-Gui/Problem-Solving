import sys
from collections import Counter

def main():
    N = int(sys.stdin.readline())
    arr = [int(sys.stdin.readline()) for _ in range(N)]
    arr.sort()
    counter = Counter(arr).most_common()
    # 산술평균
    print(round(sum(arr)/N))
    # 중앙값
    print(arr[N//2]) 
    # 최빈값
    if len(counter) > 1 and counter[0][1] == counter[1][1]:
        print(counter[1][0])
    else:
        print(counter[0][0])
    # 범위
    print(arr[-1] - arr[0])
        
if __name__ == "__main__":
    main()
