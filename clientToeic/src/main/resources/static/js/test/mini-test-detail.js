
// window.onbeforeunload = function() {
//     return "Bạn có chắc chắn muốn rời khỏi trang này?";
//   };
  
var conversation = {
    time:60,
    data: [
        {
            part:1,
            questions:[
                {
                    image:'https://learnenglishteens.britishcouncil.org/sites/teens/files/styles/max_2600x2600/public/field/image/RS9182_GettyImages-1224983587-hig.jpg?itok=oie_RJZL',
                    audioSrc: "https://bd748f642cf8b253d59c-5c160b94f727c0d27cbeccc854542bc6.ssl.cf1.rackcdn.com/LearnEnglish%20Teens%20-%20Listening%20skills%20practice%20-%20A1%20-%20A%20good%20night's%20sleep.mp3",
                    text: "W: Will you help me move my desk by the window?",
                    answers: ["Yes", "No", "Maybe","A hoặc B"],
                    correctAnswerIndex: 0
                },
                {
                    image:'https://learnenglishteens.britishcouncil.org/sites/teens/files/styles/max_2600x2600/public/field/image/RS9182_GettyImages-1224983587-hig.jpg?itok=oie_RJZL',
                    audioSrc: "https://bd748f642cf8b253d59c-5c160b94f727c0d27cbeccc854542bc6.ssl.cf1.rackcdn.com/LearnEnglish%20Teens%20-%20Listening%20skills%20practice%20-%20A1%20-%20A%20good%20night's%20sleep.mp3",
                    text: "M: Do you want the chair by the door?",
                    answers: ["Yes", "No", "I don't know"],
                    correctAnswerIndex: 0
                },
                {
                    image:'https://learnenglishteens.britishcouncil.org/sites/teens/files/styles/max_2600x2600/public/field/image/RS9182_GettyImages-1224983587-hig.jpg?itok=oie_RJZL',
                    audioSrc: "https://bd748f642cf8b253d59c-5c160b94f727c0d27cbeccc854542bc6.ssl.cf1.rackcdn.com/LearnEnglish%20Teens%20-%20Listening%20skills%20practice%20-%20A1%20-%20A%20good%20night's%20sleep.mp3",
                    text: "W: No, let’s move it next to the desk",
                    answers: ["Sure", "Okay", "Sounds good"],
                    correctAnswerIndex: 0
                },
                {
                    image:'https://learnenglishteens.britishcouncil.org/sites/teens/files/styles/max_2600x2600/public/field/image/RS9182_GettyImages-1224983587-hig.jpg?itok=oie_RJZL',
                    audioSrc: "https://bd748f642cf8b253d59c-5c160b94f727c0d27cbeccc854542bc6.ssl.cf1.rackcdn.com/LearnEnglish%20Teens%20-%20Listening%20skills%20practice%20-%20A1%20-%20A%20good%20night's%20sleep.mp3",
                    text: "M: Chair by the desk, desk by the window. Anything else to move?",
                    answers: ["Yes", "No", "Maybe"],
                    correctAnswerIndex: 0
                }
            ]
        },
        {
            part:2,
            questions:[
                {
                    audioSrc: "https://bd748f642cf8b253d59c-5c160b94f727c0d27cbeccc854542bc6.ssl.cf1.rackcdn.com/LearnEnglish%20Teens%20-%20Listening%20skills%20practice%20-%20A1%20-%20A%20good%20night's%20sleep.mp3",
                    text: "W: Will you help me move my desk by the window?",
                    answers: ["Yes", "No", "Maybe","A hoặc B"],
                    correctAnswerIndex: 0
                },
                {
                    audioSrc: "https://bd748f642cf8b253d59c-5c160b94f727c0d27cbeccc854542bc6.ssl.cf1.rackcdn.com/LearnEnglish%20Teens%20-%20Listening%20skills%20practice%20-%20A1%20-%20A%20good%20night's%20sleep.mp3",
                    text: "M: Do you want the chair by the door?",
                    answers: ["Yes", "No", "I don't know"],
                    correctAnswerIndex: 0
                },
                {
                    audioSrc: "https://bd748f642cf8b253d59c-5c160b94f727c0d27cbeccc854542bc6.ssl.cf1.rackcdn.com/LearnEnglish%20Teens%20-%20Listening%20skills%20practice%20-%20A1%20-%20A%20good%20night's%20sleep.mp3",
                    text: "W: No, let’s move it next to the desk",
                    answers: ["Sure", "Okay", "Sounds good"],
                    correctAnswerIndex: 0
                },
                {
        
                    audioSrc: "https://bd748f642cf8b253d59c-5c160b94f727c0d27cbeccc854542bc6.ssl.cf1.rackcdn.com/LearnEnglish%20Teens%20-%20Listening%20skills%20practice%20-%20A1%20-%20A%20good%20night's%20sleep.mp3",
                    text: "M: Chair by the desk, desk by the window. Anything else to move?",
                    answers: ["Yes", "No", "Maybe"],
                    correctAnswerIndex: 0
                },
                {
                    audioSrc: "https://bd748f642cf8b253d59c-5c160b94f727c0d27cbeccc854542bc6.ssl.cf1.rackcdn.com/LearnEnglish%20Teens%20-%20Listening%20skills%20practice%20-%20A1%20-%20A%20good%20night's%20sleep.mp3",
                    text: "M: Chair by the desk, desk by the window. Anything else to move?",
                    answers: ["Yes", "No", "Maybe"],
                    correctAnswerIndex: 0
                },
                {
                    audioSrc: "https://bd748f642cf8b253d59c-5c160b94f727c0d27cbeccc854542bc6.ssl.cf1.rackcdn.com/LearnEnglish%20Teens%20-%20Listening%20skills%20practice%20-%20A1%20-%20A%20good%20night's%20sleep.mp3",
                    text: "M: Chair by the desk, desk by the window. Anything else to move?",
                    answers: ["Yes", "No", "Maybe"],
                    correctAnswerIndex: 0
                },
                {
                    audioSrc: "https://bd748f642cf8b253d59c-5c160b94f727c0d27cbeccc854542bc6.ssl.cf1.rackcdn.com/LearnEnglish%20Teens%20-%20Listening%20skills%20practice%20-%20A1%20-%20A%20good%20night's%20sleep.mp3",
                    text: "W: Will you help me move my desk by the window?",
                    answers: ["Yes", "No", "Maybe","A hoặc B"],
                    correctAnswerIndex: 0
                },
                {
                    audioSrc: "https://bd748f642cf8b253d59c-5c160b94f727c0d27cbeccc854542bc6.ssl.cf1.rackcdn.com/LearnEnglish%20Teens%20-%20Listening%20skills%20practice%20-%20A1%20-%20A%20good%20night's%20sleep.mp3",
                    text: "M: Do you want the chair by the door?",
                    answers: ["Yes", "No", "I don't know"],
                    correctAnswerIndex: 0
                },
                {
                    audioSrc: "https://bd748f642cf8b253d59c-5c160b94f727c0d27cbeccc854542bc6.ssl.cf1.rackcdn.com/LearnEnglish%20Teens%20-%20Listening%20skills%20practice%20-%20A1%20-%20A%20good%20night's%20sleep.mp3",
                    text: "W: No, let’s move it next to the desk",
                    answers: ["Sure", "Okay", "Sounds good"],
                    correctAnswerIndex: 0
                },
                {
                    audioSrc: "https://bd748f642cf8b253d59c-5c160b94f727c0d27cbeccc854542bc6.ssl.cf1.rackcdn.com/LearnEnglish%20Teens%20-%20Listening%20skills%20practice%20-%20A1%20-%20A%20good%20night's%20sleep.mp3",
                    text: "W: No, let’s move it next to the desk",
                    answers: ["Sure", "Okay", "Sounds good"],
                    correctAnswerIndex: 0
                },
                {
                    audioSrc: "https://bd748f642cf8b253d59c-5c160b94f727c0d27cbeccc854542bc6.ssl.cf1.rackcdn.com/LearnEnglish%20Teens%20-%20Listening%20skills%20practice%20-%20A1%20-%20A%20good%20night's%20sleep.mp3",
                    text: "W: No, let’s move it next to the desk",
                    answers: ["Sure", "Okay", "Sounds good"],
                    correctAnswerIndex: 0
                },
                {
                    audioSrc: "https://bd748f642cf8b253d59c-5c160b94f727c0d27cbeccc854542bc6.ssl.cf1.rackcdn.com/LearnEnglish%20Teens%20-%20Listening%20skills%20practice%20-%20A1%20-%20A%20good%20night's%20sleep.mp3",
                    text: "W: No, let’s move it next to the desk",
                    answers: ["Sure", "Okay", "Sounds good"],
                    correctAnswerIndex: 0
                },
                {
                    audioSrc: "https://bd748f642cf8b253d59c-5c160b94f727c0d27cbeccc854542bc6.ssl.cf1.rackcdn.com/LearnEnglish%20Teens%20-%20Listening%20skills%20practice%20-%20A1%20-%20A%20good%20night's%20sleep.mp3",
                    text: "W: No, let’s move it next to the desk",
                    answers: ["Sure", "Okay", "Sounds good"],
                    correctAnswerIndex: 0
                }
            ]
        },
        {
            part:3,
            questions:[
                {
                    image:"https://www.anhngumshoa.com/uploads/images/resize/550x550/capture17.png",
                    audioSrc: "https://bd748f642cf8b253d59c-5c160b94f727c0d27cbeccc854542bc6.ssl.cf1.rackcdn.com/LearnEnglish%20Teens%20-%20Listening%20skills%20practice%20-%20A1%20-%20A%20good%20night's%20sleep.mp3",
                    questionsChildren:[
                        {
                            text: "Will you help me move my desk by the window?",
                            answers: ["Yes", "No", "Maybe","A hoặc B"],
                            correctAnswerIndex: 0
                        },
                        {
                            text: "Do you want the chair by the door?",
                            answers: ["Yes", "No", "I don't know"],
                            correctAnswerIndex: 0
                        },
                        {
                            text: "Do you want the chair by the door?",
                            answers: ["Yes", "No", "I don't know"],
                            correctAnswerIndex: 0
                        }
                    ]
        
                },
                {
                    audioSrc: "https://bd748f642cf8b253d59c-5c160b94f727c0d27cbeccc854542bc6.ssl.cf1.rackcdn.com/LearnEnglish%20Teens%20-%20Listening%20skills%20practice%20-%20A1%20-%20A%20good%20night's%20sleep.mp3",
                    questionsChildren:[
                        {
                            text: "Will you help me move my desk by the window?",
                            answers: ["Yes", "No", "Maybe","A hoặc B"],
                            correctAnswerIndex: 0
                        },
                        {
                            text: "Do you want the chair by the door?",
                            answers: ["Yes", "No", "I don't know"],
                            correctAnswerIndex: 0
                        },
                        {
                            text: "Do you want the chair by the door?",
                            answers: ["Yes", "No", "I don't know"],
                            correctAnswerIndex: 0
                        }
                    ]
        
                },
                {
                    audioSrc: "https://bd748f642cf8b253d59c-5c160b94f727c0d27cbeccc854542bc6.ssl.cf1.rackcdn.com/LearnEnglish%20Teens%20-%20Listening%20skills%20practice%20-%20A1%20-%20A%20good%20night's%20sleep.mp3",
                    questionsChildren:[
                        {
                            text: "Will you help me move my desk by the window?",
                            answers: ["Yes", "No", "Maybe","A hoặc B"],
                            correctAnswerIndex: 0
                        },
                        {
                            text: "Do you want the chair by the door?",
                            answers: ["Yes", "No", "I don't know"],
                            correctAnswerIndex: 0
                        },
                        {
                            text: "Do you want the chair by the door?",
                            answers: ["Yes", "No", "I don't know"],
                            correctAnswerIndex: 0
                        }
                    ]
        
                },
                {
                    audioSrc: "https://bd748f642cf8b253d59c-5c160b94f727c0d27cbeccc854542bc6.ssl.cf1.rackcdn.com/LearnEnglish%20Teens%20-%20Listening%20skills%20practice%20-%20A1%20-%20A%20good%20night's%20sleep.mp3",
                    questionsChildren:[
                        {
                            text: "Will you help me move my desk by the window?",
                            answers: ["Yes", "No", "Maybe","A hoặc B"],
                            correctAnswerIndex: 0
                        },
                        {
                            text: "Do you want the chair by the door?",
                            answers: ["Yes", "No", "I don't know"],
                            correctAnswerIndex: 0
                        },
                        {
                            text: "Do you want the chair by the door?",
                            answers: ["Yes", "No", "I don't know"],
                            correctAnswerIndex: 0
                        }
                    ]
        
                },
                {
                    audioSrc: "https://bd748f642cf8b253d59c-5c160b94f727c0d27cbeccc854542bc6.ssl.cf1.rackcdn.com/LearnEnglish%20Teens%20-%20Listening%20skills%20practice%20-%20A1%20-%20A%20good%20night's%20sleep.mp3",
                    questionsChildren:[
                        {
                            text: "Will you help me move my desk by the window?",
                            answers: ["Yes", "No", "Maybe","A hoặc B"],
                            correctAnswerIndex: 0
                        },
                        {
                            text: "Do you want the chair by the door?",
                            answers: ["Yes", "No", "I don't know"],
                            correctAnswerIndex: 0
                        },
                        {
                            text: "Do you want the chair by the door?",
                            answers: ["Yes", "No", "I don't know"],
                            correctAnswerIndex: 0
                        }
                    ]
        
                },
                {

                    audioSrc: "https://bd748f642cf8b253d59c-5c160b94f727c0d27cbeccc854542bc6.ssl.cf1.rackcdn.com/LearnEnglish%20Teens%20-%20Listening%20skills%20practice%20-%20A1%20-%20A%20good%20night's%20sleep.mp3",
                    questionsChildren:[
                        {
                            text: "Will you help me move my desk by the window?",
                            answers: ["Yes", "No", "Maybe","A hoặc B"],
                            correctAnswerIndex: 0
                        },
                        {
                            text: "Do you want the chair by the door?",
                            answers: ["Yes", "No", "I don't know"],
                            correctAnswerIndex: 0
                        },
                        {
                            text: "Do you want the chair by the door?",
                            answers: ["Yes", "No", "I don't know"],
                            correctAnswerIndex: 0
                        }
                    ]
        
                }
            ]
        },
        {
            part:4,
            questions:[
                {
                    image:"https://www.anhngumshoa.com/uploads/images/resize/550x550/capture17.png",
                    audioSrc: "https://bd748f642cf8b253d59c-5c160b94f727c0d27cbeccc854542bc6.ssl.cf1.rackcdn.com/LearnEnglish%20Teens%20-%20Listening%20skills%20practice%20-%20A1%20-%20A%20good%20night's%20sleep.mp3",
                    questionsChildren:[
                        {
                            text: "Will you help me move my desk by the window?",
                            answers: ["Yes", "No", "Maybe","A hoặc B"],
                            correctAnswerIndex: 0
                        },
                        {
                            text: "Do you want the chair by the door?",
                            answers: ["Yes", "No", "I don't know"],
                            correctAnswerIndex: 0
                        },
                        {
                            text: "Do you want the chair by the door?",
                            answers: ["Yes", "No", "I don't know"],
                            correctAnswerIndex: 0
                        }
                    ]
        
                },
                {
                    image:"https://www.anhngumshoa.com/uploads/images/resize/550x550/capture17.png",
                    audioSrc: "https://bd748f642cf8b253d59c-5c160b94f727c0d27cbeccc854542bc6.ssl.cf1.rackcdn.com/LearnEnglish%20Teens%20-%20Listening%20skills%20practice%20-%20A1%20-%20A%20good%20night's%20sleep.mp3",
                    questionsChildren:[
                        {
                            text: "Will you help me move my desk by the window?",
                            answers: ["Yes", "No", "Maybe","A hoặc B"],
                            correctAnswerIndex: 0
                        },
                        {
                            text: "Do you want the chair by the door?",
                            answers: ["Yes", "No", "I don't know"],
                            correctAnswerIndex: 0
                        },
                        {
                            text: "Do you want the chair by the door?",
                            answers: ["Yes", "No", "I don't know"],
                            correctAnswerIndex: 0
                        }
                    ]
        
                },
                {
                    image:"https://www.anhngumshoa.com/uploads/images/resize/550x550/capture17.png",
                    audioSrc: "https://bd748f642cf8b253d59c-5c160b94f727c0d27cbeccc854542bc6.ssl.cf1.rackcdn.com/LearnEnglish%20Teens%20-%20Listening%20skills%20practice%20-%20A1%20-%20A%20good%20night's%20sleep.mp3",
                    questionsChildren:[
                        {
                            text: "Will you help me move my desk by the window?",
                            answers: ["Yes", "No", "Maybe","A hoặc B"],
                            correctAnswerIndex: 0
                        },
                        {
                            text: "Do you want the chair by the door?",
                            answers: ["Yes", "No", "I don't know"],
                            correctAnswerIndex: 0
                        },
                        {
                            text: "Do you want the chair by the door?",
                            answers: ["Yes", "No", "I don't know"],
                            correctAnswerIndex: 0
                        }
                    ]
        
                },
                {
                    image:"https://www.anhngumshoa.com/uploads/images/resize/550x550/capture17.png",
                    audioSrc: "https://bd748f642cf8b253d59c-5c160b94f727c0d27cbeccc854542bc6.ssl.cf1.rackcdn.com/LearnEnglish%20Teens%20-%20Listening%20skills%20practice%20-%20A1%20-%20A%20good%20night's%20sleep.mp3",
                    questionsChildren:[
                        {
                            text: "Will you help me move my desk by the window?",
                            answers: ["Yes", "No", "Maybe","A hoặc B"],
                            correctAnswerIndex: 0
                        },
                        {
                            text: "Do you want the chair by the door?",
                            answers: ["Yes", "No", "I don't know"],
                            correctAnswerIndex: 0
                        },
                        {
                            text: "Do you want the chair by the door?",
                            answers: ["Yes", "No", "I don't know"],
                            correctAnswerIndex: 0
                        }
                    ]
        
                },
                {
                    image:"https://www.anhngumshoa.com/uploads/images/resize/550x550/capture17.png",
                    audioSrc: "https://bd748f642cf8b253d59c-5c160b94f727c0d27cbeccc854542bc6.ssl.cf1.rackcdn.com/LearnEnglish%20Teens%20-%20Listening%20skills%20practice%20-%20A1%20-%20A%20good%20night's%20sleep.mp3",
                    questionsChildren:[
                        {
                            text: "Will you help me move my desk by the window?",
                            answers: ["Yes", "No", "Maybe","A hoặc B"],
                            correctAnswerIndex: 0
                        },
                        {
                            text: "Do you want the chair by the door?",
                            answers: ["Yes", "No", "I don't know"],
                            correctAnswerIndex: 0
                        },
                        {
                            text: "Do you want the chair by the door?",
                            answers: ["Yes", "No", "I don't know"],
                            correctAnswerIndex: 0
                        }
                    ]
                }
            ]
        },
        {
            part:5,
            questions:[
                {
                    text: "Milky Cookies....... enters into a contract with a third party vendor in order to fulfill its business operations.",
                    answers: ["occasionally", "prematurely", "marginally","uncommonly"],
                    correctAnswerIndex: 0
                },
                {
                    text: "We want you to be aware that we’re doing our utmost,------ we will not be able to provide you with a personalized experience on our website.",
                    answers: ["in spite of", "so as", "despite","though"],
                    correctAnswerIndex: 0
                },
                {
                    text: "Mr. Conner’s remarks concerning the results of last year’s declining sales were concise and to the .............",
                    answers: ["grade", "feet", "point","spot"],
                    correctAnswerIndex: 0
                },
                {
                    text: "Milky Cookies....... enters into a contract with a third party vendor in order to fulfill its business operations.",
                    answers: ["occasionally", "prematurely", "marginally","uncommonly"],
                    correctAnswerIndex: 0
                },
                {
                    text: "We want you to be aware that we’re doing our utmost,------ we will not be able to provide you with a personalized experience on our website.",
                    answers: ["in spite of", "so as", "despite","though"],
                    correctAnswerIndex: 0
                },
                {
                    text: "Mr. Conner’s remarks concerning the results of last year’s declining sales were concise and to the .............",
                    answers: ["grade", "feet", "point","spot"],
                    correctAnswerIndex: 0
                },
                {
                    text: "Milky Cookies....... enters into a contract with a third party vendor in order to fulfill its business operations.",
                    answers: ["occasionally", "prematurely", "marginally","uncommonly"],
                    correctAnswerIndex: 0
                },
                {
                    text: "We want you to be aware that we’re doing our utmost,------ we will not be able to provide you with a personalized experience on our website.",
                    answers: ["in spite of", "so as", "despite","though"],
                    correctAnswerIndex: 0
                },
                {
                    text: "Mr. Conner’s remarks concerning the results of last year’s declining sales were concise and to the .............",
                    answers: ["grade", "feet", "point","spot"],
                    correctAnswerIndex: 0
                },
                {
                    text: "Milky Cookies....... enters into a contract with a third party vendor in order to fulfill its business operations.",
                    answers: ["occasionally", "prematurely", "marginally","uncommonly"],
                    correctAnswerIndex: 0
                },
                {
                    text: "We want you to be aware that we’re doing our utmost,------ we will not be able to provide you with a personalized experience on our website.",
                    answers: ["in spite of", "so as", "despite","though"],
                    correctAnswerIndex: 0
                },
                {
                    text: "Mr. Conner’s remarks concerning the results of last year’s declining sales were concise and to the .............",
                    answers: ["grade", "feet", "point","spot"],
                    correctAnswerIndex: 0
                },
                {
                    text: "Milky Cookies....... enters into a contract with a third party vendor in order to fulfill its business operations.",
                    answers: ["occasionally", "prematurely", "marginally","uncommonly"],
                    correctAnswerIndex: 0
                },
                {
                    text: "We want you to be aware that we’re doing our utmost,------ we will not be able to provide you with a personalized experience on our website.",
                    answers: ["in spite of", "so as", "despite","though"],
                    correctAnswerIndex: 0
                },
                {
                    text: "Mr. Conner’s remarks concerning the results of last year’s declining sales were concise and to the .............",
                    answers: ["grade", "feet", "point","spot"],
                    correctAnswerIndex: 0
                },
                {
                    text: "Milky Cookies....... enters into a contract with a third party vendor in order to fulfill its business operations.",
                    answers: ["occasionally", "prematurely", "marginally","uncommonly"],
                    correctAnswerIndex: 0
                },
                {
                    text: "We want you to be aware that we’re doing our utmost,------ we will not be able to provide you with a personalized experience on our website.",
                    answers: ["in spite of", "so as", "despite","though"],
                    correctAnswerIndex: 0
                },
                {
                    text: "Mr. Conner’s remarks concerning the results of last year’s declining sales were concise and to the .............",
                    answers: ["grade", "feet", "point","spot"],
                    correctAnswerIndex: 0
                }
            ]
        },
        {
            part:6,
            questions:[
                {
                    image:"https://www.anhngumshoa.com/uploads/images/resize/550x550/capture17.png",
                    description:"Notice to employment advertisers and job seekers Misleading advertisements placed in the employment (1)........ of our newspaper can result in hardship and time wasted by those who are looking for jobs, and this is certainly unacceptable. Placing false ads that are misleading is an offence against the Trade Practices and Fair Trade Act. Any (2)........ wishing to place a want ad with us should keep in mind that all advertisements should include a job title, a clear (3)........ of the job, and the income basis and be placed under the appropriate category For further information, contact the Department of Fair Trade at 755-5720 (4)........ business hours.",
                    questionsChildren:[
                        {
                            answers: ["area", "section", "branch","department"],
                            correctAnswerIndex: 0
                        },
                        {
                            answers: ["employees", "employs", "employment","employer"],
                            correctAnswerIndex: 0
                        },
                        {
                            answers: ["information", "subscription", "description","requirement"],
                            correctAnswerIndex: 0
                        },
                        {
                            answers: ["during", "dured", "dures","durring"],
                            correctAnswerIndex: 0
                        }
                    ]
                },
                {
                    description:"Notice to employment advertisers and job seekers Misleading advertisements placed in the employment (1)........ of our newspaper can result in hardship and time wasted by those who are looking for jobs, and this is certainly unacceptable. Placing false ads that are misleading is an offence against the Trade Practices and Fair Trade Act. Any (2)........ wishing to place a want ad with us should keep in mind that all advertisements should include a job title, a clear (3)........ of the job, and the income basis and be placed under the appropriate category For further information, contact the Department of Fair Trade at 755-5720 (4)........ business hours.",
                    questionsChildren:[
                        {
                            answers: ["area", "section", "branch","department"],
                            correctAnswerIndex: 0
                        },
                        {
                            answers: ["employees", "employs", "employment","employer"],
                            correctAnswerIndex: 0
                        },
                        {
                            answers: ["information", "subscription", "description","requirement"],
                            correctAnswerIndex: 0
                        },
                        {
                            answers: ["during", "dured", "dures","durring"],
                            correctAnswerIndex: 0
                        }
                    ]
                }
            ]
        },
        {
            part:7,
            questions:[
                {
                    image:"https://www.anhngumshoa.com/uploads/images/resize/550x550/capture17.png",
                    description:"Notice to employment advertisers and job seekers Misleading advertisements placed in the employment (1)........ of our newspaper can result in hardship and time wasted by those who are looking for jobs, and this is certainly unacceptable. Placing false ads that are misleading is an offence against the Trade Practices and Fair Trade Act. Any (2)........ wishing to place a want ad with us should keep in mind that all advertisements should include a job title, a clear (3)........ of the job, and the income basis and be placed under the appropriate category For further information, contact the Department of Fair Trade at 755-5720 (4)........ business hours.",
                    questionsChildren:[
                        {
                            answers: ["area", "section", "branch","department"],
                            correctAnswerIndex: 0
                        },
                        {
                            answers: ["employees", "employs", "employment","employer"],
                            correctAnswerIndex: 0
                        },
                        {
                            answers: ["information", "subscription", "description","requirement"],
                            correctAnswerIndex: 0
                        },
                        {
                            answers: ["during", "dured", "dures","durring"],
                            correctAnswerIndex: 0
                        },
                        {
                            answers: ["during", "dured", "dures","durring"],
                            correctAnswerIndex: 0
                        }
                    ]
                },
                {
                    description:"Notice to employment advertisers and job seekers Misleading advertisements placed in the employment (1)........ of our newspaper can result in hardship and time wasted by those who are looking for jobs, and this is certainly unacceptable. Placing false ads that are misleading is an offence against the Trade Practices and Fair Trade Act. Any (2)........ wishing to place a want ad with us should keep in mind that all advertisements should include a job title, a clear (3)........ of the job, and the income basis and be placed under the appropriate category For further information, contact the Department of Fair Trade at 755-5720 (4)........ business hours.",
                    questionsChildren:[
                        {
                            answers: ["area", "section", "branch","department"],
                            correctAnswerIndex: 0
                        },
                        {
                            answers: ["employees", "employs", "employment","employer"],
                            correctAnswerIndex: 0
                        },
                        {
                            answers: ["information", "subscription", "description","requirement"],
                            correctAnswerIndex: 0
                        },
                        {
                            answers: ["during", "dured", "dures","durring"],
                            correctAnswerIndex: 0
                        },
                        {
                            answers: ["during", "dured", "dures","durring"],
                            correctAnswerIndex: 0
                        }
                    ]
                },
                {
                    description:"Notice to employment advertisers and job seekers Misleading advertisements placed in the employment (1)........ of our newspaper can result in hardship and time wasted by those who are looking for jobs, and this is certainly unacceptable. Placing false ads that are misleading is an offence against the Trade Practices and Fair Trade Act. Any (2)........ wishing to place a want ad with us should keep in mind that all advertisements should include a job title, a clear (3)........ of the job, and the income basis and be placed under the appropriate category For further information, contact the Department of Fair Trade at 755-5720 (4)........ business hours.",
                    questionsChildren:[
                        {
                            answers: ["area", "section", "branch","department"],
                            correctAnswerIndex: 0
                        },
                        {
                            answers: ["employees", "employs", "employment","employer"],
                            correctAnswerIndex: 0
                        },
                        {
                            answers: ["information", "subscription", "description","requirement"],
                            correctAnswerIndex: 0
                        },
                        {
                            answers: ["during", "dured", "dures","durring"],
                            correctAnswerIndex: 0
                        },
                        {
                            answers: ["during", "dured", "dures","durring"],
                            correctAnswerIndex: 0
                        }
                    ]
                },
                {
                    description:"Notice to employment advertisers and job seekers Misleading advertisements placed in the employment (1)........ of our newspaper can result in hardship and time wasted by those who are looking for jobs, and this is certainly unacceptable. Placing false ads that are misleading is an offence against the Trade Practices and Fair Trade Act. Any (2)........ wishing to place a want ad with us should keep in mind that all advertisements should include a job title, a clear (3)........ of the job, and the income basis and be placed under the appropriate category For further information, contact the Department of Fair Trade at 755-5720 (4)........ business hours.",
                    questionsChildren:[
                        {
                            answers: ["area", "section", "branch","department"],
                            correctAnswerIndex: 0
                        },
                        {
                            answers: ["employees", "employs", "employment","employer"],
                            correctAnswerIndex: 0
                        },
                        {
                            answers: ["information", "subscription", "description","requirement"],
                            correctAnswerIndex: 0
                        },
                        {
                            answers: ["during", "dured", "dures","durring"],
                            correctAnswerIndex: 0
                        },
                        {
                            answers: ["during", "dured", "dures","durring"],
                            correctAnswerIndex: 0
                        }
                    ]
                },
                {
                    description:"Notice to employment advertisers and job seekers Misleading advertisements placed in the employment (1)........ of our newspaper can result in hardship and time wasted by those who are looking for jobs, and this is certainly unacceptable. Placing false ads that are misleading is an offence against the Trade Practices and Fair Trade Act. Any (2)........ wishing to place a want ad with us should keep in mind that all advertisements should include a job title, a clear (3)........ of the job, and the income basis and be placed under the appropriate category For further information, contact the Department of Fair Trade at 755-5720 (4)........ business hours.",
                    questionsChildren:[
                        {
                            answers: ["area", "section", "branch","department"],
                            correctAnswerIndex: 0
                        },
                        {
                            answers: ["employees", "employs", "employment","employer"],
                            correctAnswerIndex: 0
                        },
                        {
                            answers: ["information", "subscription", "description","requirement"],
                            correctAnswerIndex: 0
                        },
                        {
                            answers: ["during", "dured", "dures","durring"],
                            correctAnswerIndex: 0
                        }
                    ]
                }
               
            ]
        }
    ]
};


