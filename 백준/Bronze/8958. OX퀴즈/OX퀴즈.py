test_case = int(input())
for _ in range(test_case):
    OX = list(map(str, input()))
    grade = 0
    O_plus = 1
    for i in OX:
        if i == 'O':
            grade += O_plus
            O_plus += 1
        else:
            O_plus = 1
    print(grade)
