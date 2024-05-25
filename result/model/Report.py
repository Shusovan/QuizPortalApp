from dataclasses import dataclass
from typing import List

from model.Question import Questions

@dataclass
class Report:

    firstName : str

    lastName : str

    email : str

    tittle : str

    duration : int

    attempt : int

    question_list : List[Questions]

    final_score : int

    quizId : str