let questionButtons = [];
let totalQuestion =0;
function createButtonAnswer(){
    const backButton = document.getElementById('backButton');
    const nextButton = document.getElementById('nextButton');

    for (let i = 0; i < conversation.data.length; i++) {
        if(conversation.data[i].part === 1 || conversation.data[i].part === 2 || conversation.data[i].part === 5){
            totalQuestion += conversation.data[i].questions.length;  
    }else{
        conversation.data[i].questions.forEach(function(question,index){
           totalQuestion += question.questionsChildren.length; 
        });
    }
}
    

    const questionCount = totalQuestion;
    const $numberQuestion = $('#number-question');
    $numberQuestion.empty(); // Xóa các nút hiện có

    let buttonCount = 0;
    const rowCapacity = 6; // Số lượng div col trong mỗi hàng
    let rowCounter = 0; // Đếm số lượng hàng
    let $row = $('<div>').addClass('row');
    for (let i = 0; i < questionCount; i++) {
        const $div = $('<div>').addClass('divElementA col-2');

        const $button = $('<a>')
          .addClass('btn btn-outline-secondary rounded-circle mx-1 my-1')
          .attr('href', '#')
          .text(i+1);
        $div.append($button)  
        $button.on('click', function() {
            const wrapperClick = document.getElementById('wrapper-'+(i+1));
            const wrapperClickParent = wrapperClick.parentElement.id;
            showQuestion(parseInt(wrapperClickParent.split('-')[2]-1));
            if(currentQuestionIndex === 0){
                backButton.classList.add('d-none');
            }else{
                backButton.classList.remove('d-none');

            }
            if(currentQuestionIndex === countQuestion - 1){
                nextButton.classList.add('d-none');
            }else{
                nextButton.classList.remove('d-none');
            }   
          });  
          $row.append($div);
          buttonCount++;
   
        const remainingCols = rowCapacity - (i + 1) % rowCapacity; // Số lượng cột còn lại trong hàng
        if (remainingCols === 0 || i === questionCount - 1) {
            $numberQuestion.append($row);
            $row = $('<div>').addClass('row');
            rowCounter++;
        } else {
            // Kiểm tra nếu đây là hàng cuối cùng và cần thêm vào ít cột hơn
            if (i === questionCount - 1) {
                for (let j = 0; j < remainingCols; j++) {
                    const $emptyDiv = $('<div>').addClass('divElementA col-2');
                    $row.append($emptyDiv);
                }
            }
        }
          questionButtons.push($button);
        }
        // $numberQuestion.append($button);

}
createButtonAnswer();

