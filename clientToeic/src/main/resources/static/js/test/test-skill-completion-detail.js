
// window.onbeforeunload = function() {
//     return "Bạn có chắc chắn muốn rời khỏi trang này?";
//   };
  
var conversation = {
    time:20,
    data: [
        {
            description:"Notice to employment advertisers and job seekers Misleading advertisements placed in the employment (1)........ of our newspaper can result in hardship and time wasted by those who are looking for jobs, and this is certainly unacceptable. Placing false ads that are misleading is an offence against the Trade Practices and Fair Trade Act. Any (2)........ wishing to place a want ad with us should keep in mind that all advertisements should include a job title, a clear (3)........ of the job, and the income basis and be placed under the appropriate category For further information, contact the Department of Fair Trade at 755-5720 (4)........ business hours.",
            questions:[
                {
                    answers: ["area", "section", "branch","department"],
                    correctAnswerIndex: 1
                },
                {
                    answers: ["employees", "employs", "employment","employer"],
                    correctAnswerIndex: 2
                },
                {
                    answers: ["information", "subscription", "description","requirement"],
                    correctAnswerIndex: 1
                },
                {
                    answers: ["during", "dured", "dures","durring"],
                    correctAnswerIndex: 2
                }
            ]
        },
        {
            description:"Notice to employment advertisers and job seekers Misleading advertisements placed in the employment (1)........ of our newspaper can result in hardship and time wasted by those who are looking for jobs, and this is certainly unacceptable. Placing false ads that are misleading is an offence against the Trade Practices and Fair Trade Act. Any (2)........ wishing to place a want ad with us should keep in mind that all advertisements should include a job title, a clear (3)........ of the job, and the income basis and be placed under the appropriate category For further information, contact the Department of Fair Trade at 755-5720 (4)........ business hours.",
            questions:[
                {
                    answers: ["area", "section", "branch","department"],
                    correctAnswerIndex: 1
                },
                {
                    answers: ["employees", "employs", "employment","employer"],
                    correctAnswerIndex: 2
                },
                {
                    answers: ["information", "subscription", "description","requirement"],
                    correctAnswerIndex: 1
                },
                {
                    answers: ["during", "dured", "dures","durring"],
                    correctAnswerIndex: 2
                }
            ]
        },
        {
            description:"Notice to employment advertisers and job seekers Misleading advertisements placed in the employment (1)........ of our newspaper can result in hardship and time wasted by those who are looking for jobs, and this is certainly unacceptable. Placing false ads that are misleading is an offence against the Trade Practices and Fair Trade Act. Any (2)........ wishing to place a want ad with us should keep in mind that all advertisements should include a job title, a clear (3)........ of the job, and the income basis and be placed under the appropriate category For further information, contact the Department of Fair Trade at 755-5720 (4)........ business hours.",
            questions:[
                {
                    answers: ["area", "section", "branch","department"],
                    correctAnswerIndex: 1
                },
                {
                    answers: ["employees", "employs", "employment","employer"],
                    correctAnswerIndex: 2
                },
                {
                    answers: ["information", "subscription", "description","requirement"],
                    correctAnswerIndex: 1
                },
                {
                    answers: ["during", "dured", "dures","durring"],
                    correctAnswerIndex: 2
                }
            ]
        },
        {
            description:"Notice to employment advertisers and job seekers Misleading advertisements placed in the employment (1)........ of our newspaper can result in hardship and time wasted by those who are looking for jobs, and this is certainly unacceptable. Placing false ads that are misleading is an offence against the Trade Practices and Fair Trade Act. Any (2)........ wishing to place a want ad with us should keep in mind that all advertisements should include a job title, a clear (3)........ of the job, and the income basis and be placed under the appropriate category For further information, contact the Department of Fair Trade at 755-5720 (4)........ business hours.",
            questions:[
                {
                    answers: ["area", "section", "branch","department"],
                    correctAnswerIndex: 1
                },
                {
                    answers: ["employees", "employs", "employment","employer"],
                    correctAnswerIndex: 2
                },
                {
                    answers: ["information", "subscription", "description","requirement"],
                    correctAnswerIndex: 1
                },
                {
                    answers: ["during", "dured", "dures","durring"],
                    correctAnswerIndex: 2
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

   
    conversation.data.forEach(function(question,index){
        totalQuestion += question.questions.length;
    });
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
            if(currentQuestionIndex === totalQuestion - 1){
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

// lấy câu hỏi từ api
let currentQuestionIndex = 0;
function showQuestion(index) {
    const wrappers = document.querySelectorAll('.wrapper-multi');
    for (let i = 0; i < wrappers.length; i++) {
        if (i === index) {
            wrappers[i].style.display = 'block';
            const idWrapper =  wrappers[i].id;
            const questionButton = document.querySelectorAll(`#${idWrapper} .cauhoi`);
            questionButton.forEach(function(question,index){
                questionButtons[question.id.split('-')[1]-1].addClass('active-number-question');
            });
        } else {
            wrappers[i].style.display = 'none';
            // questionButtons[i].removeClass('active-number-question');
            const idWrapper =  wrappers[i].id;
            const questionButton = document.querySelectorAll(`#${idWrapper} .cauhoi`);
            questionButton.forEach(function(question,index){
                questionButtons[question.id.split('-')[1]-1].removeClass('active-number-question');
            });
        }
    }
    currentQuestionIndex = index;
}

function getQuestion(){
    var wrapperQuestion = document.getElementById('wrapper-question');
    var countQuestion =0;
    conversation.data.forEach(function(content, index) {
       
        var wrapper = document.createElement("div"); 
        wrapper.classList.add("wrapper-multi");
        wrapper.id = "wrapper-multi-" + (index + 1);
        // wrapper.style.display='none';

       var description = document.createElement("div");
       description.classList.add("alert", "alert-warning","answer-content","text-justify");
        description.innerHTML = content.description;
        wrapper.appendChild(description);

       // bọc các câu hỏi
       
       content.questions.forEach(function(question,index){
                var wrapperQuestionMulti = document.createElement("div"); 
                wrapperQuestionMulti.id = "wrapper-" + (countQuestion + 1);
                wrapperQuestionMulti.classList.add("cauhoi");
               // tên câu hỏi
                var questionHeader = document.createElement("h5");
                questionHeader.classList.add("fw-semi-bold");
            
                if(question.text === undefined){
                    questionHeader.innerText = "Question " + (countQuestion + 1) + ": ";
                }else{
                    questionHeader.innerText = "Question " + (countQuestion + 1) + ": " + question.text;
                }
                
                wrapperQuestionMulti.appendChild(questionHeader);
                if(question.answers){
                    question.answers.forEach(function(answer, answerIndex) {
                        var row = document.createElement("div");
                        row.classList.add("row", "mt-3");
            
                        var col = document.createElement("div");
                        col.classList.add("col-md-12");
            
                        var answerOption = document.createElement("div");
                        answerOption.classList.add("answer-option");
            
                        var input = document.createElement("input");
                        input.type = "radio";
                        input.name = "answer" + (countQuestion + 1);
                        input.id = "option" + (countQuestion + 1) + String.fromCharCode(65 + answerIndex);
                        input.classList.add("answer-input");
                        input.value = String.fromCharCode(65 + answerIndex);
            
                        var label = document.createElement("label");
                        label.setAttribute("for", "option" + (countQuestion + 1) + String.fromCharCode(65 + answerIndex));
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
                countQuestion++;
                wrapper.appendChild(wrapperQuestionMulti);
                }
            });
                
            wrapperQuestion.appendChild(wrapper);
       
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
 
     if (currentQuestionIndex === conversation.data.length - 1) {
         nextButton.classList.add('d-none');
     } else {
         nextButton.classList.remove('d-none');
     }
  
    });

    nextButton.addEventListener('click', function() {
        if (currentQuestionIndex < conversation.data.length - 1) {
            currentQuestionIndex++;
            showQuestion(currentQuestionIndex);
        }
     if (currentQuestionIndex === 0) {
         backButton.classList.add('d-none');
     } else {
         backButton.classList.remove('d-none');
     }
 
     if (currentQuestionIndex === conversation.data.length - 1) {
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

// // hiển thị lại question và các đáp án
// function showQuestionAndAnswer(){
//     conversation.questions.forEach(function(question,index){
//         var wrapper = document.getElementById('wrapper-'+(index+1));
//         var h5 = wrapper.querySelector('h5');
//         h5.innerText = "Question: " + (index+1) + ": " + question.text;
       
       
//         question.answers.forEach(function(answer,indexAnswer){
//             var input = document.querySelector("#option" + (index + 1) + String.fromCharCode(65 + indexAnswer));
//             span = document.createElement("span");
//             span.classList.add("mx-3","span-answer");
//             span.innerText = answer;
//             input.parentNode.appendChild(span);
             
//         });
       
//     });
// }

// chấm điểm
function getScore(){
   
       var numberCorrect =0;
       var indexQuestion =0;
        conversation.data.forEach(function(datas, index) {
            // question ở đây là chứa nhiều question
            datas.questions.forEach(function(question,index){
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
                        numberCorrect++;
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
         disableInputsAndLabels();
         removeActiveQuestionNumber();
         clearInterval(countdownInterval);
         displayDescription();
        var submitButton = document.getElementById("submitButton");
        var submitButtonContainer= submitButton.parentNode;
        submitButtonContainer.parentNode.removeChild(submitButtonContainer);
        var result = document.getElementById('result');
        result.innerText= 'Kết quả: ' + numberCorrect + "/" + totalQuestion;

        // hiện thị thông tin trong modal
            const scoreListening = document.getElementById('score-listening');
            const scoreReading = document.getElementById('score-reading');
            const resultNumber = document.getElementById('result-number');
            const totalScore = document.getElementById('total-score');

            let score = 0;
            if(numberCorrect ===1){
                score=5;
            }else if(numberCorrect >1){
                score=numberCorrect*5-5;
            }
            scoreListening.innerText=score;
            resultNumber.innerText = numberCorrect + "/" + totalQuestion;
            totalScore.innerText = score;
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



