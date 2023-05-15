import java.util.*;

//STEP 1) 1개 ~  만큼의 열을 조합하여 모든 경우의 수에 대해 임시 키 구성 (조합)
//STEP 2) 일시적으로 구성해본 키가 최소성을 만족하는지 검사
//STEP 3) 최소성이 만족되면 유일성을 만족하는지 검사

class Solution {
    static List<String> keyList;
    static int MAXCOL, MAXROW;
  
    public int solution(String[][] relation) {
    //전역변수 초기화
		MAXROW = relation.length;
		MAXCOL = relation[0].length;
    keyList = new ArrayList<>(); //후보키가 들어갈 리스트

		int unusedCol = MAXCOL;
		int[] compIndex;
        //STEP 1) 1개 ~ COLMAX개 만큼의 열을 조합하여 후보키인지 판단할 키를 구성 (조합)
            // 0, 1, 2, 3, 01, 02, 03, 12, ... , 0123
		for (int compNum = 1; compNum <= unusedCol && compNum <= MAXCOL; compNum++) {
            compIndex = new int[compNum];
            comb(0, compIndex, 0, relation);
		}
		return keyList.size();
    }

    //조합을 재귀적으로 구성하는 반복문 (STEP1)
    private static void comb(int col, int[] compIndex, int cnt, String[][] relation){
		if (cnt == compIndex.length) { //조합구성이 완료된 경우
            StringBuilder tmp = new StringBuilder();
            for(int i=0;i<compIndex.length;i++) tmp.append(" "+compIndex[i]);
            for(int i=0;i<keyList.size();i++){
                //STEP 2) 일시적으로 구성해본 키가 최소성을 만족하는지 검사
                if(!checkMinimality(tmp.toString(),keyList.get(i))){ //일시적으로 구성한 키가 최소성 만족 x
                    return;
                } 
            }
            //STEP 3) 최소성이 만족되면 유일성을 만족하는지 검사
			findCandidateKey(tmp.toString(), compIndex, relation);
			return;
		}

        //조합 구성 재귀
        for (int i = col; i < MAXCOL; i++) {
			compIndex[cnt] = i;
			comb(i + 1, compIndex, cnt + 1, relation);
		}
    }
    
    //최소성을 만족하는지 검사 (STEP2)  
    private static boolean checkMinimality(String tmp, String key){
        String[] arrTmp = tmp.trim().split(" ");
        String[] arrKey = key.trim().split(" ");
        int count=arrKey.length;
        //Key의 모든 원소들이 Tmp에 포함된다면 최소성을 만족시키지 못함 
        loop:
        for(int i=0;i<arrKey.length;i++){
            for(int j=0;j<arrTmp.length;j++){
                if(arrKey[i].equals(arrTmp[j])) {
                    count--;
                    continue loop;
                }
            }
        }
        if(count==0) return false; //만족 X
        else return true; //만족 O
    }
    
    //유일성을 만족하는지 검사 (STEP3)
    private static void findCandidateKey(String key, int[] compIndex, String[][] relation) {
        List<String> tupleList = new ArrayList<>();
        StringBuilder tmp;
        //임시로 구성한 키의 인덱스(compIndex)를 기반으로 투플을 구성하여 tupleList에 삽입.
        //중복되는 투플은 넣지 않고, 반복문이 끝났을 때 투플의 수가 행의 수와 같다면 유일성 만족.
        for(int row=0;row<MAXROW;row++){
            tmp = new StringBuilder();

            //투플 구성
            for(int i=0;i<compIndex.length;i++){
                tmp.append(" "+relation[row][compIndex[i]]);
            }

            //중복이 없으면 tupleList에 삽입
            if(tupleList.contains(tmp.toString())){
                break;
            } else{
                tupleList.add(tmp.toString());
            }
        }

        //유일성 만족하면 keyList에 넣음
        if(tupleList.size()==MAXROW){ //유일성 만족
            keyList.add(key);           
        }
    }
}
