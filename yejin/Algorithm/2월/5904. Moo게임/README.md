# [Gold V] Moo 게임 - 5904 

[문제 링크](https://www.acmicpc.net/problem/5904) 

### 성능 요약

메모리: 11476 KB, 시간: 76 ms

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

***

### 해결방안

#### 1. S(k) = S(k-1) + Mooo....ooo + S(k-1) 로 세 구간으로 나눠서 각 구간마다의 길이의 규칙을 찾기
![image](https://user-images.githubusercontent.com/81174840/220628432-af6f1a68-2e72-4a03-a8ee-5d22a92d7482.png)


#### 2. 세번째 구간의 경우에는 첫번째 구간의 문자열과 같으므로 인덱스를 줄여나가는 방안으로 생각해보기
![image](https://user-images.githubusercontent.com/81174840/220628574-c4108df0-f546-4213-b31a-6c71abcb2ba1.png)

