# [Gold V] 합분해 - 2225 

[문제 링크](https://www.acmicpc.net/problem/2225) 

### 성능 요약

메모리: 12248 KB, 시간: 108 ms

### 분류

수학, 다이나믹 프로그래밍

### 문제 설명

<p>0부터 N까지의 정수 K개를 더해서 그 합이 N이 되는 경우의 수를 구하는 프로그램을 작성하시오.</p>

<p>덧셈의 순서가 바뀐 경우는 다른 경우로 센다(1+2와 2+1은 서로 다른 경우). 또한 한 개의 수를 여러 번 쓸 수도 있다.</p>

### 입력 

 <p>첫째 줄에 두 정수 N(1 ≤ N ≤ 200), K(1 ≤ K ≤ 200)가 주어진다.</p>

### 출력 

 <p>첫째 줄에 답을 1,000,000,000으로 나눈 나머지를 출력한다.</p>



***

## [문제풀이]
![image](https://user-images.githubusercontent.com/81174840/224947303-e0347026-dc0e-4296-a08c-d2fcadea71a2.png)
### 초반에 접근한 방법 → 이전에 만든 숫자에서 앞에 새로운 숫자(빨간색 숫자)를 붙이는 형식으로 생각했다.


*** 

![image](https://user-images.githubusercontent.com/81174840/224947598-8c64ce8f-81a6-40ac-9bb5-4982ffd39528.png)
### dp[i][j] : i라는 수를 j개의 숫자로 만들어서 그 수를 저장한다.
 
