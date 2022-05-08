"""
문제링크 : https://programmers.co.kr/learn/courses/30/lessons/81301

입력예시
[s]
"one4seveneight"
"23four5six7"
"2three45sixseven"
"123"

결과예시
[result]
1478
234567
234567
123
"""

def solution(s):
    answer = 0
    number = ["zero", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine"]
    
    for index, num in enumerate(number):
        s = s.replace(num, str(index))
    
    answer = int(s)
    
    return answer