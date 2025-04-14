import sys

def main():
    L = int(sys.stdin.readline().rstrip())
    s = sys.stdin.readline().rstrip()
    r = 31
    M = 1234567891
    value = 0

    for i in range(len(s)):
        a_i = ord(s[i]) - ord('a') + 1
        value = (value + a_i * (r ** i)) % M
    print(value)

if __name__ == "__main__":
    main()
