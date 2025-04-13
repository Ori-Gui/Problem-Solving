T = int(input())

for i in range(T):
    T_list = list(map(str, (input().split(' '))))
    result = float(T_list.pop(0))
    for j in T_list:
        if j == '@':
            result *= 3
        elif j == '%':
            result += 5
        elif j == '#':
            result -= 7
    print('%0.2f' % result)