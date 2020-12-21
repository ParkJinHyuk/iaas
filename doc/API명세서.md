## 📌 API 명세서

--------------------------------------------

### Test URL : http://27.96.131.225:8080/swagger-ui.html

### Server API URL : http://27.96.131.225:8080

<br>

--------------------------------------------

* 클러스터 조회

```
GET /api/v1/clusters
```

<br>

#### 요청 파라미터
x

<br>

#### 응답 예시

```
[
    {
        "clusterId": 3,
        "nodeCnt": 2,
        "policy": "all",
        "nodesList": [
            {
                "id": 17,
                "serverName": "ccc002",
                "serverInstanceNo": "4996500",
                "privateIp": "10.41.164.58",
                "status": "STABLE"
            },
            {
                "id": 36,
                "serverName": "ccc003",
                "serverInstanceNo": "4996784",
                "privateIp": "10.41.169.24",
                "status": "STABLE"
            }
        ],
        "loadBalancer": {
            "id": 3,
            "loadBalancerInstanceNo": "4996584",
            "loadBalancerName": "ccc"
        }
    }
]
```

<br>

--------------------------------------------

* 클러스터 생성

```
POST /api/v1/clusters
```

<br>

#### 요청 파라미터

##### body

|**파라미터명**|**필수 여부**|**타입**|**제약 사항**|**설명**|
|---|---|---|---|---|
|clusterName|Yes|String|Min:1, Max:27||
|loadBalancerName|Yes|String|Min:3, Max:30||
|serverCreateCount|Yes|Integer|Min:2||
|policy|No|String||Default : all <br> Options : all, exactly |

<br>

#### 응답 예시

```
"Success Create Cluster"
```

<br>

--------------------------------------------

* 클러스터 정책 변경 

```
PUT /api/v1/clusters/{id}
```

<br>

#### 요청 파라미터

##### path

|**파라미터명**|**필수 여부**|**타입**|**제약 사항**|**설명**|
|---|---|---|---|---|
|id|Yes|Long|||

<br>

#### body

|**파라미터명**|**필수 여부**|**타입**|**제약 사항**|**설명**|
|---|---|---|---|---|
|policy|Yes|String||Options : all, exactly |

<br>

#### 응답 예시

```
"Success Update Cluster"
```

<br>

--------------------------------------------

* 클러스터 삭제 

```
DELETE /api/v1/clusters/{id}
```

<br>

#### 요청 파라미터

##### path

|**파라미터명**|**필수 여부**|**타입**|**제약 사항**|**설명**|
|---|---|---|---|---|
|id|Yes|Long|||

<br>

#### 응답 예시

```
"Success Delete Cluster"
```

<br>

--------------------------------------------

* 노드 생성(Scale-out) 

```
POST /api/v1/clusters/{id}
```

<br>

#### 요청 파라미터

##### path

|**파라미터명**|**필수 여부**|**타입**|**제약 사항**|**설명**|
|---|---|---|---|---|
|id|Yes|Long|||

<br>

#### 응답 예시

```
"Success Create Node"
```

<br>

--------------------------------------------

* 노드 삭제(Scale-in) 

```
DELETE /api/v1/nodes/{id}
```

<br>

#### 요청 파라미터

##### path

|**파라미터명**|**필수 여부**|**타입**|**제약 사항**|**설명**|
|---|---|---|---|---|
|id|Yes|Long|||

<br>

#### 응답 예시
```
"Success Delete Node"
```