// lăp qua toàn bộ question bỏ class active
function removeActiveQuestionNumber(){
    questionButtons.forEach(function(questionButton,index){
        questionButton.removeClass('active-number-question');
    });
}

// click vào nút của các part
function clickPart(){
    const links = document.querySelectorAll('.part a');

    // Thêm một trình nghe sự kiện click cho mỗi phần tử <a>
    links.forEach(link => {
        link.addEventListener('click', function() {
            // Lấy class của phần tử <div> chứa phần tử <a> được click
            const parentDivClass = this.parentElement.classList;
            let wrapper = document.querySelector(`.wrapper-multi.${parentDivClass[1]}`);
            let wrapperId = wrapper.id;
            showQuestion(parseInt(wrapperId.split("-")[2]-1));   
              if(currentQuestionIndex === 0){
                backButton.classList.add('d-none');
               }else{
                backButton.classList.remove('d-none');
               }
            // showQuestion();
        });
    });
}
clickPart();
// lấy câu hỏi từ api
let currentQuestionIndex = 0;
function showQuestion(index) {
    const wrappers = document.querySelectorAll('.wrapper-multi');
    const parts = document.querySelectorAll('.part');
   
    for (let i = 0; i < wrappers.length; i++) {
        if (i === index) {
            wrappers[i].style.display = 'block';
            const idWrapper =  wrappers[i].id;
            const questionButton = document.querySelectorAll(`#${idWrapper} .cauhoi`);
            let part = questionButton[0].parentNode;
            let partClass = part.classList;
            parts.forEach(function(part,index){
                if(index == (partClass[1].split("-")[1]-1)){
                    part.classList.add('part-active');
                }else{
                    part.classList.remove('part-active');
                }
            });
            questionButton.forEach(function(question,index){
                questionButtons[question.id.split('-')[1]-1].addClass('active-number-question');
            });
        } else {
            wrappers[i].style.display = 'none';
            // questionButtons[i].removeClass('active-number-question');
            const idWrapper =  wrappers[i].id;
            const questionButton = document.querySelectorAll(`#${idWrapper} .cauhoi`);
            let part = questionButton[0].parentNode;
            let partClass = part.classList;
            // console.log(part);
            // console.log(parts[partClass[1].split("-")[1]-1]);
            // parts[partClass[1].split("-")[1]-1].classList.remove('part-active');
            questionButton.forEach(function(question,index){
                questionButtons[question.id.split('-')[1]-1].removeClass('active-number-question');
            });
        }
    }
    currentQuestionIndex = index;
}
var countQuestion =0;
function getQuestion(){
    var wrapperQuestion = document.getElementById('wrapper-question');
    var countWrapper =0;
    conversation.data.forEach(function(content, index) {
           let part = content.part;
           if(index === 0 || index === 1 || index === 4){
              
              content.questions.forEach(function(question,indexQuestion){
                   var wrapperMulti = document.createElement("div"); 
                   wrapperMulti.classList.add("wrapper-multi", "part-"+part);
                   wrapperMulti.id = "wrapper-multi-" + (countQuestion + 1);

                    var wrapper = document.createElement("div"); 
                    wrapper.id = "wrapper-" + (countWrapper + 1);
                    wrapper.classList.add("cauhoi");
                    // wrapper.style.display='none';
            
                    var headerWrapper = document.createElement("div");
                    headerWrapper.classList.add("wrapper", "text-center");
                    
                    if(question.image !== undefined){
                        var mediaWrapper1 = document.createElement("div");
                        mediaWrapper1.classList.add("media-wrapper");
                        var imageElement = document.createElement("img");
                        imageElement.style.width= '60%';
                        imageElement.classList.add("media-item");
                        imageElement.src=question.image;
                        mediaWrapper1.appendChild(imageElement);
                        headerWrapper.appendChild(mediaWrapper1);
                    }
                   if(question.audioSrc !== undefined){
                    var mediaWrapper2 = document.createElement("div");
                    mediaWrapper2.classList.add("media-wrapper");
                    var audioElement = document.createElement("audio");
                    audioElement.style.height = "50px";
                    audioElement.controls = true;
                    audioElement.classList.add("media-item","my-3");
                    audioElement.innerHTML = '<source src="' + question.audioSrc + '" type="audio/mpeg">';
                    mediaWrapper2.appendChild(audioElement);
                    headerWrapper.appendChild(mediaWrapper2);
                   }
                
                wrapper.appendChild(headerWrapper);
            
                    var questionHeader = document.createElement("h5");
                    questionHeader.classList.add("fw-semi-bold");
                    questionHeader.innerText = "Question " + (countWrapper + 1) + ": " + question.text;
                    wrapper.appendChild(questionHeader);
                question.answers.forEach(function(answer, answerIndex) {
                        var row = document.createElement("div");
                        row.classList.add("row", "mt-3");
            
                        var col = document.createElement("div");
                        col.classList.add("col-md-12");
            
                        var answerOption = document.createElement("div");
                        answerOption.classList.add("answer-option");
            
                        var input = document.createElement("input");
                        input.type = "radio";
                        input.name = "answer" + (countWrapper + 1);
                        input.id = "option" + (countWrapper + 1) + String.fromCharCode(65 + answerIndex);
                        input.classList.add("answer-input");
                        input.value = String.fromCharCode(65 + answerIndex);
            
                        var label = document.createElement("label");
                        label.setAttribute("for", "option" + (countWrapper + 1) + String.fromCharCode(65 + answerIndex));
                        label.classList.add("answer-label");
                        label.innerText = String.fromCharCode(65 + answerIndex);
                        
                        var span = document.createElement("span");
                        span.classList.add("mx-3","span-answer");
                        // span.onclick= selectAnswer(`${input.id}`);
                        span.innerText = answer;
            
                        answerOption.appendChild(input);
                        answerOption.appendChild(label);
                        answerOption.appendChild(span);
            
                        col.appendChild(answerOption);
                        wrapper.appendChild(col);
                        
                });
               
                wrapperMulti.appendChild(wrapper);
                wrapperQuestion.appendChild(wrapperMulti);
                countWrapper++;
                countQuestion++;
              });
           }else{
                content.questions.forEach(function(question,index){
                var wrapper = document.createElement("div"); 
                wrapper.classList.add("wrapper-multi","part-"+part);
                wrapper.id = "wrapper-multi-" + (countQuestion + 1);
                    // wrapper.style.display='none';

                var headerWrapper = document.createElement("div");
                headerWrapper.classList.add("wrapper", "text-center");
                
                if(question.image){
                    var mediaWrapper1 = document.createElement("div");
                    mediaWrapper1.classList.add("media-wrapper");
                    var imageElement = document.createElement("img");
                    imageElement.style.width= '60%';
                    imageElement.classList.add("media-item");
                    imageElement.src=question.image;
                    mediaWrapper1.appendChild(imageElement);
                    headerWrapper.appendChild(mediaWrapper1);
                }

                if(question.audioSrc){
                        var mediaWrapper2 = document.createElement("div");
                        mediaWrapper2.classList.add("media-wrapper");
                        var audioElement = document.createElement("audio");
                        audioElement.style.height = "50px";
                        audioElement.controls = true;
                        audioElement.classList.add("media-item","my-3");
                        audioElement.innerHTML = '<source src="' + question.audioSrc + '" type="audio/mpeg">';
                        mediaWrapper2.appendChild(audioElement);
                        headerWrapper.appendChild(mediaWrapper2);           
                }
                wrapper.appendChild(headerWrapper);    
                if(question.description){
                    var description = document.createElement("div");
                    description.classList.add("alert", "alert-warning","answer-content","text-justify");
                    description.innerHTML = question.description;
                    wrapper.appendChild(description);
                }    
                
                question.questionsChildren.forEach(function(questionChild,index){
                    var wrapperQuestionMulti = document.createElement("div"); 
                    wrapperQuestionMulti.id = "wrapper-" + (countWrapper + 1);
                    wrapperQuestionMulti.classList.add("cauhoi");
                // tên câu hỏi
                    var questionHeader = document.createElement("h5");
                    questionHeader.classList.add("fw-semi-bold");
                
                    if(questionChild.text === undefined){
                        questionHeader.innerText = "Question " + (countWrapper + 1) + ": ";
                    }else{
                        questionHeader.innerText = "Question " + (countWrapper + 1) + ": " + questionChild.text;
                    }
                    
                    wrapperQuestionMulti.appendChild(questionHeader);
                    if(questionChild.answers){
                        questionChild.answers.forEach(function(answer, answerIndex) {
                            var row = document.createElement("div");
                            row.classList.add("row", "mt-3");
                
                            var col = document.createElement("div");
                            col.classList.add("col-md-12");
                
                            var answerOption = document.createElement("div");
                            answerOption.classList.add("answer-option");
                
                            var input = document.createElement("input");
                            input.type = "radio";
                            input.name = "answer" + (countWrapper + 1);
                            input.id = "option" + (countWrapper + 1) + String.fromCharCode(65 + answerIndex);
                            input.classList.add("answer-input");
                            input.value = String.fromCharCode(65 + answerIndex);
                
                            var label = document.createElement("label");
                            label.setAttribute("for", "option" + (countWrapper + 1) + String.fromCharCode(65 + answerIndex));
                            label.classList.add("answer-label");
                            label.innerText = String.fromCharCode(65 + answerIndex);
                            
                            var span = document.createElement("span");
                            span.classList.add("mx-3","span-answer");
                            span.innerText = answer;

                            answerOption.appendChild(input);
                            answerOption.appendChild(label);
                            answerOption.appendChild(span);

                            col.appendChild(answerOption);
                            wrapperQuestionMulti.appendChild(col);

                    });
                    countWrapper++;
                    wrapper.appendChild(wrapperQuestionMulti);
                    }
                });
                    
                wrapperQuestion.appendChild(wrapper);
                countQuestion++;
                });
                
                // bọc các câu hỏi
                
                
           }
       
    });

    showQuestion(currentQuestionIndex);
    clickButtonBackAndNext();

}
getQuestion();

