"""
문제링크 : https://programmers.co.kr/learn/courses/30/lessons/92334

입력예시
id_list	
["muzi", "frodo", "apeach", "neo"]
["con", "ryan"]	

report
["muzi frodo","apeach frodo","frodo neo","muzi neo","apeach muzi"]
["ryan con", "ryan con", "ryan con", "ryan con"]

k
2
3

결과예시
result
[2,1,1,0]
[0,0]
"""

def solution(id_list, reports, k):
    answer = []
    report_dic = {}
    report_count_dic = {}
    report_over_list = []

    for id in id_list:
        report_count_dic[id] = 0

    for report in reports:
        report_split = report.split(" ")

        if report_split[0] in report_dic.keys():
            if report_split[1] not in report_dic[report_split[0]]:
                report_dic[report_split[0]].append(report_split[1])
                report_count_dic[report_split[1]] += 1   
        else:
            report_dic[report_split[0]] = [report_split[1]]
            report_count_dic[report_split[1]] += 1

    for report in report_count_dic.keys():
        if report_count_dic[report] >= k:
            report_over_list.append(report)

    for index, id in enumerate(id_list):
        count = 0
        if id in report_dic.keys():
            for report in report_over_list:
                if report in report_dic[id]:
                    count += 1
            answer.append(count)
        else:
            answer.append(0)

    return answer

# 다른사람 풀이를 참고해 코드를 간결하게 변경
def solution2(id_list, reports, k):
    answer = [0] * len(id_list)

    """
    for id in id_list:
        report_count_dic[id] = 0
    """
    #람다를 이용해 한줄로 요약
    reports_count_dic = {x : 0 for x in id_list} 

    """
    for report in reports:
        report_split = report.split(" ")

        if report_split[0] in report_dic.keys():
            if report_split[1] not in report_dic[report_split[0]]:
                report_dic[report_split[0]].append(report_split[1])
                report_count_dic[report_split[1]] += 1   
        else:
            report_dic[report_split[0]] = [report_split[1]]
            report_count_dic[report_split[1]] += 1
    """
    #report_dic 딕셔너리를 만들어 유저ID와 유저가 신고한 ID를 정리해서
    #가지고 있었지만 다른 사람의 풀이를 보니까 꼭 가지고 있을 필요는 없다고 생각함
    for report in reports:
        reports_count_dic[report.split(" ")[1]] += 1 

    """
    for report in report_count_dic.keys():
        if report_count_dic[report] >= k:
            report_over_list.append(report)

    for index, id in enumerate(id_list):
        count = 0
        if id in report_dic.keys():
            for report in report_over_list:
                if report in report_dic[id]:
                    count += 1
            answer.append(count)
        else:
            answer.append(0)
    """
    #신고 초과된 ID를 찾은 뒤
    #reports 리스트에서 초과된 ID를 신고한 유저 ID를 찾아
    #해당 유저가 id_list에 있는 순서에 맞춰 1을 더해줌
    for report in reports:
        if reports_count_dic[report.split(" ")[1]] >= k:
            answer[id_list.index(report.split(" ")[0])] += 1