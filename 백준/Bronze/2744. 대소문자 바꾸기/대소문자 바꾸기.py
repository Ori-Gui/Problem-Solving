word = input()
answer = ""
for i in word:
    if i.isupper():
        answer += i.lower()
    else:
        answer += i.upper()
print(answer)