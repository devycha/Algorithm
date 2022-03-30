"""
문제링크 : https://programmers.co.kr/learn/courses/30/lessons/68935?language=python3

입력예시
45
125

출력예시
7
229
"""

import math

def solution(n):
    answer = 0
    
    result = []
    
    while n > 0:
        result.append(int(n%3))
        n //= 3
    
    for i,num in enumerate(result):
        answer += num * math.pow(3, len(result)-(i+1))
    
    return int(answer)