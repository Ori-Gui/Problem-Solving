def main():
    arr = list(map(int, input().split(' ')))
    arr.sort()
    print(arr[-2])
    
if __name__ == '__main__':
    main()