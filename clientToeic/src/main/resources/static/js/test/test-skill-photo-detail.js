var conversation = {
    time:2,
    questions: [
        {
            image:'https://learnenglishteens.britishcouncil.org/sites/teens/files/styles/max_2600x2600/public/field/image/RS9182_GettyImages-1224983587-hig.jpg?itok=oie_RJZL',
            audioSrc: "https://bd748f642cf8b253d59c-5c160b94f727c0d27cbeccc854542bc6.ssl.cf1.rackcdn.com/LearnEnglish%20Teens%20-%20Listening%20skills%20practice%20-%20A1%20-%20A%20good%20night's%20sleep.mp3",
            text: "W: Will you help me move my desk by the window?",
            answers: ["Yes", "No", "Maybe","A hoặc B"],
            correctAnswerIndex: 3
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
            correctAnswerIndex: 2
        },
        {
            image:'https://learnenglishteens.britishcouncil.org/sites/teens/files/styles/max_2600x2600/public/field/image/RS9182_GettyImages-1224983587-hig.jpg?itok=oie_RJZL',
            audioSrc: "https://bd748f642cf8b253d59c-5c160b94f727c0d27cbeccc854542bc6.ssl.cf1.rackcdn.com/LearnEnglish%20Teens%20-%20Listening%20skills%20practice%20-%20A1%20-%20A%20good%20night's%20sleep.mp3",
            text: "M: Chair by the desk, desk by the window. Anything else to move?",
            answers: ["Yes", "No", "Maybe"],
            correctAnswerIndex: 1
        },
        {
            image:'https://learnenglishteens.britishcouncil.org/sites/teens/files/styles/max_2600x2600/public/field/image/RS9182_GettyImages-1224983587-hig.jpg?itok=oie_RJZL',
            audioSrc: "https://bd748f642cf8b253d59c-5c160b94f727c0d27cbeccc854542bc6.ssl.cf1.rackcdn.com/LearnEnglish%20Teens%20-%20Listening%20skills%20practice%20-%20A1%20-%20A%20good%20night's%20sleep.mp3",
            text: "M: Chair by the desk, desk by the window. Anything else to move?",
            answers: ["Yes", "No", "Maybe"],
            correctAnswerIndex: 1
        },
        {
            image:'https://learnenglishteens.britishcouncil.org/sites/teens/files/styles/max_2600x2600/public/field/image/RS9182_GettyImages-1224983587-hig.jpg?itok=oie_RJZL',
            audioSrc: "https://bd748f642cf8b253d59c-5c160b94f727c0d27cbeccc854542bc6.ssl.cf1.rackcdn.com/LearnEnglish%20Teens%20-%20Listening%20skills%20practice%20-%20A1%20-%20A%20good%20night's%20sleep.mp3",
            text: "M: Chair by the desk, desk by the window. Anything else to move?",
            answers: ["Yes", "No", "Maybe"],
            correctAnswerIndex: 1
        }
    ]
};


