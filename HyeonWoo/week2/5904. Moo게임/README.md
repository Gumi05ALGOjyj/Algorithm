# [Gold V] Moo 게임 - 5904 

[문제 링크](https://www.acmicpc.net/problem/5904) 

### 성능 요약

메모리: 11556 KB, 시간: 80 ms

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
직접 문자열을 생성해서 접근하면 메모리초과 혹은 시간초과가 발생한다.

k에 따른 문자열 규칙을 찾아내는 것이 중요하다.

![0C5FABC2-57D0-488F-8E1A-87B3DD01BAD4](https://user-images.githubusercontent.com/88186460/220834127-bab308ca-4b87-4037-9c59-2dc78727f09d.jpg)

위 규칙에 따라 문자열의 길이가 증가한다.

우선 문자열의 길이를 증가시키며 n이 속한 문자열의 k를 구한다.

그리고 해당 문자열에서 n이 어디에 위치했는지 확인한다.

만약 정의한 'moo + o...o' 부분, 가운데보다 작거나 크다면 k - 1에 속하므로 k - 1 경우로 돌아가 다시 확인한다.

즉 가운데에 n이 위치할 때 까지 확인한다는 말이다.
