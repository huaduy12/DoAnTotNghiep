// dữ liệu bên update trả ra
// Fake data for three questions

var questionsApi = null;
// {
//     name:"Bộ câu hỏi thứ nhất",
//     description:"W: Will you help me move my desk by the window?<br>M: Do you want the chair by the door?<br>W: No, let’s move it next to the desk <br>M: Chair by the desk, desk by the window. Anything else to move?",
//     audio: {
//       nameAudio: 'Audio 1',
//       linkAudio: 'https://example.com/audio1.mp3',
//       mutipartFileAudio: "c:/passwords.txt" // You can populate FormData if needed
//     },
//     questions:[
//             {
//                 nameQuestion: "What are those interested in the class asked to do?",
//                 answers: ['Sign up today at the community center', 'Speak with the coordinators after the talk', 'Deposit a check into the bank account','Take an admission test after the talk'],
//                 answerCorrect: 1 // Index of correct answer
//             },
//             {
//                 nameQuestion: "What are those interested in the class asked to do?",
//                 answers: ['Sign up today at the community center', 'Speak with the coordinators after the talk', 'Deposit a check into the bank account','Take an admission test after the talk'],
//                 answerCorrect: 1 // Index of correct answer
//             }
//     ]
       
// };

  // khởi taọ dữ liệu từ update nếu không thì thêm bình thường

var questionCount = 1;
var selectedAddQuestions = {};
var countAnswer = 1;    
function initData(){
   
    if(questionsApi != null){
      
        var newQuestionWrapper = document.createElement('div');
            newQuestionWrapper.classList.add('wrapper-header','my-3');
            
            var questionHtml = `
            <div class="form-group row">
            <label class="col-lg-2 col-form-label fw-bold" for="name">Tên bộ câu hỏi: <span class="text-danger">*</span>
            </label>
            <div class="col-lg-7">
                <input type="text" class="form-control" id="name" name="name" placeholder="Tên bộ câu hỏi" value = "${questionsApi.name}">
            </div>
        </div>

        <div class="form-group row">
            <label class="col-lg-2 col-form-label fw-bold">Mô tả <span class="text-danger">*</span></label>
            <div class="col-lg-8">
                <textarea class="form-control" id="description" name="description" placeholder="Nhập mô tả">${questionsApi.description}</textarea>
            </div>
       </div>
       <div>
        <button type="button" class="btn btn-info my-2 add-question" name="add-question-child">
            Thêm câu hỏi 
        </button>    
        </div>
            `;
        newQuestionWrapper.innerHTML = questionHtml;
        var wrapper = document.getElementById("wrapper");
        wrapper.appendChild(newQuestionWrapper);

        var countAnswerCorrect =1;
        questionsApi.questions.forEach((ques, i) => {
            var question = document.createElement("div");
            question.classList.add("wrapper-question","my-3",`wrapper-${questionCount}`);
            var questionHtml = `
            <p class="fw-bold text-center">Question <button class="remove-question btn btn-sm btn-danger my-4" type="button">-</button> </p> 
            <div class="form-group row my-5">
                <label class="col-2 col-form-label fw-bold" for="question-name-${questionCount}">Câu hỏi <span class="text-danger">*</span></label>
                <div class="col-7">
                    <input type="text" class="form-control" id="question-name-${questionCount}" name="question-name" placeholder="Tên câu hỏi" value = "${ques.nameQuestion}">
                </div>
            </div>
            <div class="answer">
                    <div class="row">
                        <p class="col-2 col-form-label fw-bold">Đáp án<span class="text-danger">*</span></p>         
                        <button class="add-answer btn btn-sm btn-primary col-1" type="button">+</button>
                    </div>    
                    <div class="answer-input" id="answer-input-${questionCount}">
                    </div>
            </div> 
            <div class="answer-correct row my-2">
                    <label class="col-3 col-form-label fw-bold" for="question-correct-${countAnswerCorrect}">Vị trí đáp án đúng <span class="text-danger">*</span></label>
                    <input type="number" class="form-control col-3" id="question-correct-${countAnswerCorrect}" name="question-correct" placeholder="Bắt đầu từ vị trí 1" value = "${ques.answerCorrect}">
            </div>

            `
            countAnswerCorrect++;
            question.innerHTML = questionHtml;
            newQuestionWrapper.appendChild(question);

            // thêm đáp án cho câu hỏi từ data trên
          
        var answerWrapper = question.querySelector(`#answer-input-${questionCount}`);
        var numberAnswer=1;
        ques.answers.forEach((answer) => {
            var newAnswerInput = document.createElement('div');
            newAnswerInput.classList.add('d-flex', 'align-items-center');
            newAnswerInput.innerHTML = `
                <input type="text" value = "${answer}" class="form-control my-1 w-50" name="question-${questionCount}" id="answer-${questionCount}-${numberAnswer}" placeholder="Đáp án">
                <button class="remove-answer btn btn-sm btn-danger mx-2" type="button">-</button>
            `;
            numberAnswer++;
             answerWrapper.appendChild(newAnswerInput);
        });
         countAnswer++;
         questionCount++;
         });
        var wrapper = document.getElementById("wrapper");
        wrapper.appendChild(newQuestionWrapper);    
    
     
     }
}

if(questionsApi != null){
    initData();
}

