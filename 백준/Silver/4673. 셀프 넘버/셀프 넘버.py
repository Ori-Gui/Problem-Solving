def self_number(n):
    total = n
    while n > 0:
        total += n % 10
        n //= 10
    return total


def main():
    self_num = [True] * 10001
    for i in range(1, 10001):
        if self_num[i]:
            print(i)
            n = i
            while True:
                n = self_number(n)
                if n > 10000:
                    break
                self_num[n] = False


if __name__ == "__main__":
    main()
