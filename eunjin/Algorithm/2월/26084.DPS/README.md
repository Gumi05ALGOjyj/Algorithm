## 문제 출처 : 26084. DPS(siver 2)
### 성능요약 : 메모리 = 20044KB, 시간 = 192ms
#### 문제
ICPC는 세 명이 한 팀을 이뤄 참가하는 국제 대학생 프로그래밍 대회이다. 
ICPC에 참가하는 각 팀의 이름은 세 팀원의 핸들 첫 글자를 임의의 순서로 이어 붙인 것이다. 
핸들이란 Baekjoon Online Judge와 같은 온라인 채점 사이트에서 사용하는 고유한 ID이다.
예를 들어 핸들이 각각 DONGGAS, PICASSO, SEMTEO인 세 명으로 이루어진 팀의 이름은 DPS, DSP, PDS, PSD, SDP, SPD 중 하나이다.
또, 핸들이 각각 RAARARAARA, WBCHO, WEASEL인 세 명으로 이루어진 팀의 이름은 RWW, WRW, WWR 중 하나이다.
팀 이름 S와 N명의 핸들이 주어지면, N명으로 팀 S를 구성하는 모든 경우의 수를 구해보자.
#### 입력
첫째 줄에 팀 이름 S가 주어진다. 팀 이름 S는 영어 대문자 3개로 이루어져 있다.
둘째 줄에 사람의 수를 나타내는 양의 정수 N(3<=N<=50000)이 주어진다.
셋째 줄부터 N명의 핸들이 한 줄에 하나씩 주어진다. 각 핸들의 길이는 1이상 10이하이다.
모든 사람의 핸들은 영어 대문자로만 이루어진 공백이 없는 문자열이다. 또, 모든 사람의 핸들은 서로 다르다.
#### 문제 접근 방법
처음에는
```java
public static void func(int cnt) {
		if (cnt == 3) {
			totalCnt++;
			return;

		}

		for (int i = 0; i < N; i++) {
			if (isVisited[i])
				continue;
			isVisited[i] = true;
			func(cnt + 1);
			isVisited[i] = false;
		}
	} 
  ```
  으로 하나하나 다 만든 후 팀 이름에서 중복된 알파벳의 수를 계산해 전체 결과에서 나누어주었더니 시간초과가 났다..!
  결과 집합이 필요 없으므로 
  ```java
  private static long func(int input, int team) {
		if(team==1) return input;
		else if(team==2) {
			return (input*(input-1L))/2L;
		}else {
			return (input*(input-1L)*(input-2))/6L;
		}
	}
  ```
  의 방법으로 횟수만 계산해주면 끝나는 문제였다...........
