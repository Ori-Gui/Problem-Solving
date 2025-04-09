from collections import Counter

string = input()
most_check = Counter(string.upper()).most_common(2)

if len(most_check) > 1 and most_check[0][1] == most_check[1][1]:
    print('?')
else:
    print(most_check[0][0])