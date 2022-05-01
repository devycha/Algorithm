import re

"""
문제링크 : https://programmers.co.kr/learn/courses/30/lessons/72410

입력예시
"...!@BaT#*..y.abcdefghijklm"
"z-+.^."
"=.="
"123_.def"
"abcdefghijklmn.p"

출력예시
"bat.y.abcdefghi"
"z--"
"aaa"
"123_.def"
"abcdefghijklmn"
"""

def solution(new_id):

    # 소문자로 치환, 허용된 문제를 제외한 다른 문자들 삭제
    change_id = re.sub("[^-._a-z0-9]","",new_id.lower())

    # .. -> . 마침표 줄이기
    while ".." in change_id:
        change_id = change_id.replace("..", ".")

    # 양쪽 끝 마침표 제거
    change_id = change_id.strip(".")

    # 빈 문자열이면 a를 대입
    if change_id == "":
        change_id = "a"

    # 16자 이상이면 앞에 15자를 제외한 나머지 문자 제거, 15자로 줄인 후 오른쪽 맨 끝에 마침표가 있다면 삭제
    if len(change_id) > 15:
        change_id = change_id[:15].rstrip(".")

    # 길이가 2 이하라면 길이가 3이 될 때까지 마지막 문자를 추가한다    
    while len(change_id) < 3:
        change_id += change_id[len(change_id) - 1]

    return change_id