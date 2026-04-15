def solution(numbers):
    answer = 0
    numbers.sort()
    return max(numbers[0] * numbers[1], numbers[len(numbers)-2] * numbers[len(numbers)-1])