from flask import Flask, jsonify
from dotenv import load_dotenv
from flask_sqlalchemy import SQLAlchemy

from routes import result_routes

app = Flask(__name__)

# connecting to postgresql DB
# app.config['SQLALCHEMY_DATABASE_URI'] = 'postgresql://postgres:Alliance123@localhost:5432/result'
# app.config['SQLALCHEMY_TRACK_MODIFICATIONS'] = False

# db = SQLAlchemy(app)

load_dotenv()
app.register_blueprint(result_routes.result)

@app.route("/")
def hello_world():
    return jsonify("Hello World"), 200

if __name__ == "main":
    app.run(debug=True)