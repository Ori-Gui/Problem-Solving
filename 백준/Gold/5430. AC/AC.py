from collections import deque
import sys


def AC_operation(p, n, arr):
    # 문자열 p를 함수 리스트로 변환
    operations = deque(p.replace('RR', ''))  # 연속해서 두 번 R이 나오면 무시
    r_cnt = 0  # 뒤집기 연산의 횟수를 저장하는 변수
    
    # 초기배열이 비어있는데 D 연산이 있는 경우 에러
    if n == 0 and 'D' in p:
        return 0
    
    # 배열에 있는 수를 순서대로 수행
    for op in operations:
         # 배열이 비어있는데 D 연산이 있는 경우 에러
        if n == 0 and 'D' in p:
            return 0
        # R 연산은 뒤집기, 뒤집은 횟수를 증가
        if op == 'R':
            r_cnt += 1
        # D 연산은 첫 번째 수를 버리기, 뒤집은 횟수에 따라 첫 번째 또는 마지막 수를 제거
        elif op == 'D' and n > 0:
            if r_cnt % 2 == 0:
                arr.popleft()  # 뒤집은 횟수가 짝수일 때는 첫 번째 수를 제거
            else:
                arr.pop()  # 뒤집은 횟수가 홀수일 때는 마지막 수를 제거
            n -= 1

    # 최종 결과를 반환
    if r_cnt % 2 == 1:
        arr.reverse()  # 뒤집은 횟수가 홀수일 때 배열을 최종적으로 뒤집음
    return list(arr)


def main():
    T = int(sys.stdin.readline().strip())  # 테스트 케이스의 개수

    for _ in range(T):
        p = sys.stdin.readline().strip()  # 수행할 함수
        n = int(sys.stdin.readline().strip())  # 배열에 들어있는 수의 개수
        # 배열이 비어있을 때
        if n == 0:
            arr = deque(sys.stdin.readline().strip()[1:-1])
        else:
            arr = deque(map(int, sys.stdin.readline().strip()
                        [1:-1].split(',')))  # 배열에 들어있는 정수

        result = AC_operation(p, n, arr)

        if result == 0:
            print('error')
        else:
            print('[{}]'.format(','.join(map(str, result))))


if __name__ == "__main__":
    main()
