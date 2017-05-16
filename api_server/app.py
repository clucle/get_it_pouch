from flask import Flask, jsonify, request,redirect
import pymongo
from tool import glowpickAPI
import time
from random import randint
from tool import upcAPI

import json

MONGODB_HOST = 'localhost'
MONGODB_PORT = 27017

barcodes = {'3282779035576': '1057', '8806146941717': '861'}
connection = pymongo.MongoClient(host=MONGODB_HOST, port=MONGODB_PORT)
db = connection.db

pouch = db.pouch

app = Flask(__name__)


@app.route('/')
def index():
    return 'Running!'

@app.route('/video')
def video():

    return redirect('https://youtu.be/98aBhp8cB7I',302)


@app.route('/rank', methods=['POST', 'GET'])
def rank():
    print(request.form)
    categry_name = request.form.get('category')
    gp_api = glowpickAPI.glowpickAPI()
    r = gp_api.rank(category=categry_name)
    if not r:
        return 'bad category', 404
    print(r)

    return jsonify({'item': r})


@app.route('/scan', methods=['POST'])
def scan():
    code = request.form.get('barcode')
    if code:
        db.barcode.insert({'code': code})

        if barcodes.get(str(code)):
            api = glowpickAPI.glowpickAPI()
            return jsonify(api.search_product(barcodes.get(str(code))))
        else:
            api = upcAPI.upcAPI()
            d = api.search(code)
            if d:
                d['product_id']=0
                return jsonify(d)
    return '', 404


@app.route('/search', methods=['POST'])
def search():
    return ''


@app.route('/my_pouch/list', methods=['POST', 'GET'])
def my_list():
    items = []
    for dic in pouch.find():
        item = {ky: dic[ky] for ky in
                ["name", "product_id", "status", "image_url", "brand_name", "d_day", "purchase_time"]}
        items.append(item)
    print(items)

    return jsonify({'item': items})


@app.route('/my_pouch/add_item', methods=['POST'])
def pouch_add():
    product_id = request.form.get('product_id')
    if product_id:
        api = glowpickAPI.glowpickAPI()
        j = api.search_product(product_id)

        j.update({'status': 'alive', 'purchase_time': time.time(), 'd_day': randint(1, 60)})

        pouch.insert(j)

    return jsonify({"status": "true"})


@app.route('/my_pouch/delete', methods=['POST'])
def pouch_del():
    product_id = request.form.get('product_id')
    if product_id:
        pouch.delete_onc({'product_id': product_id})
        return jsonify({"status": "true"})
    return '', 404


if __name__ == '__main__':
    app.run(host='0.0.0.0', port=11022)
