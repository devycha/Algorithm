"""
문제링크 : https://programmers.co.kr/learn/courses/30/lessons/68644?language=python3

입력예시
[2,1,3,4,1]
[5,0,2,7]

출력예시
[2,3,4,5,6,7]
[2,5,7,9,12]
"""

def solution(numbers):
    temp = []
    
    for i in range(len(numbers)):
        for j in range(i+1,len(numbers)):            
            temp.append(numbers[i] + numbers[j])
            
    answer = list(set(temp))
    answer.sort()
    
    return answer