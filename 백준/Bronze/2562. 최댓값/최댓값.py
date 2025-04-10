num_list = list()

for _ in range(9):
    n = int(input())
    num_list.append(n)

for i in range(9):
    if num_list[i] == max(num_list):
        print(max(num_list))
        print(i+1)