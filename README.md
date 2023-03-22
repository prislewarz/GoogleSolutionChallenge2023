## StoPhone

# What is stoPhone?

It is unimaginable to separate modern people from smartphones, and we spend so much time on smartphones.

In Korea, 24.2% of smartphone users are at risk of overdependence (high risk group + potential risk group), and many people are already addicted to smartphones, and the number is increasing year by year. This is not just a problem for South Korea. It's a global problem.

The bigger problem is that addicting to smartphones doesn't end with simple addiction, anomalies occur in various parts of the body, such as eyes, neck, wrists, shoulders, and even traffic accidents occur while using smartphones while walking.

To prevent physical syndrome caused by smartphones and solve the problem of using smartphones while walking, we created an app called stoPhone.

### 기능

### 1. 섭취 음식 추가 방법

1. 수동으로 직접 등록 (DB로 구현)
2. Google Vision AI API를 통해 영수증 인식 (이미지에서 텍스트를 인식하는 기능이 있음)

### 2. **부족한 영양소 관리**

보유하고 있는 식재료에 어떤 영양소가 포함되어 있는지 체크하여 

### 3. 하루 or 일주일 단위로 식생활평가지수 제공

![img](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/2e79c3b1-abf2-4422-b00d-0e838756b6e8/%E1%84%89%E1%85%B3%E1%84%8F%E1%85%B3%E1%84%85%E1%85%B5%E1%86%AB%E1%84%89%E1%85%A3%E1%86%BA_2023-01-11_%E1%84%8B%E1%85%A9%E1%84%8C%E1%85%A5%E1%86%AB_12.08.54.png)

## 4. 식생활평가지수 비교

1. 전체 사용자 평균 점수와 나의 점수를 비교
2. 내 점수가 전체 사용자 중 상위 몇%인지 확인

## UI

![Untitled](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/67985262-0109-422e-bfc4-f31c73b72406/Untitled.png)

달력 - Grid Layout

pie chart - MpAndroidChart

fragment로 달력, 보고서,그래프 탭 구현

식재료를 구매할 수 있는 다양한 사이트 연결

- 웹 : 이마트,
- 앱 : 마켓컬리, 배민, …
