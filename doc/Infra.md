## 📌 인프라 설계
![Infra Architecture](/image/Infra(20200817).jpg)

***

* 요청 받는 서버와 처리 하는 서버로 나누었습니다.

<br>

* 두 서버 간의 통신 방법으로 메시지브로커를 이용하였습니다.

<br>

* 메시지브로커는 nCloud에서 제공하는 Simple rabbitmq service를 사용하여 구성하였습니다.
