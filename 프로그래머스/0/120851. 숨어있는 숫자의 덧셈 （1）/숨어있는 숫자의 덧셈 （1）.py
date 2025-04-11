def solution(my_string):
    answer = 0
    for i in list(my_string):
        for j in range(10):
            if i == str(j):
                answer += j
    return answer