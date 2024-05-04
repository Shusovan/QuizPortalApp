from dataclasses import dataclass

@dataclass
class User():
        
    userAutoIncrementId : int
    
    userId : str
    
    firstName : str
    
    lastName : str
    
    email : str
    
    quiz : None

