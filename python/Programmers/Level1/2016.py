"""
문제링크 : https://programmers.co.kr/learn/courses/30/lessons/12901

입력예시
5
24

출력예시
"TUE"
"""

def solution(a, b):
    answer = ''
    days = ["FRI","SAT","SUN","MON","TUE","WED","THU"]
    months = [31,29,31,30,31,30,31,31,30,31,30,31]
    
    dates = 0
    
    for i in range(a-1):
        dates += months[i]
    
    dates += b-1
    answer = day[dates%7]
    
    return answer