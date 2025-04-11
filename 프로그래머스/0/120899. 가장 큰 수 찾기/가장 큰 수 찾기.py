def solution(array):
    answer = [sorted(array)[-1], array.index(sorted(array)[-1])]
    return answer