# 공유기 설치
- X축에 N개의 센서들이 위치할 때, K개의 집중국을 설치해야 한다.
- 이 때 집중국의 수신범위에 포함되지 않는 센서가 없도록 집중국의 설치위치와 센서범위를 정해야 한다.

## 해결 방법 고민
### 1. 거리 등분(정답 도출 안됨)
 - 방법 : 1번째 센서와 N번째 센서의 거리를 K등분하고, 구간에 속하는 센서들의 중간값에 집중국 배치
 
### 2. 센서간 거리 차이 이용
 - 방법 : 인접한 센서들의 거리차이를 배열화하고, K가 늘어날수록 큰 거리차이를 버림.
 - ex) 센서 위치(정렬) = {1,3,6,7,9} <br/> 거리차 배열 = {2,3,1,2} -> {1,2,2,3} <br/> K=1일 경우 ans=1+2+2+3=8<br/>K=2 ans=1+2+2=5<br/>K=3 ans=1+2=3<br/>K=4 ans=1

## 결과
- 메모리 : 21072KB
- 시간 : 316ms
