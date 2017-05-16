## ��ŷ

### ��ŷ ����
- URL : http://13.124.64.211:11022/rank
- Method : `POST`
- request

| �ʵ��  | �� | ����         |
| :------------ | :-----------: | -------------------: |
| order    | `popularity`, `re_purchase`|              |
| age    | `all`, `10`,`20`,`30`,`40+`      |          |
| rank_term| `all`,`3`,`6`  ||
| category| `��Ų�ɾ�`  ||
- response

```
{
	"item": [
		{
			"name": "��ġ������ ��� �ͽ�Ʈ��Ʈ ���",
			"product_id": 4303,
			"image_url": "http://d9vmi5fxk1gsw.cloudfront.net/home/glowmee/upload/20170105/1483596672157.png",
			"price": 28000,
			"brand_name": "������"
		},
		{...},
        {...},
        {...}
	]
}
```



## �˻�

### ���ڵ� ��ĵ
- URL : http://13.124.64.211:11022/scan
- Method : `POST`
- request

| �ʵ��  | �� | ����         |
| :------------ | :-----------: | :------------------: |
| barcode    | `8806146941717`|        ���ڵ� id��      |

- response

```
{
	"product_id": 4303,
	"name": "��ġ������ ��� �ͽ�Ʈ��Ʈ ���",
	"brand_name": "������",
	"image_url": "http://d9vmi5fxk1gsw.cloudfront.net/home/glowmee/upload/20170105/1483596672157.png"
}
```

### ��ǰ ���� �˻�
- URL : http://13.124.64.211:11022/search
- Method : `POST`
- request

| �ʵ��  | �� | ����         |
| :------------ | :-----------: | -------------------: |
| product_id   | `4303`|              |

- response

```
{
	"product_id": 4303,
	"name": "��ġ������ ��� �ͽ�Ʈ��Ʈ ���",
	"brand_name": "������",
	"price": 28000,
	"link": "http://shopping.naver.com/search/all.nhn?query=%EC%9C%84%EC%B9%98%ED%97%A4%EC%9D%B4%EC%A6%90+%ED%97%88%EB%B2%8C+%EC%9D%B5%EC%8A%A4%ED%8A%B8%EB%9E%99%ED%8A%B8+%ED%86%A0%EB%84%88",
	"image_url": "http://d9vmi5fxk1gsw.cloudfront.net/home/glowmee/upload/20170105/1483596672157.png"
}
```

## �� �Ŀ�ġ
### �� �Ŀ�ġ ���
- URL : http://13.124.64.211:11022/my_pouch/list
- Method : `POST`
- request

| �ʵ��  | �� | ����         |
| :------------ | :-----------: | -------------------: |
| user_id    | `10612`|              |

- response

```
{
	"item": [
		{
			"name": "��ġ������ ��� �ͽ�Ʈ��Ʈ ���",
			"product_id": 4303,
            "status":"",
			"image_url": "http://d9vmi5fxk1gsw.cloudfront.net/home/glowmee/upload/20170105/1483596672157.png",
			"brand_name": "������",
            "d_day": 21,
            "purchase_time":""
		},
		{...},
        {...},
        {...}
	]
}
```


### �Ŀ�ġ�� ���
- URL : http://13.124.64.211:11022/my_pouch/add_item
- Method : `POST`
- request

| �ʵ��  | �� | ����         |
| :------------ | :-----------: | -------------------: |
| user_id    | `10612`|         |
| product_id   | `4303`      |          |
- response

```
{
	"status": "true"
}
```

### �� �Ŀ�ġ���� ����
- URL : http://13.124.64.211:11022/my_pouch/delete
- Method : `POST`
- request

| �ʵ��  | �� | ����         |
| :------------ | :-----------: | -------------------: |
| user_id    | `10612`|         |
| product_id   | `4303`      |          |
- response

```
{
	"status": "true"
}
```

### �� ����
- URL : http://13.124.64.211:11022/my_pouch/re_buy
- Method : `POST`
- request

| �ʵ��  | �� | ����         |
| :------------ | :-----------: | -------------------: |
| user_id    | `10612`|         |
| product_id   | `4303`      |          |
- response

```
{
	"status": "true"
}
```

