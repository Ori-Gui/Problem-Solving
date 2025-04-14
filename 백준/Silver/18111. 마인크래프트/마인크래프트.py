import sys

def main():
    N, M, B = map(int, sys.stdin.readline().split())
    ddang = [list(map(int, sys.stdin.readline().split())) for _ in range(N)]
    
    min_t = sys.maxsize
    result_h = 0
    
    for h in range(min(map(min, ddang)), 257):
        dig_block = 0
        cover_block = 0
        for i in range(N):
            for j in range(M):
                if ddang[i][j] < h:
                    cover_block += h - ddang[i][j]
                else:
                    dig_block += ddang[i][j] - h
        
        inventory = B + dig_block - cover_block
        time = dig_block * 2 + cover_block
        
        if inventory >= 0:
            if min_t >= time:
                min_t = time
                result_h = h
                
    print(min_t, result_h)
        
if __name__ == "__main__":
    main()