function clickButtonBackAndNext(){
    const backButton = document.getElementById('backButton');
    const nextButton = document.getElementById('nextButton');

    if(currentQuestionIndex === 0){
     backButton.classList.add('d-none');
    }
    backButton.addEventListener('click', function() {
        if (currentQuestionIndex > 0) {
            currentQuestionIndex--;
            showQuestion(currentQuestionIndex);
        }
        if (currentQuestionIndex === 0) {
         backButton.classList.add('d-none');
     } else {
         backButton.classList.remove('d-none');
     }
 
     if (currentQuestionIndex === countQuestion - 1) {
         nextButton.classList.add('d-none');
     } else {
         nextButton.classList.remove('d-none');
     }
  
    });

    nextButton.addEventListener('click', function() {
        if (currentQuestionIndex < countQuestion - 1) {
            currentQuestionIndex++;
            showQuestion(currentQuestionIndex);
        }
     if (currentQuestionIndex === 0) {
         backButton.classList.add('d-none');
     } else {
         backButton.classList.remove('d-none');
     }
 
     if (currentQuestionIndex === countQuestion - 1) {
         nextButton.classList.add('d-none');
     } else {
         nextButton.classList.remove('d-none');
     }
;
    });
 
}


// Lắng nghe sự kiện click cho input hoặc label trong phần tử .answer-option của mỗi câu hỏi
$('.answer-option input[type="radio"], .answer-option label').on('click', function(event) {
    // Ngăn chặn sự kiện click lan sang các phần tử cha khác của .answer-option
    event.stopPropagation();

    // Lấy ra phần tử .answer-option chứa input hoặc label được click
    const answerOption = $(this).closest('.answer-option');
    const wrapper = answerOption.closest('.cauhoi');
    const checkedRadio = wrapper.find('input[type="radio"]:checked');
    if (checkedRadio.length > 0) {
        const idWrapper =  wrapper[0].id;
        questionButtons[idWrapper.split('-')[1]-1].addClass('answered');
    } else {
        questionButtons[idWrapper.split('-')[1]-1].removeClass('answered');
    }
});

