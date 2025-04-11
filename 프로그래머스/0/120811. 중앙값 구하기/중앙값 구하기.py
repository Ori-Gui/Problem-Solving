def solution(array):
    array.sort()
    answer = array[len(array)//2]
    return answer