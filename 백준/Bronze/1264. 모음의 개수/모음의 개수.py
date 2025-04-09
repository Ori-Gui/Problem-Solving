vowels = 'aeiouAEIOU'
while True:
    cnt = 0
    a = input()
    if a == '#':
        break
    for i in a:
        if i in vowels:
            cnt += 1
    print(cnt)