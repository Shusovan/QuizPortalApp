from flask import Blueprint, jsonify, request
#from requests import request
from views import result_views
from http import HTTPStatus

result = Blueprint('result', __name__, url_prefix='/result')

# method : get all users

@result.route('/getAllUser', methods=['GET'])
def get_all_users():
    return jsonify(result_views.get_all_users()), HTTPStatus.OK

# GET userId
# GET quizId
# GET attemptId

@result.route('/getScores', methods=['GET'])
def get_scores():

    return jsonify(result_views.get_scores(request.args)), HTTPStatus.OK

@result.route('/getReport', methods=['GET'])
def get_report():

    return jsonify(result_views.get_report(request.args)), HTTPStatus.OK