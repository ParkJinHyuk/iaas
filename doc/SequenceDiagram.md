## 📌 시퀀스 다이어그램

***

### [Cluster 생성 요청]
![Sequence Diagram](/image/SequenceDiagram(CreateCluster+Infra).png)

>< API Server >

1. 사용자가 API Server로 Create Cluster 요청을 보냅니다.
2. 요청 정보를 데이터베이스에 저장합니다.
3. Rabbitmq Server로 메시지를 보냅니다.
4. 사용자에게 응답합니다.

<br>

>< Analytics Server >

5. 큐에 있는 메세지를 읽습니다.
6. nCloud API 요청을 통해 서버를 만듭니다.
7. 서버가 켜지는 것을 확인하기 위해 스케쥴러에 전달합니다.
8. 스케쥴러가 서버가 켜진 것을 확인하면 쓰레드풀의 쓰레드를 이용해 작업을 시킵니다.
9. 쓰레드는 원격 명령어 실행으로 서버들을 클러스터링 합니다.
10. 쓰레드는 로드밸런서를 생성하여 서버들을 바인딩합니다.


<br>

***

### [Node 장애 처리]
![Sequence Diagram(장애처리)](/image/SequenceDiagram(StatusCheck).png)

>< Analytics Server >

1. 스케쥴러가 노드의 장애를 감지합니다.
2. 장애 노드를 삭제하라는 API 요청을 API Server로 요청합니다.
3. 해당 클러스터에 노드를 추가하라는 요청을 API Server로 요청합니다.

<br>

>< API Server >

4. API 요청에 맞게 처리합니다.
