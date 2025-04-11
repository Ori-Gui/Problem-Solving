def solution(sides):
    answer = 1 if sum(sides) - max(sides) > max(sides) else 2
    return answer