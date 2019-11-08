from bs4 import BeautifulSoup
import requests

url = "http://www.saramin.co.kr/zf_user/jobs/list/job-category?cat_cd=404&panel_type=&search_optional_item=n&search_done=y&panel_count=y"
url2 = "http://www.saramin.co.kr/zf_user/jobs/list/job-category?cat_cd=407&panel_type=&search_optional_item=n&search_done=y&panel_count=y"
url3 = "http://www.saramin.co.kr/zf_user/jobs/list/job-category?cat_key=40701&panel_type=&search_optional_item=n&search_done=y&panel_count=y"
response = requests.get(url)

# print(response.text)

html = BeautifulSoup(response.text, 'html.parser') # 
company_names = html.select('.company_name') # class가 company_name인 모든 친구들
recruit_names = html.select('.recruit_name')
recruit_conditions = html.select('.list_recruit_condition')
'''
for company_name, recruit_name, recruit_condition in zip(company_names, recruit_names, recruit_conditions):
    print(f'{company_name.text} - {recruit_name.text} - {recruit_condition.text}')

# print(company_names)
# print(type(company_names))
for company_name in company_names:
    #print(company_name)
    print(company_name.text)

for recruit_name in recruit_names:
    print(recruit_name.text)
'''
# 길이가 같은지 확인해본다
# print(len(company_names))
# print(len(recruit_names))
# print(len(recruit_conditions))

# 더 큰 조건으로 작은 조건들을 뽑아내보자
'''
company = html.select('.part_top')
for com in company:
    # print(type(com)) # <class 'bs4.element.Tag'>
    print(f'{com.select_one(".company_name").text}- {com.select_one(".recruit_name").text}')
    print(com.se√ßslect_one(".list_recruit_condition").text)
    break
'''

company_list = html.select('ul.product_list li')

for com in company_list:
    # print(type(com))  # <class 'bs4.element.Tag'>
    idx = com.select_one('a')['href'].split('=')[-1]
    company_info_url = "http://www.saramin.co.kr/zf_user/jobs/relay/view-ajax"
    company_info_params = { 'rec_idx' : idx }
    company_response = requests.post(company_info_url, params=company_info_params) # url과 params라는 매개변수
    print(company_response)
    company_html = BeautifulSoup(company_response.text, 'html.parser')
    company_title = company_html.select_one('a.company').text
    print(company_title.strip()) # strip() 을 활용하여 공백 없애기
    break