function initDataNoApi(){
   
    if(questionsApi == null){
      
        var newQuestionWrapper = document.createElement('div');
            newQuestionWrapper.classList.add('wrapper-header','my-3');
            
            var questionHtml = `
            <div class="form-group row">
            <label class="col-lg-2 col-form-label fw-bold" for="name">Tên bộ câu hỏi: <span class="text-danger">*</span>
            </label>
            <div class="col-lg-7">
                <input type="text" class="form-control" id="name" name="name" placeholder="Tên bộ câu hỏi">
            </div>
        </div>

        <div class="form-group row">
            <label class="col-lg-2 col-form-label fw-bold">Mô tả<span class="text-danger">*</span></label>
            <div class="col-lg-8">
                <textarea class="form-control" id="description" name="description" placeholder="Nhập mô tả"></textarea>
            </div>
       </div>
       <div>
        <button type="button" class="btn btn-info my-2 add-question" name="add-question-child">
            Thêm câu hỏi 
        </button>    
        </div>
            `;
        newQuestionWrapper.innerHTML = questionHtml;
        var wrapper = document.getElementById("wrapper");
        wrapper.appendChild(newQuestionWrapper);

     }

}

if(questionsApi == null){
    initDataNoApi();
}

   var selectedQuestions = {};
   
    // Thêm bộ câu hỏi mới
    document.querySelector('.add-question').addEventListener('click', function() {
        numberAnswer=1;
       
        var newQuestionWrapper = document.createElement('div');
        newQuestionWrapper.classList.add('wrapper-question','my-3',`wrapper-${questionCount}`);
       
        var questionHtml = `
               <p class="fw-bold text-center">Question <button class="remove-question btn btn-sm btn-danger my-4" type="button">-</button> </p> 
                <div class="form-group row">
                <label class="col-lg-2 col-form-label fw-bold" for="question-name-${questionCount}">Câu hỏi <span class="text-danger">*</span></label>
                <div class="col-lg-7">
                    <input type="text" class="form-control question-name" id="question-name-${questionCount}" name="question-name-${questionCount}" placeholder="Tên câu hỏi">
                </div>
            </div>
            <div class="answer">
                    <div class="row">
                        <p class="col-2 col-form-label fw-bold">Đáp án<span class="text-danger">*</span></p>         
                        <button class="add-answer btn btn-sm btn-primary col-1" type="button">+</button>
                    </div>    
                    <div class="answer-input" id="answer-input-${questionCount}">
                    </div>
            </div> 
            <div class="answer-correct row my-5">
                    <label class="col-lg-3 col-form-label fw-bold" for="question-correct-${questionCount}">Vị trí đáp án đúng <span class="text-danger">*</span></label>
                    <input type="number" class="form-control col-lg-3" id="question-correct-${questionCount}" name="question-correct" placeholder="Bắt đầu từ vị trí 1">
            </div>
        </div>

        `;
        questionCount++;
        newQuestionWrapper.innerHTML = questionHtml;
        var wrapper = document.getElementById("wrapper");
        wrapper.appendChild(newQuestionWrapper);
     
      
    });

    // xoá câu hỏi đang tạo tương ứng
    document.addEventListener('click', function(event) {
        if (event.target && event.target.classList.contains('remove-question')) {
            var questionId = event.target.closest('.wrapper-question').querySelector('input').id.split('-').pop();
            var questionWrapper = event.target.closest('.wrapper-question');
            questionWrapper.remove();
             // Xóa câu hỏi khỏi selectedAddQuestions
            delete selectedAddQuestions[questionId];
            questionCount--;
            
        }
    });
    

    // Thêm/xóa đáp án
    var numberAnswer=1;
    document.addEventListener('click', function(event) {
        if (event.target && event.target.classList.contains('add-answer')) {
           if(questionsApi != null){
            var answerWrapper = event.target.closest('.answer').querySelector('.answer-input');
            var answerWrapperClass = answerWrapper.id;
            var newAnswerInput = document.createElement('div');
            newAnswerInput.classList.add('d-flex', 'align-items-center');
            newAnswerInput.innerHTML = `
                <input type="text" class="form-control my-1 w-50" name="question-${answerWrapper.id.split("-")[2]}" id="answer-${answerWrapper.id.split("-")[2]}-${numberAnswer}" placeholder="Đáp án mới">
                <button class="remove-answer btn btn-sm btn-danger mx-2" type="button">-</button>
            `;
            numberAnswer++;
            answerWrapper.appendChild(newAnswerInput);
           }else{
            var answerWrapper = event.target.closest('.answer').querySelector('.answer-input');
            var newAnswerInput = document.createElement('div');
            newAnswerInput.classList.add('d-flex', 'align-items-center');
            newAnswerInput.innerHTML = `
                <input type="text" class="form-control my-1 w-50" name="question-${answerWrapper.id.split("-")[2]}" id="answer-${answerWrapper.id.split("-")[2]}-${numberAnswer}" placeholder="Đáp án mới">
                <button class="remove-answer btn btn-sm btn-danger mx-2" type="button">-</button>
            `;
            numberAnswer++;
            answerWrapper.appendChild(newAnswerInput);
           }
        }
    
        if (event.target && event.target.classList.contains('remove-answer')) {
            var questionId = event.target.closest('.wrapper-question').querySelector('input').id.split('-').pop();
            var answerInput = event.target.closest('.d-flex');
            answerInput.remove();
            numberAnswer--;
       
        }
    });
    // end

//     // end


