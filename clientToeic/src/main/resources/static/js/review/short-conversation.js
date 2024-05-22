document.getElementById('reloadButton').addEventListener('click', () => {
    window.location.reload();
  });
  

// window.onbeforeunload = function() {
//     return "Bạn có chắc chắn muốn rời khỏi trang này?";
//   };
  
var conversation = {
    time:20,
    questions:[
                {
                    audioSrc: "https://bd748f642cf8b253d59c-5c160b94f727c0d27cbeccc854542bc6.ssl.cf1.rackcdn.com/LearnEnglish%20Teens%20-%20Listening%20skills%20practice%20-%20A1%20-%20A%20good%20night's%20sleep.mp3",
                    description:"M: Ms. Martin, I really think that the photos from the weekend rugby match should be included in this weekend's sports section.",
                    name: "Milky Cookies....... enters into a contract with a third party vendor in order to fulfill its business operations.",
                    answers: ["occasionally", "prematurely", "marginally","uncommonly"],
                    correctAnswerIndex: 3
                },
                {
                    audioSrc: "https://bd748f642cf8b253d59c-5c160b94f727c0d27cbeccc854542bc6.ssl.cf1.rackcdn.com/LearnEnglish%20Teens%20-%20Listening%20skills%20practice%20-%20A1%20-%20A%20good%20night's%20sleep.mp3",
                    description:"M: Ms. Martin, I really think that the photos from the weekend rugby match should be included in this weekend's sports section.",
                    name: "We want you to be aware that we’re doing our utmost,------ we will not be able to provide you with a personalized experience on our website.",
                    answers: ["in spite of", "so as", "despite","though"],
                    correctAnswerIndex: 1
                },
                {
                    audioSrc: "https://bd748f642cf8b253d59c-5c160b94f727c0d27cbeccc854542bc6.ssl.cf1.rackcdn.com/LearnEnglish%20Teens%20-%20Listening%20skills%20practice%20-%20A1%20-%20A%20good%20night's%20sleep.mp3",
                    description:"M: Ms. Martin, I really think that the photos from the weekend rugby match should be included in this weekend's sports section.",
                    name: "Mr. Conner’s remarks concerning the results of last year’s declining sales were concise and to the .............",
                    answers: ["grade", "feet", "point","spot"],
                    correctAnswerIndex: 2
                },
                {
                    audioSrc: "https://bd748f642cf8b253d59c-5c160b94f727c0d27cbeccc854542bc6.ssl.cf1.rackcdn.com/LearnEnglish%20Teens%20-%20Listening%20skills%20practice%20-%20A1%20-%20A%20good%20night's%20sleep.mp3",
                    description:"M: Ms. Martin, I really think that the photos from the weekend rugby match should be included in this weekend's sports section.",
                    name: "Milky Cookies....... enters into a contract with a third party vendor in order to fulfill its business operations.",
                    answers: ["occasionally", "prematurely", "marginally","uncommonly"],
                    correctAnswerIndex: 3
                }
            ]
};


let questionButtons = [];
function createButtonAnswer(){
    const backButton = document.getElementById('backButton');
    const nextButton = document.getElementById('nextButton');

    const questionCount = conversation.questions.length;
    const $numberQuestion = $('#number-question');
    $numberQuestion.empty(); // Xóa các nút hiện có

    let buttonCount = 0;
    const rowCapacity = 6; // Số lượng div col trong mỗi hàng
    let rowCounter = 0; // Đếm số lượng hàng
    let $row = $('<div>').addClass('row');
    for (let i = 0; i <questionCount; i++) {
        const $div = $('<div>').addClass('divElementA col-2');

        const $button = $('<a>')
          .addClass('btn btn-outline-secondary rounded-circle mx-1 my-1')
          .attr('href', '#')
          .text(i+1);
        $div.append($button)  
        $button.on('click', function() {
            showQuestion(i);
            if(currentQuestionIndex === 0){
                backButton.classList.add('d-none');
            }else{
                backButton.classList.remove('d-none');

            }
            if(currentQuestionIndex === questionCount - 1){
                nextButton.classList.add('d-none');
            }else{
                nextButton.classList.remove('d-none');
            }   
          });  
          $row.append($div);
          buttonCount++;
      
        //   if (buttonCount % 6 === 0 || i === questionCount - 1) {
        //     $numberQuestion.append($row);
        //     $row = $('<div>').addClass('row');
        //     buttonCount = 0;
        //   }
      
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
    const wrappers = document.querySelectorAll('.cauhoi');
    
    for (let i = 0; i < wrappers.length; i++) {
        if (i === index) {
            wrappers[i].style.display = 'block';
            questionButtons[i].addClass('active-number-question');

        } else {
            wrappers[i].style.display = 'none';
            questionButtons[i].removeClass('active-number-question');
        }
    }
    currentQuestionIndex = index;
}

function getQuestion(){
    var wrapperQuestion = document.getElementById('wrapper-question');
  
    conversation.questions.forEach(function(question, index) {
       
        var wrapper = document.createElement("div"); 
        wrapper.id = "wrapper-" + (index + 1);
        wrapper.classList.add("cauhoi");
        // wrapper.style.display='none';

        var headerWrapper = document.createElement("div");
        headerWrapper.classList.add("wrapper", "text-center");
    
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

       var description = document.createElement("div");
       description.classList.add("alert", "alert-warning","answer-content","d-none");
       description.innerHTML = question.description;
       wrapper.appendChild(description);


        var questionHeader = document.createElement("h5");
        questionHeader.classList.add("fw-semi-bold");
        questionHeader.innerText = "Question " + (index + 1) + ": " + question.name;
        wrapper.appendChild(questionHeader);


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
                input.name = "answer" + (index + 1);
                input.id = "option" + (index + 1) + String.fromCharCode(65 + answerIndex);
                input.classList.add("answer-input");
                input.value = String.fromCharCode(65 + answerIndex);
    
                var label = document.createElement("label");
                label.setAttribute("for", "option" + (index + 1) + String.fromCharCode(65 + answerIndex));
                label.classList.add("answer-label");
                label.innerText = String.fromCharCode(65 + answerIndex);
                
                span = document.createElement("span");
                span.classList.add("mx-3","span-answer");
                span.innerText = answer;
               
                answerOption.appendChild(input);
                answerOption.appendChild(label);           
                answerOption.appendChild(span);

                col.appendChild(answerOption);
                wrapper.appendChild(col);
           });
        }
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
 
     if (currentQuestionIndex === conversation.questions.length - 1) {
         nextButton.classList.add('d-none');
     } else {
         nextButton.classList.remove('d-none');
     }
     const selectedAnswers = document.querySelectorAll('.cauhoi')[currentQuestionIndex].querySelectorAll('input[type="radio"]:checked');
    });

    nextButton.addEventListener('click', function() {
        if (currentQuestionIndex < conversation.questions.length - 1) {
            currentQuestionIndex++;
            showQuestion(currentQuestionIndex);
        }
     if (currentQuestionIndex === 0) {
         backButton.classList.add('d-none');
     } else {
         backButton.classList.remove('d-none');
     }
 
     if (currentQuestionIndex === conversation.questions.length - 1) {
         nextButton.classList.add('d-none');
     } else {
         nextButton.classList.remove('d-none');
     }

    });
}

