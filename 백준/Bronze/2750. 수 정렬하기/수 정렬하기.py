n = int(input())
nums = []

for i in range(n):
    nums.append(int(input()))

for num in sorted(nums):
    print(num)