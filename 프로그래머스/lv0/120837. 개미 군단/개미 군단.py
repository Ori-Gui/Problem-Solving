def solution(hp):
    if hp % 5 == 0:
        return hp / 5
    else:
        return hp // 5 + (2 if (hp % 5) % 2 == 0 else 1)