
document.getElementById('reloadButton').addEventListener('click', () => {
    window.location.reload();
});

var urlParams = new URLSearchParams(window.location.search);
var id = urlParams.get('id');
var conversation = undefined;

// Định nghĩa hàm async để gọi API và xử lý dữ liệu
async function fetchDataAndInitialize(id) {
    try {
        const response = await axios.get(`/api/review/${id}`);
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
    const backButton = document.getElementById('backButton');
    const nextButton = document.getElementById('nextButton');

    const questionCount = conversation.questions.length;
    const $numberQuestion = $('#number-question');
    for (let i = 0; i < questionCount; i++) {
        const $button = $('<a>')
            .addClass('btn btn-outline-secondary rounded-circle mx-2 my-2')
            .attr('href', '#')
            .text(i + 1);

        $button.on('click', function () {
            showQuestion(i);
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
        $numberQuestion.append($button);
        questionButtons.push($button);
    }
}

// Lăp qua toàn bộ question bỏ class active
function removeActiveQuestionNumber() {
    questionButtons.forEach(function (questionButton, index) {
        questionButton.removeClass('active-number-question');
    });
}

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

function getQuestion() {
    var wrapperQuestion = document.getElementById('wrapper-question');
    conversation.questions.forEach(function (question, index) {
        var wrapper = document.createElement("div");
        wrapper.id = "wrapper-" + (index + 1);
        wrapper.classList.add("cauhoi");

        var headerWrapper = document.createElement("div");
        headerWrapper.classList.add("wrapper", "text-center");

        var mediaWrapper1 = document.createElement("div");
        mediaWrapper1.classList.add("media-wrapper");
        var imageElement = document.createElement("img");
        imageElement.style.width = '60%';
        imageElement.classList.add("media-item");
        imageElement.src = question.image;
        mediaWrapper1.appendChild(imageElement);
        headerWrapper.appendChild(mediaWrapper1);

        var mediaWrapper2 = document.createElement("div");
        mediaWrapper2.classList.add("media-wrapper");
        var audioElement = document.createElement("audio");
        audioElement.style.height = "50px";
        audioElement.controls = true;
        audioElement.classList.add("media-item", "my-3");
        audioElement.innerHTML = '<source src="' + question.audioSrc + '" type="audio/mpeg">';
        mediaWrapper2.appendChild(audioElement);
        headerWrapper.appendChild(mediaWrapper2);

        wrapper.appendChild(headerWrapper);

        var questionHeader = document.createElement("h5");
        questionHeader.classList.add("fw-semi-bold");
        questionHeader.innerText = "Question " + (index + 1) + ": " + question.text;
        wrapper.appendChild(questionHeader);

        question.answers.forEach(function (answer, answerIndex) {
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
            span.classList.add("mx-3", "span-answer");
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

function clickButtonBackAndNext() {
    const backButton = document.getElementById('backButton');
    const nextButton = document.getElementById('nextButton');

    if (currentQuestionIndex === 0) {
        backButton.classList.add('d-none');
    }
    backButton.addEventListener('click', function () {
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
    });

    nextButton.addEventListener('click', function () {
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

function submitButton() {
    var submitButton = document.getElementById("submitButton");
    submitButton.addEventListener("click", getScore);
}

function getScore() {
    var numberCorrect = 0;
    conversation.questions.forEach(function (question, index) {
        var selectedAnswer = document.querySelector('input[name="answer' + (index + 1) + '"]:checked');
        if (selectedAnswer) {
            var correctAnswerIndex = question.correctAnswerIndex;
            var correctAnswer = String.fromCharCode(65 + (correctAnswerIndex - 1));
            var parentDiv = selectedAnswer.closest(".answer-option");
            var label = parentDiv.querySelector("label");
            var span = parentDiv.querySelector("span");
            if (selectedAnswer.value === correctAnswer) {
                label.classList.add("correct");
                span.classList.add("span-correct");
                var correctSpan = document.createElement('span');
                correctSpan.classList.add('result-icon-correct');
                correctSpan.innerHTML = '<i class="fas fa-check"></i>';
                parentDiv.appendChild(correctSpan);
                numberCorrect++;
                questionButtons[index].addClass('answered-score-correct');
            } else {
                label.classList.add("incorrect");
                span.classList.add("span-incorrect");
                var incorrectSpan = document.createElement('span');
                incorrectSpan.classList.add('result-icon-incorrect');
                incorrectSpan.innerHTML = '<i class="fas fa-times"></i>';
                parentDiv.appendChild(incorrectSpan);
                questionButtons[index].addClass('answered-score-incorrect');
            }
        }
        disableInputsAndLabels();
    });
    removeActiveQuestionNumber();

    var submitButton = document.getElementById("submitButton");
    var submitButtonContainer = submitButton.parentNode;
    submitButtonContainer.parentNode.removeChild(submitButtonContainer);
}

function disableInputsAndLabels() {
    var inputs = document.querySelectorAll('.answer-option input');
    var labels = document.querySelectorAll('.answer-option label');
    inputs.forEach(function (input) {
        input.disabled = true;
    });
    labels.forEach(function (label) {
        label.classList.add("disabled");
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

    $('.answer-option input[type="radio"], .answer-option label').on('click', function (event) {
        event.stopPropagation();
        const answerOption = $(this).closest('.answer-option');
        const checkedRadio = answerOption.find('input[type="radio"]:checked');
        if (checkedRadio.length > 0) {
            questionButtons[currentQuestionIndex].addClass('answered');
        } else {
            questionButtons[currentQuestionIndex].removeClass('answered');
        }
    });

    $('.answer-option .span-answer').on('click', function (event) {
        const inputId = $(this).siblings('input[type="radio"]').attr('id');
        const input = $(`input#${inputId}`);
        input.click();
    });
}

function selectAnswer(optionId) {
    document.getElementById(optionId).checked = true;
}
