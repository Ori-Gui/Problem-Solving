import itertools

def solution(babbling):
    bab = ["aya", "ye", "woo", "ma"]
    babP = []
    answer = 0
    for i in range(4):
        babP += list(map(''.join, itertools.permutations(bab, i+1)))
    for b in babbling:
        if b in babP:
            answer += 1
    return answer