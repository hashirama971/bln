from nltk.chat.util import Chat, reflections
pairs = [
    [
        r"money",
        ["Where?",]
    ],
     [
        r"finance",
        ["Many firms provide loan options",]
    ],
    [
        r"invest",
        ["Yes,Offcourse.Basically there are many options to invest.Regional Or Investment Banks In which section would you like to invest?",]
    ],
    [
        r"bank",
        ["Yes,Offcourse.Basically there are many options to invest.Regional Or Investment Banks In which section would you like to invest?",]
    ],
    [
        r"Shares",
        ["Which company Shares?",]
    ],
    [
        r"hi|hey|hello",
        ["Hello", "Hey there",]
    ],
    [
        r"loan",
        ["Which loan?? Housing ,Personal,Educational.I recommend to visit SBI banks for this",]
        
    ],
    [
        r"investment",
        ["Well there are many such as UBS,Barclays,Deutsche bank,HSBC,Wells Fargo",]
        
    ],
   
    
    [
        r"quit",
        ["BBye take care. See you soon :) ","It was nice talking to you. See you soon :)"]
],
]
def chatty():
        print("Hi, I'm Chatty and I chat alot ;)\nPlease type lowercase English language to start a conversation. Type quit to leave ") #default message at the start
        chat = Chat(pairs, reflections)
        chat.converse()
if __name__ == "__main__":
    chatty()
