D, H, W = map(int, input().split())
print(int((D**2/((W/H)**2+1))**0.5), int((D**2/((H/W)**2+1))**0.5))