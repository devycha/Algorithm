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
        # 인쇄 요청 문서가 첫 번째에 있는 경우
        if location == 0:
            # 대기 문서 중 가장 크지 않은 경우
            if priorities[0] < max(priorities):
                # 가장 맨 뒤로 보냄
                priorities.append(priorities.pop(0))
                # 인쇄 요청 문서 현재 위치 재설정 
                location = len(priorities) - 1
            else:
                # 가장 큰 경우 출력된 순서 리턴
                return answer + 1
        # 인쇄 요청 문서가 첫 번째가 아닌 경우
        else:
            # 첫 번째 있는 문서 우선순위 낮을 경우
            if priorities[0] < max(priorities):
                # 대기 문서 제일 뒤로 보냄
                priorities.append(priorities.pop(0))
                # 인쇄 요청 문서 현재 위치 수정
                location -= 1
            # 첫 번째 있는 문서 우선순위가 가장 높을 경우
            else:
                # 첫 번째 문서 출력으로 제거
                priorities.pop(0)
                # 인쇄 요청 문서 현재 위치 수정
                location -= 1
                # 출력된 수 증가
                answer += 1