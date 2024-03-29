# 두 큐 합 같게 만들기
두 큐의 각각의 원소들의 합이 동일하게 되도록 pop+insert 연산횟수를 계산하는 문제

## 해결방법
1. 각 큐가 늘고 줄어드는것을 배열로 구현.(시간이 더 소요되는 가변길이 큐 대신 넉넉한 크기의 배열 두개 선언)
2. 합이 큰 큐에서 pop해서 합이 작은 큐에 삽입. answer++
3. 큐 공간을 다 쓰거나 목표하는 값이 될 때까지 반복

## 에러노트
1. 두 큐의 총합에 나누기 2 한 값을 목표로 하고 푸니 아주 큰 수의 소수점때문인지 오류가 자꾸 발생 (-> sum1과 sum2를 비교하는것으로 해결)
2. 가능한가를 판단한 뒤에 다시 연산횟수를 계산하는 로직을 짜서 일부 테케에 시간초과 발생. (잘짠거같았는데..)


## 개선점
- 시간을 너무 많이 썼다. 복잡하게 구성되지 않고 깔끔하게 코드를 짜는지를 보는 문제인데 자꾸 수학적으로 접근하려고해서 오히려 코드가 복잡해짐

### 실행 결과
테스트 1 〉	통과 (0.03ms, 72.2MB) <br>
테스트 2 〉	통과 (0.01ms, 73.5MB)<br>
테스트 3 〉	통과 (0.02ms, 75.2MB)<br>
테스트 4 〉	통과 (0.03ms, 73.9MB)<br>
테스트 5 〉	통과 (0.03ms, 75.8MB)<br>
테스트 6 〉	통과 (0.03ms, 71.6MB)<br>
테스트 7 〉	통과 (0.04ms, 77MB)<br>
테스트 8 〉	통과 (0.05ms, 73.3MB)<br>
테스트 9 〉	통과 (0.14ms, 73.4MB)<br>
테스트 10 〉	통과 (0.25ms, 77.6MB)<br>
테스트 11 〉	통과 (5.31ms, 85.7MB)<br>
테스트 12 〉	통과 (3.20ms, 77.1MB)<br>
테스트 13 〉	통과 (3.83ms, 85.2MB)<br>
테스트 14 〉	통과 (4.30ms, 95.8MB)<br>
테스트 15 〉	통과 (4.10ms, 97.7MB)<br>
테스트 16 〉	통과 (4.03ms, 94.6MB)<br>
테스트 17 〉	통과 (5.04ms, 98.9MB)<br>
테스트 18 〉	통과 (9.39ms, 147MB)<br>
테스트 19 〉	통과 (13.98ms, 138MB)<br>
테스트 20 〉	통과 (10.18ms, 130MB)<br>
테스트 21 〉	통과 (9.93ms, 142MB)<br>
테스트 22 〉	통과 (20.64ms, 143MB)<br>
테스트 23 〉	통과 (21.20ms, 128MB)<br>
테스트 24 〉	통과 (11.61ms, 149MB)<br>
테스트 25 〉	통과 (0.06ms, 74.3MB)<br>
테스트 26 〉	통과 (0.04ms, 85.9MB)<br>
테스트 27 〉	통과 (0.04ms, 79.4MB)<br>
테스트 28 〉	통과 (13.74ms, 101MB)<br>
테스트 29 〉	통과 (0.64ms, 79.9MB)<br>
테스트 30 〉	통과 (5.62ms, 105MB)<br>
