L, P = map(int, input().split())
party_people = list(map(int, input().split()))

for i in range(5):
    party_people[i] -= L*P

print(*party_people)
