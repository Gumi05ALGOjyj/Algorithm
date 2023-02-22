# [Gold V] Moo 게임 - 5904 

[문제 링크](https://www.acmicpc.net/problem/5904) 

### 성능 요약

메모리: 11480 KB, 시간: 76 ms

### 분류

분할 정복(divide_and_conquer), 재귀(recursion)

### 문제 설명

<p>Moo는 술자리에서 즐겁게 할 수 있는 게임이다. 이 게임은 Moo수열을 각 사람이 하나씩 순서대로 외치면 되는 게임이다.</p>

<p>Moo 수열은 길이가 무한대이며, 다음과 같이 생겼다. </p>

<pre>m o o m o o o m o o m o o o o m o o m o o o m o o m o o o o o </pre>

<p>Moo 수열은 다음과 같은 방법으로 재귀적으로 만들 수 있다. 먼저, S(0)을 길이가 3인 수열 "m o o"이라고 하자. 1보다 크거나 같은 모든 k에 대해서, S(k)는 S(k-1)과 o가 k+2개인 수열 "m o ... o" 와 S(k-1)을 합쳐서 만들 수 있다.</p>

<pre>S(0) = "m o o"
S(1) = "m o o m o o o m o o"
S(2) = "m o o m o o o m o o m o o o o m o o m o o o m o o"</pre>

<p>위와 같은 식으로 만들면, 길이가 무한대인 문자열을 만들 수 있으며, 그 수열을 Moo 수열이라고 한다.</p>

<p>N이 주어졌을 때, Moo 수열의 N번째 글자를 구하는 프로그램을 작성하시오.</p>

### 입력 

 <p>첫째 줄에 N (1 ≤ N ≤ 10<sup>9</sup>)이 주어진다.</p>

### 출력 

 <p>N번째 글자를 출력한다.</p>

### 풀이
 <p>1) moo</br>
 2) moomooomoo</br>
 문자의 길이가 s(k-1)*2 + k+3 으로 증가하는 것을 이용하여, input 값이 넘을 때 까지 길이 증가</br>
 if) 이전 사이즈의 +1번째 index는 무조건 m (moo'm'ooomoo 4번째)</br>
 else if) 현재 사이즈 - 이전 사이즈 +1번째 index는 input값 - (현재 사이즈- 이전사이즈) 로 다시 재귀 (moomooo'moo'  8~10번째)</br>
 else) o가 k+2 있는 구간은 o 리턴 (moom'ooo'moo 5~7번째)</p>
