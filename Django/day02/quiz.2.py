
import requests
import json

# 1. 평균을 구하시오.
scores = {
    "수학" : 90,
    "영어" : 88,
    "화학" : 92
}
# 배열의 길이를 구하는 함수는 len(배열)
# 반올림 하는 함수는 round(숫자, 소숫점)
for score in scores.values():
    score += score 
avg_score = round(score/len(scores), 1)

print("평균 : ", avg_score)

# 2. 각 학생의 평균 점수와 반 평균을 구하세요
student_scores = {
    "a학생" : {
        "수학" : 95,
        "국어" : 90,
        "생물" : 100
    },
    "b학생" : {
        "수학" : 90,
        "영어" : 90,
        "화학" : 60
    }
}

# 학생의 평균 점수
sums = []
for stu in student_scores.values():
    #print(stu)
    scores = []
    for stu_score in stu.values():
        #print(stu_score)
        scores.append(stu_score)
    #print(scores)
    print("학생의 평균 점수 : ", round(sum(scores)/len(stu),1))
    sums.append(sum(scores))
# 반 평균 점수
print("반 평균 점수 : ", round(sum(sums)/len(sums)/3,1))



