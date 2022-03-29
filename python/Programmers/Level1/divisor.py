"""
문제링크 : https://programmers.co.kr/learn/courses/30/lessons/77884?language=python3

입력예시
left right
13   17
24   27

출력예시
43
52
"""

def solution(left, right):
    answer = 0
    
    for num in range(left, right+1):
        count = 0
        
        for i in range(1, num+1):
            if num % i == 0:
                count += 1
            
        if count % 2 == 0:
            answer += num
        else:
            answer -= num
                
    return answer