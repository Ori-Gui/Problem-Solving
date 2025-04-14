alphabet = 'abcdefghijklmnopqrstuvwxyz'
find = [-1]*26

string = input()

for i in range(len(alphabet)):
    for j in range(len(string)):
        if alphabet[i] == string[j] and find[i] == -1:
            find[i] = j
print(*find)
    