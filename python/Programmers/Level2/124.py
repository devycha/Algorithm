"""
문제링크 : https://programmers.co.kr/learn/courses/30/lessons/12899

입력예시
1	
2	
3	
4
5
6
7

결과예시
1
2
4
11
12
14
21
"""

def solution(n):
    answer = ''
    
    while n > 0:
        if n % 3 == 0:
            answer = "4" + answer
            n -= 1
        elif n % 3 == 1:
            answer = "1" + answer
        else:
            answer = "2" + answer
        n //= 3
    
    return answer