import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

class Solution {
    static int time = 0;

    public int solution(int cacheSize, String[] cities) {

        for (int i = 0; i < cities.length; i++) { // 일단 다 대문자로 변경, 대소문자 구분을 안하니까
            cities[i] = cities[i].toUpperCase();
        }

        if (cacheSize == 0) { // 캐시크기가 0일때는 cities개수 * 5
            return cities.length * 5;
        }
        int answer = 0;
        LinkedList<String> arr = new LinkedList<>();


        for (int i = 0; i < cities.length; i++) {
            if (arr.contains(cities[i])) {
                answer += 1;
                arr.remove(cities[i]);
                arr.add(cities[i]);
            } else {
                if (arr.size() >= cacheSize) {
                    arr.remove(0);
                }
                arr.add(cities[i]);

                answer += 5;
            }
        }

        return answer;
    }
}
