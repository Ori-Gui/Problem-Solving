import sys

def main():
  n, m = map(int, sys.stdin.readline().split())
  trees = list(map(int, sys.stdin.readline().split()))
  answer = 0
  l, r = 1, max(trees)

  while l <= r:
    mid = (l + r) // 2
    Sanggeun = 0
    for tree in trees:
      Sanggeun += (tree - mid) if tree > mid else 0
    if Sanggeun >= m:
      l = mid + 1
      answer = mid
      if Sanggeun == m:
        break
    else:
      r = mid - 1
      
  print(answer)
  
if __name__ == '__main__':
    main()
