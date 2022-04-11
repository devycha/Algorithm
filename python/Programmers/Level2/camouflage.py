"""
문제링크 : https://programmers.co.kr/learn/courses/30/lessons/42578?language=python3

입력예시
[["yellowhat", "headgear"], ["bluesunglasses", "eyewear"], ["green_turban", "headgear"]]

결과예시
5
"""

def solution(clothes):
    clothes_set = {}
    answer = 1
    
    for clothe in clothes:
        key = clothe[1]
        value = clothe[0]
        
        if key in clothes_set:
            clothes_set[key].append(value)
        else:
            clothes_set[key] = [value]
        
    for key in clothes_set.keys():
        answer *= (len(clothes_set[key]) + 1)
    
    return answer - 1