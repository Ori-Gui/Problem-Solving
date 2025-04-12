ascending_num = [1, 2, 3, 4, 5, 6, 7, 8]
descending_num = [8, 7, 6, 5, 4, 3, 2, 1]

input_num = list(map(int, input().split()))

if input_num == ascending_num:
    print('ascending')
elif input_num == descending_num:
    print('descending')
else:
    print('mixed')
