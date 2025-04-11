from collections import Counter

def solution(array, n):
    counter = Counter(array)
    return counter[n]