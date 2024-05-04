from dataclasses import dataclass

@dataclass
class Candidate:

    firstName : str

    lastName : str

    email : str

    quizId : str

    tittle : str

    duration : int

    question : str

    option1 : str

    option2 : str

    option3 : str

    option4 : str

    correctAnswer : str

    userResponse : str