## 문제
⚾는 9명으로 이루어진 두 팀이 공격과 수비를 번갈아 하는 게임이다. 하나의 이닝은 공격과 수비로 이루어져 있고, 총 N이닝 동안 게임을 진행해야 한다. 한 이닝에 3아웃이 발생하면 이닝이 종료되고, 두 팀이 공격과 수비를 서로 바꾼다.
두 팀은 경기가 시작하기 전까지 타순(타자가 타석에 서는 순서)을 정해야 하고, 경기 중에는 타순을 변경할 수 없다. 
9번 타자까지 공을 쳤는데 3아웃이 발생하지 않은 상태면 이닝은 끝나지 않고, 1번 타자가 다시 타석에 선다. 
타순은 이닝이 변경되어도 순서를 유지해야 한다. 
예를 들어, 2이닝에 6번 타자가 마지막 타자였다면, 3이닝은 7번 타자부터 타석에 선다.
공격은 투수가 던진 공을 타석에 있는 타자가 치는 것이다. 
공격 팀의 선수가 1루, 2루, 3루를 거쳐서 홈에 도착하면 1점을 득점한다. 
타자가 홈에 도착하지 못하고 1루, 2루, 3루 중 하나에 머물러있을 수 있다. 루에 있는 선수를 주자라고 한다. 이닝이 시작될 때는 주자는 없다.
<공을 쳐서 얻을 수 있는 결과들>
안타: 타자와 모든 주자가 한 루씩 진루한다.
2루타: 타자와 모든 주자가 두 루씩 진루한다.
3루타: 타자와 모든 주자가 세 루씩 진루한다.
홈런: 타자와 모든 주자가 홈까지 진루한다.
아웃: 모든 주자는 진루하지 못하고, 공격 팀에 아웃이 하나 증가한다.
중요조건 : 1번 타자는 항상 4번째이다.

## 결과
메모리 : 61364KB 시간 : 488ms

## 풀이 방법
1. 타자의 순서를 정해준다 -> 여기서 순서가 중요하므로 순열로 구해주는데 주의해야할 점은 1번 타자가 항상 4번째에 있다는 것이다.
2. 게임을 시작해 준다 -> 주의해야할 점은 게임이 시작되면 타순을 바꿀 수 없다는 점이다.
3. 게임 진행의 특징은 한 루에는 1명만 있다 -> 각 루에 사람이 있고 없고를 true/false로 저장해 두면 따로 각 선수들의 위치를 저장해 두지 않아도 점수 계산이 가능하다.
