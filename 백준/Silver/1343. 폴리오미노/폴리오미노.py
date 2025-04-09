import sys

def main():
    board = sys.stdin.readline().rstrip().split('.')
    result = ''
    
    for i in range(len(board)):
        if len(board[i]) % 2 == 1:
            print(-1)
            return
        else:
            result += 'AAAA' * (len(board[i]) // 4)
            result += 'BB' * (len(board[i]) % 4 // 2)
            result += '.'
    
    print(result[:-1])
            
if __name__ == "__main__":
    main()
