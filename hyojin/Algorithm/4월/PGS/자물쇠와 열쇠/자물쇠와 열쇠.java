class Solution {
    public boolean solution(int[][] key, int[][] lock) {

        		boolean answer = true;
		int m = key[0].length; // key 사이즈
		int n = lock[0].length; // lock 사이즈
		int[][] arr = new int[2 * m + n - 2][2 * m + n - 2]; // 키가 돌 수 있는 판

		// arr판에 lock모양 삽입해야됨
		// lock 삽입하기 전에 락이 아닌 곳은 -1로 해줘서 바꾸기
		for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j < arr.length; j++) {
				arr[i][j] = -1;
			}
		}

		// arr판에 lock모양 삽입하기
		int lx = 0; // 자물쇠의 x좌표
		int ly = 0; // 자물쇠의 y좌표
		for (int x = m - 1; x <= (2 * m + n - 2) - (m - 1); x++) {
			for (int y = m - 1; y <= (2 * m + n - 2) - (m - 1); y++) {
				if (ly == n)
					continue; // lock의 y는 3*3일때 [0][1][2]까지갔다가 다음줄의 [0]으로 내려가야되니까 continue
				if (lx == m)
					continue; // lock의 x는 3*3일때 세 줄까지 다 왔으면 멈추기
				arr[x][y] = lock[lx][ly];
				ly++; // 한칸 저장하고 다음 y칸에 저장하기
			}
			lx++;
			ly = 0;
		}


		
		boolean rotb = false;
		for (int rot = 0; rot <= 4; rot++) {
			for (int x = 0; x < arr.length - m + 1; x++) {
				for (int y = 0; y < arr.length - m + 1; y++) {
					int[][] newArr = copyarr(arr,m,n);
					rotb = check(x, y, newArr,key,m,n);
					if(rotb) {
						answer = true;
						return answer;
					}
				}
			}
			change(key);
		}
		System.out.println(answer);
		return answer;
	}

	private void change(int[][] key) {
		int m = key[0].length;
        int[][] copy = new int[m][m];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < m; j++) {
                copy[j][m - 1 - i] = key[i][j];
            }
        }
//        key = copy;
        for(int i = 0; i < m; i++) {
        	for(int j = 0 ; j < m; j++) {
        		key[i][j] = copy[i][j];
        	}
        }
		
	}
	
	private int[][] copyarr(int[][] arr,int m,int n) {
        int[][] copy = new int[2 * m + n - 2][2 * m + n - 2];
//        key = copy;
        for(int i = 0; i < 2 * m + n - 2; i++) {
        	for(int j = 0 ; j < 2 * m + n - 2; j++) {
        		copy[i][j] = arr[i][j];
        	}
        }
		return copy;
	}

	private boolean check(int startX, int startY, int[][] arr, int[][] key,int m,int n) {
		boolean result = true;
		for(int x = startX ; x < startX + m ; x++) {
			for(int y = startY ; y < startY + m ; y++) {
				if(arr[x][y]==0) {
					if(key[x-startX][y-startY]==1)
						arr[x][y] = 1;
				}
			}
		}
		
		for(int i = 0 ; i < arr.length ; i++) {
			for(int j = 0 ; j < arr[i].length ; j++) {
				if(arr[i][j]==0)
					return false;
			}
		}
		return result;

	}
        
        
        
    }
