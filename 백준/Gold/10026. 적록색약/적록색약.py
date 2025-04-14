from collections import deque
import sys
import copy


def bfs(n, grid, visited, s):
    # 상, 하, 좌, 우
    dx = [0, 0, -1, 1]
    dy = [1, -1, 0, 0]

    queue = deque([(s[0], s[1])])  # 시작점을 큐에 추가
    color = grid[s[0]][s[1]]  # 시작점의 색깔
    visited[s[0]][s[1]] = True  # 시작점 방문 처리

    while queue:
        x, y = queue.popleft()  # 큐에서 하나의 원소를 꺼내기
        # 상하좌우 탐색
        for i in range(4):
            nx, ny = x + dx[i], y + dy[i]
            # 범위 안에 있고, 방문하지 않았으며, 같은 색깔인 경우
            if 0 <= nx < n and 0 <= ny < n and not visited[nx][ny]:
                if grid[nx][ny] == color:
                    visited[nx][ny] = True  # 방문 처리
                    queue.append((nx, ny))  # 큐에 추가


def count(n, grid):
    visited = [[False] * n for _ in range(n)]  # 방문 여부를 저장하는 2차원 리스트
    cnt = 0  # 구역의 개수

    for i in range(n):
        for j in range(n):
            # 방문하지 않은 경우
            if not visited[i][j]:
                bfs(n, grid, visited, (i, j))  # BFS 탐색
                cnt += 1  # 구역의 개수 증가

    return cnt


def main():
    n = int(sys.stdin.readline().strip())  # 그리드의 크기
    grid = [list(map(str, sys.stdin.readline().strip()))
            for _ in range(n)]  # 그리드
    weakness_grid = copy.deepcopy(grid)  # 적록색약 그리드

    for i in range(n):
        for j in range(n):
            # 적록색약인 경우, 빨간색을 초록색으로 변경
            if weakness_grid[i][j] == "R":
                weakness_grid[i][j] = "G"

    print(count(n, grid), count(n, weakness_grid))


if __name__ == "__main__":
    main()
