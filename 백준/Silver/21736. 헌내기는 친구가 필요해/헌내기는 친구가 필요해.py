import sys

def main():
    N, M = map(int, sys.stdin.readline().split()) # 대학 캠퍼스 크기
    campus = [] # 대학 캠퍼스
    for _ in range(N):
        campus.append(list(sys.stdin.readline().rstrip()))
        
    stack = [] # 스택
    visited = [[False for _ in range(M)] for _ in range(N)] # 방문 여부
    
    for i in range(N):
        for j in range(M):
            if campus[i][j] == 'I': # 나는 도연
                stack.append((i, j))
                visited[i][j] = True
                break

    friends = 0 # 친구 TT
    
    while stack: # DFS
        x, y = stack.pop()
        for dx, dy in ((0, 1), (1, 0), (0, -1), (-1, 0)):
            nx, ny = x + dx, y + dy
            if 0 <= nx < N and 0 <= ny < M and not visited[nx][ny] and campus[nx][ny] != 'X':
                stack.append((nx, ny))
                visited[nx][ny] = True
                if campus[nx][ny] == 'P':
                    friends += 1 # 친구 찾음 TT
    
    if friends == 0:
        print("TT")
    else:
        print(friends)
    
if __name__ == "__main__":
    main()
