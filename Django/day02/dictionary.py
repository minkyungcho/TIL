import random

student_phoneNumber = {
    "김민지" : "010-1234-1234"
}
lunch_menu = {
    "20층 식당" : {
        "A코스" : "돈까스",
        "B코스" : "냉면"
    },
    "양자강" : {
        "점심메뉴" : "탕짜면",
        "특선메뉴" : "군만두"
    },
    "대동집" : {
        "술안주" : "비빔면",
        "밥안주" : "오돌뼈"
    }
}
# print(student_phoneNumber["김민지"])
# print(student_phoneNumber.get("김민지"))

#print(lunch_menu["20층 식당"]["B코스"])
#print(lunch_menu.get("20층 식당").get("A코스"))

# dictionary에 data 추가하기
# lunch_menu에 새로운 메뉴 추가하기
lunch_menu["경성불백"] = {
    "한식메뉴" : "석쇠불고기",
    "특식메뉴" : "제육볶음"
}
# print(lunch_menu)
# 같은 key값에 data 추가하기
# 양자강에 메뉴 추가하기
lunch_menu["양자강"] = "짜장면"
# print(lunch_menu)

'''
# 모든 key 값, values 값 뽑기
print(lunch_menu.keys())
print(lunch_menu.values())
# key와 values 동시에 뽑기
print(lunch_menu.items())
'''

# for문
# b는 순회를 하려는 대상(반복대상), a는 순회할때마다 정보를 담아두는 임시변수
# for a in b
'''
for key in lunch_menu.keys(): # scope 생성
    print(key)
for value in lunch_menu.values():
    print(value)
for key, value in lunch_menu.items():
    print(key)
    print(value)
'''

# key 중에서 랜덤으로 하나 뽑기
print(random.choice(list(lunch_menu.keys())))
# key 중에서 랜덤으로 두개 뽑기
print(random.sample(list(lunch_menu.keys()),2))

# 조건문
'''
if 조건1:
    # 조건에 괄호는 필요 없다!
    조건1의 실행문
elif 조건2:
    조건2의 실행문
else:
    나머지 실행문
'''

# 조건문과 반복문을 동시에..?
