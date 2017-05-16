## 랭킹

### 랭킹 순위
- URL : http://13.124.64.211:11022/rank
- Method : `POST`
- request

| 필드명  | 값 | 설명         |
| :------------ | :-----------: | -------------------: |
| order    | `popularity`, `re_purchase`|              |
| age    | `all`, `10`,`20`,`30`,`40+`      |          |
| rank_term| `all`,`3`,`6`  ||
| category| `스킨케어`  ||
- response

```
{
	"item": [
		{
			"name": "위치헤이즐 허벌 익스트랙트 토너",
			"product_id": 4303,
			"image_url": "http://d9vmi5fxk1gsw.cloudfront.net/home/glowmee/upload/20170105/1483596672157.png",
			"price": 28000,
			"brand_name": "빌리프"
		},
		{...},
        {...},
        {...}
	]
}
```



## 검색

### 바코드 스캔
- URL : http://13.124.64.211:11022/scan
- Method : `POST`
- request

| 필드명  | 값 | 설명         |
| :------------ | :-----------: | :------------------: |
| barcode    | `8806146941717`|        바코드 id값      |

- response

```
{
	"product_id": 4303,
	"name": "위치헤이즐 허벌 익스트랙트 토너",
	"brand_name": "빌리프",
	"image_url": "http://d9vmi5fxk1gsw.cloudfront.net/home/glowmee/upload/20170105/1483596672157.png"
}
```

### 상품 정보 검색
- URL : http://13.124.64.211:11022/search
- Method : `POST`
- request

| 필드명  | 값 | 설명         |
| :------------ | :-----------: | -------------------: |
| product_id   | `4303`|              |

- response

```
{
	"product_id": 4303,
	"name": "위치헤이즐 허벌 익스트랙트 토너",
	"brand_name": "빌리프",
	"price": 28000,
	"link": "http://shopping.naver.com/search/all.nhn?query=%EC%9C%84%EC%B9%98%ED%97%A4%EC%9D%B4%EC%A6%90+%ED%97%88%EB%B2%8C+%EC%9D%B5%EC%8A%A4%ED%8A%B8%EB%9E%99%ED%8A%B8+%ED%86%A0%EB%84%88",
	"image_url": "http://d9vmi5fxk1gsw.cloudfront.net/home/glowmee/upload/20170105/1483596672157.png"
}
```

## 내 파우치
### 내 파우치 목록
- URL : http://13.124.64.211:11022/my_pouch/list
- Method : `POST`
- request

| 필드명  | 값 | 설명         |
| :------------ | :-----------: | -------------------: |
| user_id    | `10612`|              |

- response

```
{
	"item": [
		{
			"name": "위치헤이즐 허벌 익스트랙트 토너",
			"product_id": 4303,
            "status":"",
			"image_url": "http://d9vmi5fxk1gsw.cloudfront.net/home/glowmee/upload/20170105/1483596672157.png",
			"brand_name": "빌리프",
            "d_day": 21,
            "purchase_time":""
		},
		{...},
        {...},
        {...}
	]
}
```


### 파우치에 등록
- URL : http://13.124.64.211:11022/my_pouch/add_item
- Method : `POST`
- request

| 필드명  | 값 | 설명         |
| :------------ | :-----------: | -------------------: |
| user_id    | `10612`|         |
| product_id   | `4303`      |          |
- response

```
{
	"status": "true"
}
```

### 내 파우치에서 삭제
- URL : http://13.124.64.211:11022/my_pouch/delete
- Method : `POST`
- request

| 필드명  | 값 | 설명         |
| :------------ | :-----------: | -------------------: |
| user_id    | `10612`|         |
| product_id   | `4303`      |          |
- response

```
{
	"status": "true"
}
```

### 재 구매
- URL : http://13.124.64.211:11022/my_pouch/re_buy
- Method : `POST`
- request

| 필드명  | 값 | 설명         |
| :------------ | :-----------: | -------------------: |
| user_id    | `10612`|         |
| product_id   | `4303`      |          |
- response

```
{
	"status": "true"
}
```