let questionButtons = [];
function createButtonAnswer(){
    const backButton = document.getElementById('backButton');
    const nextButton = document.getElementById('nextButton');

   
    const questionCount = conversation.questions.length;
    const $numberQuestion = $('#number-question');
    for (let i = 0; i < questionCount; i++) {
        const $button = $('<a>')
          .addClass('btn btn-outline-secondary rounded-circle mx-2 my-2')
          .attr('href', '#')
          .text(i+1);

        $button.on('click', function() {
            showQuestion(i);
            if(currentQuestionIndex === 0){
                backButton.classList.add('d-none');
            }else{
                backButton.classList.remove('d-none');

            }
            if(currentQuestionIndex === conversation.questions.length - 1){
                nextButton.classList.add('d-none');
            }else{
                nextButton.classList.remove('d-none');
            }   
          });  
        $numberQuestion.append($button);
        questionButtons.push($button);
      }
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

        var mediaWrapper1 = document.createElement("div");
        mediaWrapper1.classList.add("media-wrapper");
        var imageElement = document.createElement("img");
        imageElement.style.width= '60%';
        imageElement.classList.add("media-item");
        imageElement.src=question.image;
        mediaWrapper1.appendChild(imageElement);
        headerWrapper.appendChild(mediaWrapper1);
    
        var mediaWrapper2 = document.createElement("div");
        mediaWrapper2.classList.add("media-wrapper");
        var audioElement = document.createElement("audio");
        audioElement.style.height = "50px";
        audioElement.controls = true;
        audioElement.classList.add("media-item","my-3");
        audioElement.innerHTML = '<source src="' + question.audioSrc + '" type="audio/mpeg">';
        mediaWrapper2.appendChild(audioElement);
        headerWrapper.appendChild(mediaWrapper2);

       wrapper.appendChild(headerWrapper);

        var questionHeader = document.createElement("h5");
        questionHeader.classList.add("fw-semi-bold");
        questionHeader.innerText = "Question " + (index + 1) + ": " + question.text;
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
            input.name = "answer" + (index + 1);
            input.id = "option" + (index + 1) + String.fromCharCode(65 + answerIndex);
            input.classList.add("answer-input");
            input.value = String.fromCharCode(65 + answerIndex);

            var label = document.createElement("label");
            label.setAttribute("for", "option" + (index + 1) + String.fromCharCode(65 + answerIndex));
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
   
    var numberCorrect =0;
        conversation.questions.forEach(function(question, index) {
            var selectedAnswer = document.querySelector('input[name="answer' + (index + 1) + '"]:checked');
            if (selectedAnswer) {
                var correctAnswerIndex = question.correctAnswerIndex;
                var correctAnswer = String.fromCharCode(65 + correctAnswerIndex);
                var parentDiv = selectedAnswer.closest(".answer-option");
                var label = parentDiv.querySelector("label");
                var span = parentDiv.querySelector("span");
                if (selectedAnswer.value === correctAnswer) {
                    label.classList.add("correct"); // Thêm class "correct" nếu đáp án đúng
                    span.classList.add("span-correct"); // Thêm class "correct"
                    var correctSpan = document.createElement('span');
                    correctSpan.classList.add('result-icon-correct');
                    correctSpan.innerHTML = '<i class="fas fa-check"></i>';
                    parentDiv.appendChild(correctSpan);
                    numberCorrect++;
                    questionButtons[index].addClass('answered-score-correct');
                   
                } else {
                    label.classList.add("incorrect"); // Thêm class "incorrect" nếu đáp án sai
                    span.classList.add("span-incorrect"); // Thêm class "correct"
                    var incorrectSpan = document.createElement('span');
                    incorrectSpan.classList.add('result-icon-incorrect');
                    incorrectSpan.innerHTML = '<i class="fas fa-times"></i>';
                    parentDiv.appendChild(incorrectSpan);
                    questionButtons[index].addClass('answered-score-incorrect');
                 
                }
            }else{
                // alert("Vui lòng hoàn thành đáp án trước khi kiểm tra đáp án!");
            }
           
            disableInputsAndLabels();
        });
        removeActiveQuestionNumber();
        clearInterval(countdownInterval);
        var submitButton = document.getElementById("submitButton");
        var submitButtonContainer= submitButton.parentNode;
        submitButtonContainer.parentNode.removeChild(submitButtonContainer);
        var result = document.getElementById('result');
        result.innerText= 'Kết quả: ' + numberCorrect + "/" + conversation.questions.length;

}

function submitButton(){
    var submitButton = document.getElementById("submitButton");
    submitButton.addEventListener("click", getScore);
    
}
submitButton();
// hết thời gian tự submit bài làm

 // thêm sự kiện cho các nút 
//  function getAnswerFromButtonCheck(){
//     var checkButton = document.getElementById("checkButton");
    
//     checkButton.addEventListener("click", function() {
//         var isAnyQuestionUnanswered = false; // Cờ kiểm tra nếu có câu hỏi nào chưa được hoàn thành

//         conversation.questions.forEach(function(question, index) {
//         var selectedAnswer = document.querySelector('input[name="answer' + (index + 1) + '"]:checked');
//         if (!selectedAnswer) {
//             isAnyQuestionUnanswered = true; // Đặt cờ thành true nếu có câu hỏi nào chưa được hoàn thành
//               }
//          });

//         if (isAnyQuestionUnanswered) {
//             alert("Vui lòng hoàn thành đáp án trước khi kiểm tra đáp án!");
//             return; // Kết thúc sự kiện kiểm tra nếu có câu hỏi nào chưa được hoàn thành
//         }

