def solution(n, k):
    answer = 12000*n + 2000*k - 2000*(min(n//10 , k))
    return answer