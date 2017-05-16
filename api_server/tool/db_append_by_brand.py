#!/usr/bin/env python3
# coding:utf-8
import requests
import random
from bs4 import BeautifulSoup
from urllib.parse import quote
from glowpickAPI import glowpickAPI
import pymongo



connection = pymongo.MongoClient('mongodb://localhost:27017')
db = connection.db
product = db.product

api = glowpickAPI()

def name_to_link(name='노세범 파우더 크림'):
    try:
        br = requests.post('http://www.innisfree.com/kr/ko/Search.do',{'query':name})
        soup = BeautifulSoup(br.content,'html.parser')
        div_tag = soup.find('div',{'class':'listCon'})
        a_tag = div_tag.find('a')
        
        return 'http://www.innisfree.com'+a_tag.get('href')
    except:
        return 'http://shopping.naver.com/search/all.nhn?query='+quote(name)


n=1
while 1:
    r = api.get_request('v1.0/products/brand/186/?page=%d&order=rank&gender=all&age=all&skin=all&rank_term=all'%(n))
    
    json_data = r.json()
    
    for jsondata in json_data['products']:
        dic = {'rebuy_percent': random.randint(0, 75),
    'popularity': 0,
    'name': jsondata.get('product_title'),
    'link': name_to_link(jsondata.get('product_title')),
    'brand_name': jsondata.get('brand_title'),
    'expiration_date': '',
    'image_url': jsondata.get('product_image'),
    'category': '',
    'product_id': jsondata.get('id_product'),
    'review_count': jsondata.get('review_count')}
        
        print(dic)
        product.insert(dic)
    
    
    if jsondata.get('is_last_page'):
        break
    n+=1
    
