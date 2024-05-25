from dataclasses import dataclass

@dataclass
class Questions:

    questionId : int

    quizId : str

    question : str

    option1 : str

    option2 : str

    option3 : str

    option4 : str

    correctAnswer : str

    isCorrect : bool = False

    response : str = None
