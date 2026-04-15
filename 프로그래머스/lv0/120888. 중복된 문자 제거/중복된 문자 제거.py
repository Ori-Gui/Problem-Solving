def solution(my_string):
    answer = ''
    for i in my_string:
        if i in answer:
            continue
        else:
            answer += i
    return answer