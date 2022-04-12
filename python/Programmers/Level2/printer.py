"""
문제링크: https://programmers.co.kr/learn/courses/30/lessons/42587?language=python3

입력예시
priorities
[2, 1, 3, 2]
[1, 1, 9, 1, 1, 1]

location
2
0

결과예시
1
5
"""

def solution(priorities, location):
    answer = 0
    
    while (len(priorities) != 0):
        if location == 0:
            if priorities[0] < max(priorities):
                priorities.append(priorities.pop(0))
                location = len(priorities) - 1
            else:
                return answer + 1
        else:
            if priorities[0] < max(priorities):
                priorities.append(priorities.pop(0))
                location -= 1
            else:
                priorities.pop(0)
                location -= 1
                answer += 1