import sys

def main():
    n = int(sys.stdin.readline())
    lv = []
    
    if n == 0:
        print(0)
        return
    
    def round(a):
        if a - int(a) >= 0.5:
            return int(a) + 1
        else:
            return int(a)
    
    for _ in range(n):
        opinion = int(sys.stdin.readline().strip())
        lv.append(opinion)
        
    lv.sort()
    k = round(n * 0.15)

    if n - (2 * k) >= 1:
        lv = lv[k:n-k]
        print(round(sum(lv)/(n - (2 * k))))
    
if __name__ == "__main__":
    main()
