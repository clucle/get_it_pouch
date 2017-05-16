#!/usr/bin/env python3
# coding:utf-8

import requests


class glowpickAPI:
    ROOT = 'https://api.glowpick.com:51666/'
    HEADERS = {
        'Accept': 'application/json',
        'Content-type': 'application/json',
        'Authorization': "Bearer BZqIKU1F51MlYk6F8lL3PHaELBykX4",
        'UID': '70455bf84130024a',
        'IDREGISTER': '0',
        'OS': 'aos',
        'APPVERSION': '1.9.0',
        'User-Agent': 'okhttp/3.6.0'
    }
    CATEGORY = {'바디/핸드/풋': 11, '여성용품': 21, '베이비&맘': 15, '마스크/팩': 4, '미용렌즈': 17, '선케어': 3, '기타제품': 14, '향수': 9,
                '기능성화장품': 2, '클렌징': 5, '스킨케어': 1, '색조메이크업': 7, '남성화장품': 8, '베이스메이크업': 6, '헤어': 10, '네일': 13, '바디라인': 12}

    AGE = {'10': '10s', '20': '20early,20late', '30': '30early', '40+': '30late', 'all': 'all'}

    TERM = {'3': '3month', '6': '6month', 'all': 'all'}

    def __init__(self):
        self.br = requests.Session()
        self.br.headers.update(self.HEADERS)

    def rank(self, order='rank', age='all', rank_term='all', category='스킨케어'):
        if self.CATEGORY.get(category):
            parms = {
                'page': 1,
                'order': order,
                'gender': 'all',
                'age': self.AGE.get(str(age)),
                'skin': 'all',
                'rank_term': self.TERM.get(rank_term)}

            r = self.br.get(self.ROOT + 'products/category/%d/' % (self.CATEGORY.get(category)), params=parms).json()

            return [{
                "name": k.get('product_title'),
                "product_id": k.get('id_product'),
                "image_url": k.get('product_image'),
                "price": k.get('price'),
                "brand_name": k.get('brand_title')}
                for k in r['products']]

    def get_request(self, endpoint='', params=None):
        url = self.ROOT + endpoint
        return self.br.get(url, params=params)

    def search_product(self, Id='4303'):
        r = self.br.get(self.ROOT + 'v1.0/product/detail/%s/' % (Id))
        if r:
            data = r.json()
            print(data)
            return {
                "product_id": data.get('id_product'),
                "name": data.get('product_title'),
                "brand_name": data.get('brand_title'),
                "image_url": data.get('product_thumbnail')
            }


if __name__ == '__main__':
    a = glowpickAPI()
    print(a.search_product())
