from dataclasses import dataclass

from sqlalchemy import DateTime
from model import user_model

@dataclass
class User_Quiz():

    autoIncrId : int

    quizId : str

    user_model : user_model

    rowCreated : DateTime

    isDisabled = False