# [level 3] GPS - 1837 

[문제 링크](https://school.programmers.co.kr/learn/courses/30/lessons/1837) 

### 성능 요약

메모리: 84.1 MB, 시간: 74.22 ms

### 구분

코딩테스트 연습 > 2017 카카오코드 본선

### 채점결과

정확성: 100.0<br/>합계: 100.0 / 100.0

### 제출 일자

2025년 04월 09일 12:07:55

### 문제 설명

<h2>GPS</h2>

<p>카카오 택시 개발자 Jay-G는 다음 업데이트를 준비하기 위해 개선사항을 위한 여러 피드백을 받았다. 그중에서 손님이 자주 탑승하는 위치를 추천해주었으면 한다는 의견이 많았다.</p>

<p>다음 업데이트 준비를 위해 Jay-G는 택시의 승하차 및 이동 경로를 수집하여 분석하기 시작하였다. 데이터를 분석하던 Jay-G는 몇 가지 특이사항을 발견했다. 택시의 이동 경로를 GPS를 통해 수집하게 되는데, GPS 신호 불량, 통신 오류 등 다양한 원인으로 위치의 오류가 발생한 것을 알게 되었다. 다만 승차 위치와 하차 위치는 오류가 없는 것으로 확인이 되었다. </p>

<p>개발자 Jay-G는 수집한 이동 경로의 오류를 최소한으로 수정하여 좀 더 정확한 이동 경로를 구하고 싶어 한다.</p>

<p>택시는 다음과 같은 조건으로만 이동한다. 먼저 택시는 거점을 이동해 다니며, 거점 간의 이동은 해당하는 도로가 있는 경우에만 가능하다. 또한, 교통 상황에 따라 택시는 한 거점에 머무를 수 있고, 왔던 길을 되돌아갈 수 있다. 모든 도로는 방향이 별도로 없는 왕복 도로이다.</p>

<p><img src="https://t1.kakaocdn.net/codefestival/gps1.png" title="" alt="Graph 1"></p>

<p>예를 들어, 위 그래프에서 택시가 다음과 같이 시간대별로 이동 경로를 보내왔다. </p>
<table class="table">
        <thead><tr>
<th>t</th>
<th>위치</th>
</tr>
</thead>
        <tbody><tr>
<td>1</td>
<td>1</td>
</tr>
<tr>
<td>2</td>
<td>2</td>
</tr>
<tr>
<td>3</td>
<td>3</td>
</tr>
<tr>
<td>4</td>
<td>3</td>
</tr>
<tr>
<td>5</td>
<td>6</td>
</tr>
<tr>
<td>6</td>
<td>7</td>
</tr>
</tbody>
      </table>
<p>하지만 위의 택시가 보내온 경로에는 <code>거점 3</code>에서 <code>거점 6</code>으로 이동할 수 있는 도로가 없으므로 이동 경로에 오류가 있다. </p>

<p><img src="https://t1.kakaocdn.net/codefestival/gps2.png" title="" alt="Graph 2"></p>

<p>이러한 오류를 최소한으로 수정하여 이동 가능한 경로로 만들고 싶다. 이 경우 1회의 오류를 수정하여 다음과 같이 이동 가능한 경로를 만들 수 있다. 시간 <code>t=4</code>의 위치를 <code>거점 5</code>로 한 번 수정하면 이동 가능한 경로가 된다. </p>
<table class="table">
        <thead><tr>
<th>t</th>
<th>위치</th>
</tr>
</thead>
        <tbody><tr>
<td>1</td>
<td>1</td>
</tr>
<tr>
<td>2</td>
<td>2</td>
</tr>
<tr>
<td>3</td>
<td>3</td>
</tr>
<tr>
<td><strong>4</strong></td>
<td><strong>5</strong></td>
</tr>
<tr>
<td>5</td>
<td>6</td>
</tr>
<tr>
<td>6</td>
<td>7</td>
</tr>
</tbody>
      </table>
<p><img src="https://t1.kakaocdn.net/codefestival/gps3.png" title="" alt="Graph 3"></p>

<p>이와 비슷하게 시간 <code>t=4</code>의 위치를 <code>거점 4</code>로 바꾸거나, 시간 <code>t=5</code> 위치를 <code>거점 5</code>로 바꾸면 이동 가능한 경로로 만들 수 있다. 위의 경우 수정한 오류의 개수는 1개이다. </p>
<table class="table">
        <thead><tr>
<th>t</th>
<th>위치</th>
</tr>
</thead>
        <tbody><tr>
<td>1</td>
<td>1</td>
</tr>
<tr>
<td>2</td>
<td>2</td>
</tr>
<tr>
<td>3</td>
<td>3</td>
</tr>
<tr>
<td><strong>4</strong></td>
<td><strong>4</strong></td>
</tr>
<tr>
<td>5</td>
<td>6</td>
</tr>
<tr>
<td>6</td>
<td>7</td>
</tr>
</tbody>
      </table><table class="table">
        <thead><tr>
<th>t</th>
<th>위치</th>
</tr>
</thead>
        <tbody><tr>
<td>1</td>
<td>1</td>
</tr>
<tr>
<td>2</td>
<td>2</td>
</tr>
<tr>
<td>3</td>
<td>3</td>
</tr>
<tr>
<td>4</td>
<td>3</td>
</tr>
<tr>
<td><strong>5</strong></td>
<td><strong>5</strong></td>
</tr>
<tr>
<td>6</td>
<td>7</td>
</tr>
</tbody>
      </table>
<p>위와 같이 택시가 보내온 경로에서 이동 가능한 경로로 만드는 최소의 오류 수정 횟수를 구하여라. </p>

<h3>입력 형식</h3>

<p>주어지는 입력은 총 다섯 가지로, 거점 개수 <code>n</code>과 도로의 개수 <code>m</code>, 각 거점 간의 연결된 도로 정보 <code>edge_list</code>, 택시가 시간대별로 보내오는 거점 정보의 총 개수 <code>k</code>, 그리고 머물렀던 거점의 정보 <code>gps_log</code>이다. 제한조건은 아래와 같다.</p>

<ul>
<li><code>2 &lt;= n &lt;= 200</code></li>
<li><code>1 &lt;= m &lt;= 10,000</code></li>
<li><code>2 &lt;= k &lt;= 100</code></li>
<li><code>edge_list</code>는 <code>m × 2</code> 크기의 2차원 배열로, 각 행의 두 값은 도로가 잇는 두 거점의 번호를 의미한다.</li>
<li>거점의 번호는 1부터 n까지 숫자이다.</li>
<li>모든 도로는 양방향 통행이 가능하다.</li>
<li>입력되는 데이터에서 항상 모든 거점 간 경로가 있음이 보장되지 않는다.</li>
<li><code>gps_log</code>의 시작 거점과 도착 거점은 바뀔 수 없다.</li>
</ul>

<h3>출력 형식</h3>

<p>이동 가능한 경로로 만들 수 있는 최소의 오류 수정 횟수를 리턴한다. 올바른 경로로 수정하는 것이 불가능할 경우 <code>-1</code>을 리턴한다.</p>

<h3>예제 입출력</h3>
<table class="table">
        <thead><tr>
<th>변수명</th>
<th>값</th>
</tr>
</thead>
        <tbody><tr>
<td>n</td>
<td>7</td>
</tr>
<tr>
<td>m</td>
<td>10</td>
</tr>
<tr>
<td>edge_list</td>
<td>[[1, 2], [1, 3], [2, 3], [2, 4], [3, 4], [3, 5], [4, 6], [5, 6], [5, 7], [6, 7]]</td>
</tr>
<tr>
<td>k</td>
<td>6</td>
</tr>
<tr>
<td>gps_log</td>
<td>[1, 2, 3, 3, 6, 7]</td>
</tr>
<tr>
<td>answer</td>
<td>1</td>
</tr>
</tbody>
      </table><table class="table">
        <thead><tr>
<th>변수명</th>
<th>값</th>
</tr>
</thead>
        <tbody><tr>
<td>n</td>
<td>7</td>
</tr>
<tr>
<td>m</td>
<td>10</td>
</tr>
<tr>
<td>edge_list</td>
<td>[[1, 2], [1, 3], [2, 3], [2, 4], [3, 4], [3, 5], [4, 6], [5, 6], [5, 7], [6, 7]]</td>
</tr>
<tr>
<td>k</td>
<td>6</td>
</tr>
<tr>
<td>gps_log</td>
<td>[1, 2, 4, 6, 5, 7]</td>
</tr>
<tr>
<td>answer</td>
<td>0</td>
</tr>
</tbody>
      </table>
<h3>예제에 대한 설명</h3>

<p>두 예제 모두 <code>edge_list</code>의 데이터는 본문의 그림과 같은 예이다.<br>
첫 번째 테스트 케이스에서 <code>gps_log</code>로 주어진 경로 중 <code>거점 3</code>에서 <code>거점 6</code>으로 가는 도로가 없다. 여기서 시간 <code>t=4</code>의 위치를 <code>거점 5</code>로 한 번 수정하면 이동 가능한 경로가 된다.<br>
두 번째 테스트 케이스는 <code>gps_log</code>로 주어진 경로가 모두 도로로 연결된 경우이므로 수정이 필요 없다. </p>


> 출처: 프로그래머스 코딩 테스트 연습, https://school.programmers.co.kr/learn/challenges