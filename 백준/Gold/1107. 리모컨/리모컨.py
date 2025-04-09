import sys

# 선택된 숫자가 고장난 버튼을 포함하는지 확인
def check(num, broken):
    num = str(num)
    for i in num:
        if int(i) in broken:
            return False
    return True


def main():
    N = int(sys.stdin.readline().rstrip())  # 채널 번호
    M = int(sys.stdin.readline().rstrip())  # 고장난 버튼 개수
    # 고장난 버튼
    if M != 0:
        broken = list(map(int, sys.stdin.readline().split()))
    else:
        broken = []

    cur = 100  # 현재 채널
    min_sub = abs(N - cur)  # 최소 버튼 조작 횟수 (초기값)

    # 100만 채널까지 탐색 (채널은 무한대 까지 있다. 하지만, 50만 까지만 입력받는다.)
    for i in range(1000001):
        # i가 고장난 버튼을 포함하지 않는다면
        if check(i, broken):
            min_sub = min(min_sub, len(str(i)) + abs(N - i))  # 최소값 갱신

    print(min_sub)


if __name__ == "__main__":
    main()