//         conversation.questions.forEach(function(question, index) {
//             var selectedAnswer = document.querySelector('input[name="answer' + (index + 1) + '"]:checked');
//             if (selectedAnswer) {
//                 var correctAnswerIndex = question.correctAnswerIndex;
//                 var correctAnswer = String.fromCharCode(65 + correctAnswerIndex);
//                 var parentDiv = selectedAnswer.closest(".answer-option");
//                 var label = parentDiv.querySelector("label");
//                 var span = parentDiv.querySelector("span");
//                 if (selectedAnswer.value === correctAnswer) {
//                     label.classList.add("correct"); // Thêm class "correct" nếu đáp án đúng
//                     span.classList.add("span-correct"); // Thêm class "correct"
//                     var correctSpan = document.createElement('span');
//                     correctSpan.classList.add('result-icon-correct');
//                     correctSpan.innerHTML = '<i class="fas fa-check"></i>';
//                     parentDiv.appendChild(correctSpan);
//                 } else {
//                     label.classList.add("incorrect"); // Thêm class "incorrect" nếu đáp án sai
//                     span.classList.add("span-incorrect"); // Thêm class "correct"
//                     var incorrectSpan = document.createElement('span');
//                     incorrectSpan.classList.add('result-icon-incorrect');
//                     incorrectSpan.innerHTML = '<i class="fas fa-times"></i>';
//                     parentDiv.appendChild(incorrectSpan);
//                 }
//             }else{
//                 alert("Vui lòng hoàn thành đáp án trước khi kiểm tra đáp án!");
//             }
           
//             disableInputsAndLabels();
//         });
//         var buttonContainer = checkButton.parentNode;
//           // Loại bỏ phần tử cha (khối div bọc)
//          buttonContainer.parentNode.removeChild(buttonContainer);
//     });
    
// }

// getAnswerFromButtonCheck();

function disableInputsAndLabels() {
    var inputs = document.querySelectorAll('.answer-option input');
    var labels = document.querySelectorAll('.answer-option label');
    inputs.forEach(function(input) {
        input.disabled = true; // Vô hiệu hóa các input
    });
    labels.forEach(function(label) {
        label.classList.add("disabled"); // Thêm class disabled cho các label để ẩn hiển thị
    });
}
 // end nút kiểm tra
//  function getAnswerFromButtonAnswer(){
//     var answerButton = document.getElementById("answerButton");
//     var description = document.getElementById("answer-content");
//     answerButton.addEventListener("click", function() {

//         var isAnyQuestionUnanswered = false; // Cờ kiểm tra nếu có câu hỏi nào chưa được hoàn thành
//         conversation.questions.forEach(function(question, index) {
//         var selectedAnswer = document.querySelector('input[name="answer' + (index + 1) + '"]:checked');
//         if (!selectedAnswer) {
//             isAnyQuestionUnanswered = true; // Đặt cờ thành true nếu có câu hỏi nào chưa được hoàn thành
//               }
//          });

//         if (isAnyQuestionUnanswered) {
//             alert("Vui lòng hoàn thành đáp án trước khi kiểm tra đáp án!");
//             return; // Kết thúc sự kiện kiểm tra nếu có câu hỏi nào chưa được hoàn thành
//         }
//         description.innerHTML=conversation.description;
//         description.style.display='block';
//         conversation.questions.forEach(function(question, index) {
//             var selectedAnswer = document.querySelector('input[name="answer' + (index + 1) + '"]:checked');
//             if (selectedAnswer) {
//                 var correctAnswerIndex = question.correctAnswerIndex;
//                 var correctAnswer = String.fromCharCode(65 + correctAnswerIndex);
//                 var parentDiv = selectedAnswer.closest(".answer-option");
//                 var label = parentDiv.querySelector("label");
//                 var span = parentDiv.querySelector("span");
//                 if (selectedAnswer.value === correctAnswer) {
//                     label.classList.add("correct"); // Thêm class "correct" nếu đáp án đúng
//                     span.classList.add("span-correct"); // Thêm class "correct"
//                     var correctSpan = document.createElement('span');
//                     correctSpan.classList.add('result-icon-correct');
//                     correctSpan.innerHTML = '<i class="fas fa-check"></i>';
//                     parentDiv.appendChild(correctSpan);
//                 } else {
//                     label.classList.add("incorrect"); // Thêm class "incorrect" nếu đáp án sai
//                     span.classList.add("span-incorrect"); // Thêm class "correct"
//                     var incorrectSpan = document.createElement('span');
//                     incorrectSpan.classList.add('result-icon-incorrect');
//                     incorrectSpan.innerHTML = '<i class="fas fa-times"></i>';
//                     parentDiv.appendChild(incorrectSpan);
//                 }
//             }else{
//                 alert("Vui lòng hoàn thành đáp án trước khi kiểm tra đáp án!");
//             }
           
//             disableInputsAndLabels();
//         });
    
//         var checkButton =document.getElementById('checkButton');
//         var checkButtonContainer= checkButton.parentNode;
//         checkButtonContainer.parentNode.removeChild(checkButtonContainer);

//         var buttonContainer = answerButton.parentNode;
//           // Loại bỏ phần tử cha (khối div bọc)
//          buttonContainer.parentNode.removeChild(buttonContainer);

//     });
// }
// getAnswerFromButtonAnswer();



