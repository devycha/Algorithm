/**
 * 문제링크
 * https://programmers.co.kr/learn/courses/30/lessons/42748?language=java
 */

class PrimeNumber {
    public int[] solution(int[] array, int[][] commands) {
        int[] answer = new int[commands.length];
        
        for (int i = 0; i < commands.length; i++) {
            int startNumber = commands[i][0];
            int endNumber = commands[i][1];
            int selectNumber = commands[i][2] - 1;
            
            int[] tempArray = new int[endNumber - startNumber + 1];
            int num = 0;
            for (int j = startNumber - 1; j < endNumber; j++) {
                tempArray[num++] = array[j];
            }
            
            for (int j = 0; j < tempArray.length; j++) {
                for (int k = 0; k < tempArray.length - (j+1); k++)
                if (tempArray[k] > tempArray[k+1]) {
                    int temp = tempArray[k+1];
                    tempArray[k+1] = tempArray[k];
                    tempArray[k] = temp;
                }
            }
            answer[i] = tempArray[selectNumber];
        }
        
        return answer;
    }
}