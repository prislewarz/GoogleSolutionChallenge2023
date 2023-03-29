## What is stoPhone?

It is unimaginable to separate modern people from smartphones, and we spend so much time on smartphones.

In Korea, 24.2% of smartphone users are at risk of addiction (high risk group + potential risk group), and many people are already addicted to smartphones, and the number is increasing every year. This is not just a South Korean problem. It's a global problem.

The bigger problems are that smartphone addiction doesn't end with simple addiction, but abnormalities occur in various parts of the body, such as eyes, neck, wrists, shoulders. In addition, traffic accidents caused by using smartphones while walking, called smombie, are also a big problem.

In order to prevent the physical syndrome caused by smartphones and to solve the problems caused by smombie, we created an app called stoPhone.

## Feature

Users can sign in with their email and password, and easily sign up and sign in with a Google Account.

The main screen shows how many times users did eye and neck exercises this week and how many times they used their smartphone while walking.

The Eyes Exercises setup screen explains the effects of prolonged smartphone use on the eyes and why Eyes Exercises should be performed. Users can set a warning phrase (message) that directly prompts the user to perform eye exercises and set how many hours&minutes the user would like to receive a notification. In addition, the user can start the eye exercise by clicking the Eye Exercise Start button Even if it's not a set time

The Neck Exercises setup screen explains the effects of prolonged smartphone use on the neck and why neck exercises should be performed. Users can set a warning phrase (message) that directly prompts the user to perform neck exercises and set the specific time at which the user would like to receive a notification. Users can also check the box to send notifications every day at the specified time. In addition, the user can start the eye exercise by clicking the Eye Exercise Start button Even if it's not a set time.

The Smombie setup screen explains the effects of using a smartphone while walking and why you shouldn't use a smartphone while walking. Users can set a warning phrase (message) that directly guides the user when to use smartphone while walking, and set the number of steps to send a warning notification.







### 1. 

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
