# Chat GPT
n, b = input().split()  # n과 b를 입력받음
b = int(b)  # b를 정수형으로 변환

# 각 알파벳에 해당하는 값
alpha_dict = {
    'A': 10, 'B': 11, 'C': 12, 'D': 13, 'E': 14, 'F': 15,
    'G': 16, 'H': 17, 'I': 18, 'J': 19, 'K': 20, 'L': 21,
    'M': 22, 'N': 23, 'O': 24, 'P': 25, 'Q': 26, 'R': 27,
    'S': 28, 'T': 29, 'U': 30, 'V': 31, 'W': 32, 'X': 33,
    'Y': 34, 'Z': 35
}

# 각 자릿수에 해당하는 값
digits = []
for i in range(len(n)):
    if n[i].isalpha():  # 알파벳일 경우
        digits.append(alpha_dict[n[i]])
    else:
        digits.append(int(n[i]))

# B진법 수 N을 10진법으로 변환
result = 0
for i in range(len(digits)):
    result += digits[i] * (b ** (len(digits) - i - 1))

print(result)  # 결과 출력
