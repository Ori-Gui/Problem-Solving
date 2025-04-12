T = int(input())

for _ in range(T):
    k = int(input())
    n = int(input())

    apt = [[i for i in range(15)]]
    
    for i in range(1, 15):
        l = [0]
        for j in range(1, 15):
            l.append(l[j-1] + apt[i-1][j])
        apt.append(l)
        
    print(apt[k][n])