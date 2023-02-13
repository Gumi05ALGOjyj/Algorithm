# [Silver 2] DPS

[Link](https://www.acmicpc.net/problem/14225)

## 문제

ICPC는 세 명이 한 팀을 이뤄 참가하는 국제 대학생 프로그래밍 대회이다. ICPC에 참가하는 각 팀의 이름은 세 팀원의 핸들 첫 글자를 임의의 순서로 이어 붙인 것이다. 핸들이란 Baekjoon Online Judge와 같은 온라인 채점 사이트에서 사용하는 고유한 ID이다.

예를 들어 핸들이 각각 DONGGAS, PICASSO, SEMTEO인 세 명으로 이루어진 팀의 이름은 DPS, DSP, PDS, PSD, SDP, SPD 중 하나이다. 또, 핸들이 각각 RAARARAARA, WBCHO, WEASEL인 세 명으로 이루어진 팀의 이름은 RWW, WRW, WWR 중 하나이다.

팀 이름 S와 N명의 핸들이 주어지면, N명으로 팀 S를 구성하는 모든 경우의 수를 구해보자.

## 입력

첫째 줄에 팀 이름 S가 주어진다. 팀 이름 S는 영어 대문자 3개로 이루어져 있다.

둘째 줄에 사람의 수를 나타내는 양의 정수 N(3≤N≤50000)이 주어진다.

셋째 줄부터 N명의 핸들이 한 줄에 하나씩 주어진다. 각 핸들의 길이는 1이상 10이하이다.

모든 사람의 핸들은 영어 대문자로만 이루어진 공백이 없는 문자열이다. 또, 모든 사람의 핸들은 서로 다르다.

## 출력

첫째 줄에 팀 S의 구성으로 가능한 모든 경우의 수를 출력한다.

출력이 32비트 정수형 타입의 표현 범위를 초과할 수 있으므로 언어에 따라 아래와 같은 적절한 64비트 정수형 타입을 이용하여 출력해야 한다.

- C/C++: long long
- JAVA: long
- Kotlin: Long

## 예제 입력 1

```
DPS
7
DONGGAS
PICASSO
SEMTEO
DJS
WBCHO
RAARARAARA
WEASEL

```

## 예제 출력 1

```
2
```

7명으로 팀 DPS를 구성하는 경우는 (DONGGAS, PICASSO, SEMTEO)와 (DJS, PICASSO, SEMTEO) 두 가지뿐이다.





# 문제풀이

1. n명의 이름을 받을때 첫글자를 배열에 저장
    1. 이름이 WBCHO일때 alpha[87]에 +1
2. 팀 이름은 무조건 대문자 3개
    1. 세글자가 모두 같은 경우 : AAA → A의 알파벳개수C3
    2. 두글자만 같은 경우 : AAB → A의 알파벳개수 C 2 * B의 알파벳개수
    3. 모두 다 다른 경우 : ABC → A의 알파벳개수 * B의 알파벳개수 * C의 알파벳개수
