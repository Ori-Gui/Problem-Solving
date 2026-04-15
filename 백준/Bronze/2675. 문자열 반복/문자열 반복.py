T = input()
for i in range(int(T)):
    R, S= map(str, (input().split(' ')))
    R = int(R)
    new_str = ""
    for j in range(len(S)):
        new_str += S[j]*R
    print(new_str)