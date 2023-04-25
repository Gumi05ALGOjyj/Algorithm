

***

## [풀이]

### 이분탐색을 하는 경우에는 공유기를 설치하는 경우의 최소거리를 기준으로 체크한다.
### 상한을 기준으로 탐색하기 때문에 low = 1, high = number[N-1] - number[0] + 1 로 설정하고 이분 탐색을 진행하고 결과에 -1을 한 값을 반환한다.
![image](https://user-images.githubusercontent.com/81174840/229278847-a6da7dea-9ec7-494a-9131-01d569ec718f.png)
