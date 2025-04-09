import sys

def main():
    n = int(sys.stdin.readline())
    stack = []
    result = []
    nums = []
    
    for _ in range(n):
        a = int(sys.stdin.readline())
        nums.append(a)

    for i in range(1, n + 1):
        stack.append(i)
        result.append('+')
        while stack and stack[-1] == nums[0]:
            stack.pop()
            result.append('-')
            nums.pop(0)
    
    if stack:
        print('NO')
    else:
        for i in result:
            print(i)
    
if __name__ == "__main__":
    main()
