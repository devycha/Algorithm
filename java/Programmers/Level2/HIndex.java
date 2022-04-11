/**
 * 문제링크 : https://programmers.co.kr/learn/courses/30/lessons/42747?language=java
 * 
 * 입력예시
 * [3, 0, 6, 1, 5]
 * 
 * 결과예시
 * 3
 */


import java.util.Arrays;

class HIndex {
    public int solution(int[] citations) {

        Arrays.sort(citations);

        int arrayLength = citations.length;
        for (int i = 0; i < arrayLength; i++) {
            if (citations[arrayLength - (i+1)] <= i) {
                return i;
            }
        }

        return arrayLength;
    }
}