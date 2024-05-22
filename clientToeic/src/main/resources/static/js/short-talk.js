document.getElementById("reloadButton").addEventListener("click", function() {
    location.reload(); // Reload lại trang
});

var conversation = {
    audioSrc: "https://bd748f642cf8b253d59c-5c160b94f727c0d27cbeccc854542bc6.ssl.cf1.rackcdn.com/LearnEnglish%20Teens%20-%20Listening%20skills%20practice%20-%20A1%20-%20A%20good%20night's%20sleep.mp3",
    description:'Thank you for calling TC Telecom on this beautiful September Monday morning. Currently, all of our operators are busy with other calls, however, if you’d be kind enough to wait on the line, one of our operators will be with you very shortly. In the meantime, if you know the extension of the person you wish to contact, please press it on your phone now. If you are calling to check your account profile, please press 1. If you wish to delete or change a service, press 3. If this is a call that needs the attention of a customer service representative, please continue holding and talk to the operator. Thank you and we hope you have a great day. Remember, we here at TC Telecom value every call.',
    questions: [
        {
            text: "W: Will you help me move my desk by the window?",
            answers: ["Yes", "No", "Maybe","A hoặc B"],
            correctAnswerIndex: 3
        },
        {
            text: "M: Do you want the chair by the door?",
            answers: ["Yes", "No", "I don't know"],
            correctAnswerIndex: 1
        },
        {
            text: "W: No, let’s move it next to the desk",
            answers: ["Sure", "Okay", "Sounds good"],
            correctAnswerIndex: 2
        },
        {
            text: "M: Chair by the desk, desk by the window. Anything else to move?",
            answers: ["Yes", "No", "Maybe"],
            correctAnswerIndex: 1
        }
    ]
};

function getQuestion(){
    var questionSample = document.getElementById('question-sample');
    var answerContent = document.getElementById('answer-content');
    var wrapperQuestion = document.getElementById('wrapper-question');

    var audioWrapper = document.createElement("div");
    audioWrapper.classList.add("wrraper", "text-center");

    var audioElement = document.createElement("audio");
    audioElement.style.height = "50px";
    audioElement.controls = true;
    audioElement.classList.add("media-item");
    audioElement.innerHTML = '<source src="' + conversation.audioSrc + '" type="audio/mpeg">';

    audioWrapper.appendChild(audioElement);
    questionSample.insertAdjacentElement('afterend',audioWrapper);

    conversation.questions.forEach(function(question, index) {
       
        var wrapper = document.createElement("div"); 
        wrapper.id = "wrapper-" + (index + 1);
    
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
}
getQuestion();

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

 // thêm sự kiện cho các nút 
 function getAnswerFromButtonCheck(){
    var checkButton = document.getElementById("checkButton");
    
    checkButton.addEventListener("click", function() {
        var isAnyQuestionUnanswered = false; // Cờ kiểm tra nếu có câu hỏi nào chưa được hoàn thành

        conversation.questions.forEach(function(question, index) {
        var selectedAnswer = document.querySelector('input[name="answer' + (index + 1) + '"]:checked');
        if (!selectedAnswer) {
            isAnyQuestionUnanswered = true; // Đặt cờ thành true nếu có câu hỏi nào chưa được hoàn thành
              }
         });

        if (isAnyQuestionUnanswered) {
            alert("Vui lòng hoàn thành đáp án trước khi kiểm tra đáp án!");
            return; // Kết thúc sự kiện kiểm tra nếu có câu hỏi nào chưa được hoàn thành
        }

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
                } else {
                    label.classList.add("incorrect"); // Thêm class "incorrect" nếu đáp án sai
                    span.classList.add("span-incorrect"); // Thêm class "correct"
                    var incorrectSpan = document.createElement('span');
                    incorrectSpan.classList.add('result-icon-incorrect');
                    incorrectSpan.innerHTML = '<i class="fas fa-times"></i>';
                    parentDiv.appendChild(incorrectSpan);
                }
            }else{
                alert("Vui lòng hoàn thành đáp án trước khi kiểm tra đáp án!");
            }
           
            disableInputsAndLabels();
        });
        var buttonContainer = checkButton.parentNode;
          // Loại bỏ phần tử cha (khối div bọc)
         buttonContainer.parentNode.removeChild(buttonContainer);
    });
    
}

getAnswerFromButtonCheck();

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
 function getAnswerFromButtonAnswer(){
    var answerButton = document.getElementById("answerButton");
    var description = document.getElementById("answer-content");
    answerButton.addEventListener("click", function() {

        var isAnyQuestionUnanswered = false; // Cờ kiểm tra nếu có câu hỏi nào chưa được hoàn thành
        conversation.questions.forEach(function(question, index) {
        var selectedAnswer = document.querySelector('input[name="answer' + (index + 1) + '"]:checked');
        if (!selectedAnswer) {
            isAnyQuestionUnanswered = true; // Đặt cờ thành true nếu có câu hỏi nào chưa được hoàn thành
              }
         });

        if (isAnyQuestionUnanswered) {
            alert("Vui lòng hoàn thành đáp án trước khi kiểm tra đáp án!");
            return; // Kết thúc sự kiện kiểm tra nếu có câu hỏi nào chưa được hoàn thành
        }
        description.innerHTML=conversation.description;
        description.style.display='block';
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
                } else {
                    label.classList.add("incorrect"); // Thêm class "incorrect" nếu đáp án sai
                    span.classList.add("span-incorrect"); // Thêm class "correct"
                    var incorrectSpan = document.createElement('span');
                    incorrectSpan.classList.add('result-icon-incorrect');
                    incorrectSpan.innerHTML = '<i class="fas fa-times"></i>';
                    parentDiv.appendChild(incorrectSpan);
                }
            }else{
                alert("Vui lòng hoàn thành đáp án trước khi kiểm tra đáp án!");
            }
           
            disableInputsAndLabels();
        });
    
        var checkButton =document.getElementById('checkButton');
        var checkButtonContainer= checkButton.parentNode;
        checkButtonContainer.parentNode.removeChild(checkButtonContainer);

        var buttonContainer = answerButton.parentNode;
          // Loại bỏ phần tử cha (khối div bọc)
         buttonContainer.parentNode.removeChild(buttonContainer);

    });
}
getAnswerFromButtonAnswer();


