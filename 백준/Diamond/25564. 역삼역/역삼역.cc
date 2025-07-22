#include <bits/stdc++.h>
using namespace std;
 
// --- Suffix Array 구현 (Manber-Myers + Kasai) ---
template<int SIZE = 506000, int CSIZE = 27>
struct SuffixArray {
    string src; 
    int len = -1;
    int sa[SIZE], lcp[SIZE];
 
    // 내부 사용값
    int rk[SIZE], cnt[SIZE], tmp[SIZE];
    int o = 1;
 
    inline bool Cmp(int a, int b) {
        if (rk[a] != rk[b]) return rk[a] < rk[b];
        if (a + o < len && b + o < len)
            return rk[a + o] < rk[b + o];
        return a + o > b + o;
    }
 
    void CountSort() {
        int m = max(rk[sa[len - 1]] + 1, CSIZE) + 1;
        fill(cnt, cnt + m, 0);
        for (int i = 0; i < len; i++) 
            cnt[i + o < len ? rk[i + o] + 1 : 0]++;
        for (int i = 1; i <= m; i++) 
            cnt[i] += cnt[i - 1];
        for (int i = len - 1; i >= 0; i--) 
            tmp[--cnt[i + o < len ? rk[i + o] + 1 : 0]] = i;
 
        fill(cnt, cnt + m, 0);
        for (int i = 0; i < len; i++) 
            cnt[rk[i]]++;
        for (int i = 1; i < m; i++) 
            cnt[i] += cnt[i - 1];
        for (int i = len - 1; i >= 0; i--) 
            sa[--cnt[rk[tmp[i]]]] = tmp[i];
    }
 
    void Init() {
        len = src.size();
        int p, i;
 
        // Manber-Myers 알고리즘
        for (i = 0; i < len; i++) 
            rk[i] = src[i] - 'a';
        for (p = 0, o = 1; p + 1 < len; o <<= 1) {
            CountSort();
            p = tmp[sa[0]] = 0;
            for (int i = 1; i < len; i++)
                tmp[sa[i]] = Cmp(sa[i - 1], sa[i]) ? ++p : p;
            swap(tmp, rk);
        }
 
        // Kasai 알고리즘 (lcp 배열 계산)
        int num = 0;
        for (int i = 0; i < len; i++) {
            int k = rk[i];
            if (k) {
                while (src[i + num] == src[sa[k - 1] + num])
                    num++;
                lcp[k] = num;
                if(num) num--;
            }
        }
    }
};
 
// 전역 변수: 최대 입력 크기에 맞춘 배열 크기
const int MAXIN = 506000;
 
// --- Manacher 알고리즘 ---
// dp 배열: in[i]를 중심으로 한 팰린드롬의 반지름 (in: 변환된 문자열)
int dp[MAXIN * 2];
char in[MAXIN * 2];
 
void Manacher(int n) {
    int r = 0, p = 0;
    for (int i = 0; i < n; i++) {
        if (i <= r)
            dp[i] = min(dp[p * 2 - i], r - i);
        else
            dp[i] = 0;
 
        while (i - dp[i] - 1 >= 0 && i + dp[i] + 1 < n && in[i - dp[i] - 1] == in[i + dp[i] + 1])
            dp[i]++;
 
        if (r < i + dp[i]) {
            r = i + dp[i];
            p = i;
        }
    }
}
 
// --- Global SuffixArray object ---
SuffixArray<MAXIN, 27> sf;
 
char s[MAXIN];
int n, k;
 
// Solve 함수
long long Solve() {
    // Manacher을 위한 변환: 원본 s에 '#'을 끼워 넣는다.
    // 예: "banana" -> "#b#a#n#a#n#a#"
    for (int i = n - 1; i >= 0; i--)
        in[(i << 1) + 1] = s[i];
    for (int i = 0; i <= n; i++)
        in[i << 1] = '#';
    n = n * 2 + 1;  // 변환된 문자열 길이
 
    Manacher(n);
 
    // goods 벡터: (start, len) 각 원소는 원본 문자열에서,
    // 최소로 영우 조건(팰린드롬 길이가 ≥ k)이 보장되는 구간 (시작 인덱스, 최소 길이)
    vector<pair<int, int>> goods;
    for (int i = 0; i < n; i++) {
        if (dp[i] >= k) {
            // dp[i]의 홀짝에 따라 원본에서의 위치와 최소 길이 결정
            if ((dp[i] & 1) == (k & 1))
                goods.push_back({ i / 2 - k / 2, k });
            else
                goods.push_back({ i / 2 - (k + 1) / 2, k + 1 });
        }
    }
    sort(goods.begin(), goods.end());  // 시작 인덱스 기준 정렬
 
    // Suffix Array 구축 (원본 문자열 s에 대해)
    sf.src = s;
    sf.Init();
 
    // 정답 계산
    // SA에 의해 s의 서로 다른 부분 문자열은, 
    // 각 i번째 순서의 접미사 s[sa[i] ... end]에서,
    // 중복은 LCP로 제거된다.
    // goods 벡터의 첫 번째 원소가 "영우 조건"을 만족하는 최소 시작점을 나타내고,
    // 그 구간에서 조건을 만족하는 최소 끝 인덱스는: goods[j].first + goods[j].second - 1.
    // 또한, 현재 접미사에 대해 중복 제거 접두사의 길이는 lcp[rk[i]].
    // 최종적으로, 접미사 i에서 기여하는 부분 문자열 수는: (sf.len - idx),
    //   idx = max( goods[j].first + goods[j].second - 1, i + sf.lcp[sf.rk[i]] ).
    long long ans = 0;
    size_t j = 0;
    for (int i = 0; i < sf.len; i++) {
        while (j < goods.size() && i > goods[j].first)
            j++;
        if (j == goods.size()) break;
        int idx = max(goods[j].first + goods[j].second - 1, i + sf.lcp[sf.rk[i]]);
        ans += sf.len - idx;
    }
 
    return ans;
}
 
int main(){
    scanf("%d %d", &n, &k);
    scanf("%s", s);
 
    printf("%lld\n", Solve());
    return 0;
}
