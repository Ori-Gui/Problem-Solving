from collections import Counter
def solution(array):
    counter = Counter(array)
    cnt = counter.most_common(len(counter))
    for i in range(1, len(cnt)):
        if cnt[0][1] == cnt[i][1]:
            return -1
    return cnt[0][0]