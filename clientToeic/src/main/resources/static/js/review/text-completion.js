
document.getElementById('reloadButton').addEventListener('click', () => {
    window.location.reload();
});

var urlParams = new URLSearchParams(window.location.search);
var id = urlParams.get('id');
var conversation = undefined;

// Định nghĩa hàm async để gọi API và xử lý dữ liệu
async function fetchDataAndInitialize(id) {
    try {
        const response = await axios.get(`/api/review/talk/${id}`);
        return response.data;
    } catch (error) {
        console.error('Error fetching data:', error);
    }
}

// Định nghĩa một hàm async để sử dụng await và xử lý dữ liệu
async function initialize() {
    conversation = await fetchDataAndInitialize(id);
    if (conversation) {
        console.log("Trong: ", conversation.questions);
        setupQuestionsAndAnswers();
        createButtonAnswer();
        getQuestion();
        submitButton();
    }
}

// Gọi hàm initialize
initialize();
let questionButtons = [];
let currentQuestionIndex = 0;

function createButtonAnswer() {
    let totalQuestion =0;
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

// Lăp qua toàn bộ question bỏ class active
function removeActiveQuestionNumber() {
    questionButtons.forEach(function (questionButton, index) {
        questionButton.removeClass('active-number-question');
    });
}

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

function getQuestion() {
    var wrapperQuestion = document.getElementById('wrapper-question');
    var countQuestion =0;
    conversation.data.forEach(function(content, index) {

        var wrapper = document.createElement("div");
        wrapper.classList.add("wrapper-multi");
        wrapper.id = "wrapper-multi-" + (index + 1);
        // wrapper.style.display='none';

        var headerWrapper = document.createElement("div");
        headerWrapper.classList.add("wrapper", "text-center");

        if(content.audioSrc){
            var mediaWrapper2 = document.createElement("div");
            mediaWrapper2.classList.add("media-wrapper");
            var audioElement = document.createElement("audio");
            audioElement.style.height = "50px";
            audioElement.controls = true;
            audioElement.classList.add("media-item","my-3");
            audioElement.innerHTML = '<source src="' + content.audioSrc + '" type="audio/mpeg">';
            mediaWrapper2.appendChild(audioElement);
            headerWrapper.appendChild(mediaWrapper2);
        }
        wrapper.appendChild(headerWrapper);

        var description = document.createElement("div");
        description.classList.add("alert", "alert-warning","answer-content");
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

            questionHeader.innerText = "Question " + (countQuestion + 1) + ": " + question.text;

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

function clickButtonBackAndNext() {
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

    });

}

// hiển thị lại question và các đáp án
// function showQuestionAndAnswer(){
//     conversation.questions.forEach(function(question,index){
//         var wrapper = document.getElementById('wrapper-'+(index+1));
//         var h5 = wrapper.querySelector('h5');
//         h5.innerText = "Question: " + (index+1) + ": " + question.text;
//
//
//         question.answers.forEach(function(answer,indexAnswer){
//             var input = document.querySelector("#option" + (index + 1) + String.fromCharCode(65 + indexAnswer));
//             span = document.createElement("span");
//             span.classList.add("mx-3","span-answer");
//             span.innerText = answer;
//             input.parentNode.appendChild(span);
//
//         });
//
//     });
// }

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

function getScore() {
    var numberCorrect =0;
    var indexQuestion =0;
    conversation.data.forEach(function(datas, index) {
        // question ở đây là chứa nhiều question
        datas.questions.forEach(function(question,index){
            indexQuestion++;
            var selectedAnswer = document.querySelector('input[name="answer' + (indexQuestion) + '"]:checked');

            if (selectedAnswer) {

                var correctAnswerIndex = question.correctAnswerIndex;
                var correctAnswer = String.fromCharCode(65 + (correctAnswerIndex-1));
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
    displayDescription();
    var submitButton = document.getElementById("submitButton");
    var submitButtonContainer= submitButton.parentNode;
    submitButtonContainer.parentNode.removeChild(submitButtonContainer);


}

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

function setupQuestionsAndAnswers() {
    // Function to setup event listeners for answer options after DOM content is loaded
    document.addEventListener('DOMContentLoaded', function () {
        var spans = document.querySelectorAll('.span-answer');
        spans.forEach(function (span) {
            span.onclick = function () {
                var inputId = this.previousElementSibling.getAttribute('for');
                selectAnswer(inputId);
            };
        });
    });

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
}

function selectAnswer(optionId) {
    document.getElementById(optionId).checked = true;
}
