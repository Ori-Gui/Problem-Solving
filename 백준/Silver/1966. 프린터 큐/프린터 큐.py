import sys

def main():
    T = int(sys.stdin.readline().rstrip())
    for _ in range(T):
        N, M = map(int, sys.stdin.readline().rstrip().split())
        queue = list(map(int, sys.stdin.readline().rstrip().split()))
        cnt = 0
        while queue:
            if queue[0] < max(queue):
                queue.append(queue.pop(0))
                M = len(queue) - 1 if M == 0 else M - 1
            else:
                cnt += 1
                if M == 0 and queue[0] == max(queue):
                    break
                else:
                    queue.pop(0)
                    M = len(queue) - 1 if M == 0 else M - 1
        print(cnt)
        
if __name__ == "__main__":
    main()
