import sys

def main():
  n = int(sys.stdin.readline())
  yong = list(map(int, sys.stdin.readline().split()))
  mixed = 2e9
  l, r = 0, n-1
  y1, y2 = 0, 0
  
  while l < r:
    cur_mixed = yong[l] + yong[r]
    if mixed > abs(cur_mixed):
      y1, y2 = yong[l], yong[r]
      mixed = abs(cur_mixed)
      if mixed == 0:
        break
    if cur_mixed <= 0:
      l += 1
    else:
      r -= 1
  print(y1, y2)
    
if __name__ == '__main__':
  main()
