"""
문제링크 : https://programmers.co.kr/learn/courses/30/lessons/42577

입력예시
["119", "97674223", "1195524421"]
["123","456","789"]
["12","123","1235","567","88"]

출력예시
false
true
false
"""

def solution(phone_book):
    
    hash_map = {}
    
    for num in phone_book:
        hash_map[num] = 1
        
    for phone_num in phone_book:
        temp = ""
        for num in phone_num:
            temp += num
            if temp in hash_map and temp != phone_num:
                return False
    
    return True