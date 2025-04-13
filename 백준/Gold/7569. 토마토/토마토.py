from collections import deque
import sys

def tomato(M, N, H, tomatoes):
    # 상, 하, 좌, 우, 위, 아래
    dx = [1, -1, 0, 0, 0, 0]
    dy = [0, 0, 1, -1, 0, 0]
    dz = [0, 0, 0, 0, 1, -1]

    queue = deque()  # 익은 토마토의 위치와 현재 날짜를 저장할 큐 -> BFS

    # 익은 토마토의 위치를 큐에 추가
    for z in range(H):
        for y in range(N):
            for x in range(M):
                if tomatoes[z][y][x] == 1:
                    queue.append((x, y, z, 0))  # 초기 날짜는 0

    while queue:
        # 현재 탐색 중인 상자(토마토)의 위치와 날짜
        cur_x, cur_y, cur_z, days = queue.popleft()

        for i in range(6):
            # 주변 상자(토마토) 탐색 (6방향)
            nx, ny, nz = cur_x + dx[i], cur_y + dy[i], cur_z + dz[i]

            # 상자의 범위를 벗어나면 무시
            if nx < 0 or ny < 0 or nz < 0 or nx >= M or ny >= N or nz >= H:
                continue

            # 익지 않은 토마토인 경우 익히기
            if tomatoes[nz][ny][nx] == 0:
                tomatoes[nz][ny][nx] = 1
                # 익은 토마토의 위치를 큐에 추가
                queue.append((nx, ny, nz, days + 1))

    # 모든 토마토가 익은 상태 확인
    for z in range(H):
        for y in range(N):
            for x in range(M):
                if tomatoes[z][y][x] == 0:
                    return -1  # 익지 않은 토마토가 있는 경우

    return days  # 모든 토마토가 익은 경우


def main():
    # 입력 받기
    M, N, H = map(int, sys.stdin.readline().split())  # 가로, 세로, 높이
    tomatoes = []
    for _ in range(H):
        box = []
        for _ in range(N):
            row = list(map(int, sys.stdin.readline().split()))
            box.append(row)
        tomatoes.append(box)

    # 결과 출력
    print(tomato(M, N, H, tomatoes))


if __name__ == "__main__":
    main()
