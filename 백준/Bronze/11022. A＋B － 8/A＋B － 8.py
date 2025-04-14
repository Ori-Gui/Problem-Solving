T = int(input())

for i in range(0, T):
    A, B = map(int, (input().split(' ')))
    print("Case #%d: %d + %d = %d" % ((i+1),A,B,(A+B)))
    