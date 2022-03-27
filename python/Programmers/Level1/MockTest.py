"""
 문제 링크 : https://programmers.co.kr/learn/courses/30/lessons/42840?language=python3
 
 입력값 예시
 [1,2,3,4,5]
 [1,3,2,4,2]

 출력값 예시
 [1]
 [1,2,3]

"""
def solution(answers):
    answer = []
    
    # 1. 3명의 수포자 학생들의 맞은 문제 개수 파악
    student1 = [1,2,3,4,5]
    student2 = [2,1,2,3,2,4,2,5]
    student3 = [3,3,1,1,2,2,4,4,5,5]
    
    collect1 = 0
    collect2 = 0
    collect3 = 0
    
    for i in range(len(answers)):
        if student1[i%5] == answers[i]:
            collect1 += 1
    
    for i in range(len(answers)):
        if student2[i%8] == answers[i]:
            collect2 += 1
            
    for i in range(len(answers)):
        if student3[i%10] == answers[i]:
            collect3 += 1
            
    # 2. 가장 높은 점수를 찾음
    collectMax = max(collect1, collect2, collect3)
    
    # 3. 가장 높은 점수와 각 학생들의 점수를 비교하여 같으면 리스트에 담음
    if collectMax == collect1:
        answer.append(1)
    if collectMax == collect2:
        answer.append(2)
    if collectMax == collect3:
        answer.append(3)
        
    return answer