// Lắng nghe sự kiện click cho label trong phần tử .answer-option của mỗi câu hỏi
$('.answer-option .span-answer').on('click', function(event) {
    // Lấy ra id của input được liên kết với label
    const inputId = $(this).siblings('input[type="radio"]').attr('id');
    const input = $(`input#${inputId}`);

     input.click();
});

// lấy thời gian từ api
var countdownInterval;
function getTime(){
    var countdownSpan = document.getElementById('countdown');
var totalSeconds = conversation.time * 60; // Chuyển đổi thời gian từ phút sang giây

  countdownInterval = setInterval(function() {
  var hours = Math.floor(totalSeconds / 3600);
  var minutes = Math.floor((totalSeconds % 3600) / 60);
  var seconds = totalSeconds % 60;

  // Thêm số 0 vào trước nếu số nhỏ hơn 10
  hours = hours < 10 ? '0' + hours : hours;
  minutes = minutes < 10 ? '0' + minutes : minutes;
  seconds = seconds < 10 ? '0' + seconds : seconds;

  countdownSpan.textContent = hours + ':' + minutes + ':' + seconds;

  totalSeconds--;

  // Nếu thời gian đã hết, dừng đếm ngược
  if (totalSeconds < 0) {
    clearInterval(countdownInterval);
    countdownSpan.textContent = '00:00:00';
    getScore();
  }
}, 1000); // Cập nhật mỗi giây (1000 mili giây)
}
getTime();

