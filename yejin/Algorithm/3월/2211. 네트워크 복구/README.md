![image](https://user-images.githubusercontent.com/81174840/224471703-f8f37b43-7e01-40c3-aed3-c74684ed53ba.png)
![image](https://user-images.githubusercontent.com/81174840/224471708-aa0aca80-efa5-452a-bd02-6befb61c69dc.png)

### → tempList[] 배열은 해당 정점까지의 방문한 간선의 이름을 저장한다. Ex) tempList[3] = [3,6] 이라면 3번정점까지 최단 거리로 방문했을 때 3번, 6번 간선을 지나서 도착했다는 뜻이다.
### → 기존의 최단 거리에서 더 짧은 경로를 찾아서 최단 경로의 비용과 tempList[] 를 업데이트하는 경우에는 tempList[]는 새로운 리스트를 만들고 새로운 간선을 추가한다.
