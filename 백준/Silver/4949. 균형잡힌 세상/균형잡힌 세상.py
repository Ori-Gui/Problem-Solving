import sys

def main():
    while True:
        s = sys.stdin.readline().rstrip()
        stack = []
        flag = True
        if len(s) == 1 and s == ".":
            break       
        else:
            for i in s:
                if i == "(" or i == "[":
                    stack.append(i)
                if i == ")":
                    if len(stack) != 0:
                        if stack[-1] == "(":
                            stack.pop()
                        else:
                            flag = False
                            break
                    else:
                        flag = False
                        break
                elif i == "]":
                    if len(stack) != 0:
                        if stack[-1] == "[":
                            stack.pop()
                        else:
                            flag = False
                            break
                    else:
                        flag = False
                        break

        if len(stack) == 0 and s[-1] == i and flag:
            print("yes")
        else:
            print("no")
    
if __name__ == "__main__":
    main()
