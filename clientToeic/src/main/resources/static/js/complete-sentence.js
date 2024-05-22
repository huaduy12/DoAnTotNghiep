document.getElementById("reloadButton").addEventListener("click", function() {
    location.reload(); // Reload lại trang
});


var conversation = {
    questions: [
        {
            text: "He was so helpful.He helped nearly ___ in the village",
            answers: ["Everybody", "Anybody", "Somebody","A hoặc B"],
            correctAnswerIndex: 3
        },
        {
            text: "Do you want ___ the chair by the door?",
            answers: ["Yes", "No", "I don't know"],
            correctAnswerIndex: 1
        },
        {
            text: "W: No, let’s move ___ it next to the desk",
            answers: ["Sure", "Okay", "Sounds good"],
            correctAnswerIndex: 2
        }
    ]
};


function getQuestion(){
    var questionSample = document.getElementById('question-sample');
    var answerContent = document.getElementById('answer-content');
    var wrapperQuestion = document.getElementById('wrapper-question');



    conversation.questions.forEach(function(question, index) {
       
        var wrapper = document.createElement("div"); 
        wrapper.id = "wrapper-" + (index + 1);
    
        var questionHeader = document.createElement("div");
        questionHeader.classList.add("alert","alert-warning", "text-justify");
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
 