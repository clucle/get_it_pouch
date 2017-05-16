import requests
from bs4 import BeautifulSoup
import re

class upcAPI:
    API_ROOT = 'http://www.upcitemdb.com/upc/'
    def __init__(self):
        self.br = requests.Session()
    
    def search(search, upc=''):
        r =search.br.get(search.API_ROOT+str(upc))
        soup = BeautifulSoup(r.content,'html.parser')

        dic={}
        
        
        for ol_tag in soup.find_all('ol',{'class':'num'}):
            for li_tag in ol_tag.find_all('li'):
                if li_tag.text:
                    dic['name']=li_tag.text
                    break
        
        for dl_tag in soup.find_all('dl',{'class':'detail-list'}):
            d=re.findall('<dt>Brand:</dt>.*?<dd>(.*?)</dd>',dl_tag.decode_contents(),re.DOTALL)
            if d:
                dic['brand_name'] = d[0]
                break
        
        for img_tag in soup.find_all('img',{'class':['product','amzn']}):
            if img_tag:
                if img_tag.get('src'):
                    dic['image_url'] = img_tag.get('src')
                    break
        
        return dic


    

        
        
        

if __name__ == '__main__':
    a = upcAPI()
    print(a.search('8806146941717'))