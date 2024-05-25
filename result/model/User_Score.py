from dataclasses import dataclass
from datetime import datetime
from model import user_model
from sqlalchemy.ext.declarative import declarative_base
from sqlalchemy import Column, DateTime, Integer, String

Base = declarative_base()

@dataclass
class Score(Base):

    __tablename__ = "user_score"

    scoreAutoIncrid = Column(Integer, primary_key=True, autoincrement=True)

    userId = Column(String(10))

    quizId = Column(String(10))

    attemptId = Column(Integer)

    score = Column(Integer)

    row_created = Column(DateTime, default = datetime.now())
