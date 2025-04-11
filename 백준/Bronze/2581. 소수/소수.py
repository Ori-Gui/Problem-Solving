# Chat GPT
def eratosthenes(n):
    # 에라토스테네스의 체를 이용하여 2부터 n까지의 소수를 구하는 함수
    sieve = [True] * (n + 1)
    sieve[0] = sieve[1] = False
    for i in range(2, int(n**0.5) + 1):
        if sieve[i]:
            for j in range(i*2, n+1, i):
                sieve[j] = False
    return sieve

m = int(input())
n = int(input())

sieve = eratosthenes(n)  # n까지의 소수를 구합니다.

prime_list = [i for i in range(m, n+1) if sieve[i]]  # m 이상 n 이하의 소수를 구합니다.

if not prime_list:  # 소수가 없으면 -1을 출력합니다.
    print(-1)
else:
    print(sum(prime_list))  # 소수의 합을 출력합니다.
    print(min(prime_list))  # 소수의 최솟값을 출력합니다.