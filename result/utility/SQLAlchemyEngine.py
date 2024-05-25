from os import environ
import psycopg2
from sqlalchemy import create_engine
from requests import Session
from sqlalchemy.orm import sessionmaker
from model.User_Score import Base

def SQLEngine():

    session = None

    try:
        host = environ.get("DB_Host")
        username = environ.get("DB_Username")
        password = environ.get("DB_Password")
        db = environ.get("DB_Name")

        # creating connection
        connection_string = f"postgresql://{username}:{password}@{host}:5432/{db}"

        # creating database engine
        engine = create_engine(connection_string)

        # creating session
        Session = sessionmaker(bind = engine)
        session = Session()

        # creating table in databsse
        Base.metadata.create_all(engine)

    except Exception as e:
        print(e)

    return session