document.addEventListener('DOMContentLoaded', function() {
    var spans = document.querySelectorAll('.span-answer');
    // console.log(spans);
    spans.forEach(function(span) {
      span.onclick = function() {
        var inputId = this.previousElementSibling.getAttribute('for');
        selectAnswer(inputId);
      };
    });
  });

  function selectAnswer(optionId) {
    document.getElementById(optionId).checked = true;
  }


// chấm điểm
function getScore(){
   
       var numberCorrectListening =0;
       var numberCorrectReading =0;
       var indexQuestion =0;
       conversation.data.forEach(function(content, indexTong) {
        if(indexTong === 0 || indexTong === 1 || indexTong === 4){
           content.questions.forEach(function(question,indexQ){
            indexQuestion++;
            var selectedAnswer = document.querySelector('input[name="answer' + (indexQuestion) + '"]:checked');
           
            if (selectedAnswer) {

                var correctAnswerIndex = question.correctAnswerIndex;
                var correctAnswer = String.fromCharCode(65 + correctAnswerIndex);
                var parentDiv = selectedAnswer.closest(".answer-option");
                var label = parentDiv.querySelector("label");
                

                if (selectedAnswer.value === correctAnswer) {
                    label.classList.add("correct"); // Thêm class "correct" nếu đáp án đúng
                    var correctSpan = document.createElement('span');
                    correctSpan.classList.add('result-icon-correct');
                    correctSpan.innerHTML = '<i class="fas fa-check"></i>';
                    parentDiv.appendChild(correctSpan);
                    if(indexTong === 0 || indexTong === 1 || indexTong === 2 || indexTong === 3){
                        numberCorrectListening++;
                    }else{
                        numberCorrectReading++;
                    }
                    questionButtons[indexQuestion-1].removeClass('answered');
                    questionButtons[indexQuestion-1].addClass('answered-score-correct');
                } else {
                    label.classList.add("incorrect"); // Thêm class "incorrect" nếu đáp án sai
                    var incorrectSpan = document.createElement('span');
                    incorrectSpan.classList.add('result-icon-incorrect');
                    incorrectSpan.innerHTML = '<i class="fas fa-times"></i>';
                    parentDiv.appendChild(incorrectSpan);
                    questionButtons[indexQuestion-1].removeClass('answered');
                    questionButtons[indexQuestion-1].addClass('answered-score-incorrect');
                }
            }else{
                // alert("Vui lòng hoàn thành đáp án trước khi kiểm tra đáp án!");
            }
            });
         }else{
            content.questions.forEach(function(question,index){
                question.questionsChildren.forEach(function(questionChild,index){
                    indexQuestion++;
            var selectedAnswer = document.querySelector('input[name="answer' + (indexQuestion) + '"]:checked');
           
            if (selectedAnswer) {

                var correctAnswerIndex = questionChild.correctAnswerIndex;
                var correctAnswer = String.fromCharCode(65 + correctAnswerIndex);
                var parentDiv = selectedAnswer.closest(".answer-option");
                var label = parentDiv.querySelector("label");
                

                if (selectedAnswer.value === correctAnswer) {
                    label.classList.add("correct"); // Thêm class "correct" nếu đáp án đúng
                    var correctSpan = document.createElement('span');
                    correctSpan.classList.add('result-icon-correct');
                    correctSpan.innerHTML = '<i class="fas fa-check"></i>';
                    parentDiv.appendChild(correctSpan);
                    if(indexTong === 0 || indexTong === 1 || indexTong === 2 || indexTong === 3){
                        numberCorrectListening++;
                    }else{
                        numberCorrectReading++;
                    }
                    questionButtons[indexQuestion-1].removeClass('answered');
                    questionButtons[indexQuestion-1].addClass('answered-score-correct');
                } else {
                    label.classList.add("incorrect"); // Thêm class "incorrect" nếu đáp án sai
                    var incorrectSpan = document.createElement('span');
                    incorrectSpan.classList.add('result-icon-incorrect');
                    incorrectSpan.innerHTML = '<i class="fas fa-times"></i>';
                    parentDiv.appendChild(incorrectSpan);
                    questionButtons[indexQuestion-1].removeClass('answered');
                    questionButtons[indexQuestion-1].addClass('answered-score-incorrect');
                }
            }else{
                // alert("Vui lòng hoàn thành đáp án trước khi kiểm tra đáp án!");
            }
                });
            });
         }
        });
         disableInputsAndLabels();
         removeActiveQuestionNumber();
         clearInterval(countdownInterval);
         displayDescription();
        var submitButton = document.getElementById("submitButton");
        var submitButtonContainer= submitButton.parentNode;
        submitButtonContainer.parentNode.removeChild(submitButtonContainer);
        var result = document.getElementById('result');
        result.innerText= 'Kết quả: ' + (numberCorrectListening+numberCorrectReading) + "/" + totalQuestion;

        // hiện thị thông tin trong modal
            const scoreListening = document.getElementById('score-listening');
            const scoreReading = document.getElementById('score-reading');
            const resultNumber = document.getElementById('result-number');
            const totalScore = document.getElementById('total-score');

            let scorePartReading = 0;
            let scorePartListening = 0;
            if(numberCorrectListening ===1){
                scorePartListening = 15;
            } else if(numberCorrectListening >1 && numberCorrectListening <= 75){
                scorePartListening = numberCorrectListening * 5+10;
            } else if(numberCorrectListening >75 && numberCorrectListening <= 95){
                scorePartListening = numberCorrectListening * 5 +15;
            } else if(numberCorrectListening >= 96){
                scorePartListening = 495;
            }
            console.log(numberCorrectListening);
            console.log(numberCorrectReading);
            if(numberCorrectReading ===1){
                scorePartReading =5;
            }else if(numberCorrectReading > 1){
                scorePartReading = numberCorrectReading* 5-5;
            }
            console.log(scoreListening);
            console.log(scoreReading);
            scoreListening.innerText=scorePartListening;
            scoreReading.innerText=scorePartReading;
            resultNumber.innerText = (numberCorrectListening+numberCorrectReading) + "/" + totalQuestion;
            totalScore.innerText = (scorePartListening+scorePartReading);
            $('#scoreModal').modal('show');
        
}

function submitButton(){
    var submitButton = document.getElementById("submitButton");
    submitButton.addEventListener("click", function(){
          getScore();
    });
    
}
submitButton();


function displayDescription(){
    const answerContent = document.querySelectorAll('.wrapper-multi .answer-content');
    answerContent.forEach(function(answer){
        answer.classList.remove('d-none');
    });
}
function disableInputsAndLabels() {
    var inputs = document.querySelectorAll('.answer-option input');
    var labels = document.querySelectorAll('.answer-option label');
    var spans = document.querySelectorAll('.answer-option span');

    inputs.forEach(function(input) {
        input.disabled = true; // Vô hiệu hóa các input
    });
    labels.forEach(function(label) {
        label.classList.add("disabled"); // Thêm class disabled cho các label để ẩn hiển thị
    });
    spans.forEach(function(span) {
        span.classList.add("disabled"); // Thêm class disabled cho các span để ẩn hiển thị
    });

}



