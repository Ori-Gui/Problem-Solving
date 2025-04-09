import sys


def main():
    # 입력 받기
    N, M = map(int, input().split())  # 수의 개수 N, 합을 구해야 하는 횟수 M
    nums = list(map(int, input().split()))  # N개의 수
    sections = []  # 구간 합을 구해야 하는 리스트

    for _ in range(M):
        i, j = map(int, input().split())  # 합을 구해야 하는 구간 i와 j
        sections.append((i, j))

    # 누적 합 배열 만들기
    acc_sums = [0] * (N + 1)
    for i in range(1, N + 1):
        acc_sums[i] = acc_sums[i - 1] + nums[i - 1]

    # 각 쿼리에 대해 구간 합을 계산하고 출력
    for i, j in sections:
        print(acc_sums[j] - acc_sums[i - 1])


if __name__ == "__main__":
    main()
