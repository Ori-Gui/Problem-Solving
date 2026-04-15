def solution(n):
    divisors = []  # 약수를 저장할 리스트
    for i in range(1, n+1):
        if n % i == 0:  # n이 i로 나누어 떨어지면 i는 n의 약수
            divisors.append(i)  # 약수를 리스트에 추가
    return divisors