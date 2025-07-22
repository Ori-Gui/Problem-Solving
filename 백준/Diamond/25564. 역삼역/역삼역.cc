#include <bits/stdc++.h>
using namespace std;
 
template<int SIZE = 506000, int CSIZE = 27>
struct SuffixArray {
    string src; 
    int len = -1;
    int sa[SIZE], lcp[SIZE];
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
 
        for (i = 0; i < len; i++) 
            rk[i] = src[i] - 'a';
        for (p = 0, o = 1; p + 1 < len; o <<= 1) {
            CountSort();
            p = tmp[sa[0]] = 0;
            for (int i = 1; i < len; i++)
                tmp[sa[i]] = Cmp(sa[i - 1], sa[i]) ? ++p : p;
            swap(tmp, rk);
        }
 
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
 
const int MAXIN = 506000;
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
 
SuffixArray<MAXIN, 27> sf;
char s[MAXIN];
int n, k;
 
long long Solve() {
    for (int i = n - 1; i >= 0; i--)
        in[(i << 1) + 1] = s[i];
    for (int i = 0; i <= n; i++)
        in[i << 1] = '#';
    n = n * 2 + 1; 
    Manacher(n);
 
    vector<pair<int, int>> goods;
    for (int i = 0; i < n; i++) {
        if (dp[i] >= k) {
            if ((dp[i] & 1) == (k & 1))
                goods.push_back({ i / 2 - k / 2, k });
            else
                goods.push_back({ i / 2 - (k + 1) / 2, k + 1 });
        }
    }
    sort(goods.begin(), goods.end());
    sf.src = s;
    sf.Init();
 
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
