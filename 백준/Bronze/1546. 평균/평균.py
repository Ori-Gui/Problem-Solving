N = int(input())
grade = list(map(int, input().split()))
new_grade = list()

for g in grade:
    new_grade.append((g / max(grade)) * 100)

print(sum(new_grade)/N)