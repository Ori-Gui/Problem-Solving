def solution(age):
    alphabet = 'abcdefghij'  # 각 알파벳에 대응되는 숫자를 저장한 문자열
    answer = ''  # 알파벳으로 된 나이를 저장할 변수
    while age > 0:
        remainder = age % 10  # 나이를 10으로 나눈 나머지
        answer = alphabet[remainder] + answer  # 나머지에 해당하는 알파벳을 문자열 앞에 추가
        age //= 10  # 나이를 10으로 나눈 몫
    return answer