// Lắng nghe sự kiện click cho input hoặc label trong phần tử .answer-option của mỗi câu hỏi
$('.answer-option input[type="radio"], .answer-option label').on('click', function(event) {
    // Ngăn chặn sự kiện click lan sang các phần tử cha khác của .answer-option
    event.stopPropagation();

    // Lấy ra phần tử .answer-option chứa input hoặc label được click
    const answerOption = $(this).closest('.answer-option');

    // Kiểm tra xem người dùng đã chọn đáp án hay chưa
    const checkedRadio = answerOption.find('input[type="radio"]:checked');
    if (checkedRadio.length > 0) {
        // Nếu có đáp án được chọn, thực hiện xử lý tương ứng (ví dụ: log giá trị đáp án)
        questionButtons[currentQuestionIndex].addClass('answered');
    } else {
        // Nếu không có đáp án được chọn, bạn có thể xử lý tùy thuộc vào yêu cầu của bạn (ví dụ: hiển thị thông báo)
        questionButtons[currentQuestionIndex].removeClass('answered');
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
        conversation.questions.forEach(function(question, index) {
            var selectedAnswer = document.querySelector('input[name="answer' + (index + 1) + '"]:checked');
            var correctAnswerIndex = question.correctAnswerIndex;
            var correctAnswer = String.fromCharCode(65 + (correctAnswerIndex-1));
            
            if (selectedAnswer) {
                var parentDiv = selectedAnswer.closest(".answer-option");
                var label = parentDiv.querySelector("label");
                if (selectedAnswer.value === correctAnswer) {
                    label.classList.add("correct"); // Thêm class "correct" nếu đáp án đúng
                    var correctSpan = document.createElement('span');
                    correctSpan.classList.add('result-icon-correct');
                    correctSpan.innerHTML = '<i class="fas fa-check"></i>';
                    parentDiv.appendChild(correctSpan);
                    numberCorrect++;
                    questionButtons[index].removeClass('answered');
                    questionButtons[index].addClass('answered-score-correct');
                   
                } else {
                    label.classList.add("incorrect"); // Thêm class "incorrect" nếu đáp án sai
                    var incorrectSpan = document.createElement('span');
                    incorrectSpan.classList.add('result-icon-incorrect');
                    incorrectSpan.innerHTML = '<i class="fas fa-times"></i>';
                    parentDiv.appendChild(incorrectSpan);
                    questionButtons[index].removeClass('answered');
                    questionButtons[index].addClass('answered-score-incorrect');
                 
                }
            }else{
                // alert("Vui lòng hoàn thành đáp án trước khi kiểm tra đáp án!");
                // var unansweredQuestions = document.querySelectorAll('input[name="answer' + (index + 1) + '"]:not(:checked)');
                // unansweredQuestions.forEach(function(unansweredQuestion) {
                // var parentDiv = unansweredQuestion.closest(".answer-option");
                // var correctAnswerIndex = conversation.questions[index].correctAnswerIndex;
                // var correctAnswer = String.fromCharCode(65 + correctAnswerIndex);
                // var label = parentDiv.querySelector("label");
                // if (correctAnswer === unansweredQuestion.value) {
                //     label.classList.add("correct");
                //     var correctSpan = document.createElement('span');
                //     correctSpan.classList.add('result-icon-correct');
                //     correctSpan.innerHTML = '<i class="fas fa-check"></i>';
                //     parentDiv.appendChild(correctSpan);
                // } 
                // });
            }
            disableInputsAndLabels();
        });
    
        removeActiveQuestionNumber();
    
        var submitButton = document.getElementById("submitButton");
        var submitButtonContainer= submitButton.parentNode;
        submitButtonContainer.parentNode.removeChild(submitButtonContainer);
        
}


function submitButton(){
    var submitButton = document.getElementById("submitButton");
    var descriptions = document.querySelectorAll(".answer-content");
    submitButton.addEventListener("click", function(){
         getScore();
         descriptions.forEach(function(description) {
            description.classList.remove("d-none");
        });
    });
    
}
submitButton();

// nút hiển thị đáp án




// hết thời gian tự submit bài làm

 

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



