// dữ liệu bên update trả ra

var questions = null;
// {
//     id:'21312432',
//     nameReview:"Bài học mới nhất",
//     questions:[
//          {
//           image:{
//             linkImage: 'https://example.com/image1.jpg',
//             mutipartFileImage: "c:/passwords.txt", // You can populate FormData if needed
//             fileName:"ảnh 1212.jpg"
//           },
//           audio: {
//             linkAudio: 'https://example.com/audio1.mp3',
//             mutipartFileAudio: "c:/passwords.txt", // You can populate FormData if needed
//             fileName:"ảnh 1212 audio.jpg"
//           },
//           answers: ['Answer 1', 'Answer 2', 'Answer 3'],
//           answerCorrect: 1 // Index of correct answer
//         }
//     ]
// };


   var selectedQuestions = {};
    document.addEventListener("DOMContentLoaded", function() {
        if(questions == null){
            numberAnswer=1;
        var newQuestionWrapper = document.createElement('div');
        newQuestionWrapper.classList.add('wrapper-question','my-3');
       
        var questionHtml = `

            <div class="form-group row">
                <label class="col-lg-2 col-form-label fw-bold">Mô tả <span class="text-danger">*</span></label>
                <div class="col-lg-8">
                    <textarea class="form-control" id="description" name="description" placeholder="Nhập mô tả"></textarea>
                </div>
            </div>
            
            <div class="answer">
               <div class="row">
               <p class="col-2 col-form-label fw-bold" for="question-correct">Đáp án<span class="text-danger">*</span></p>         
               <button class="add-answer btn btn-sm btn-primary col-1" type="button">+</button>
               </div>
                <div class="answer-input">
                   
                </div>
            </div>
              
            <div class="answer-correct row my-5">
                <label class="col-lg-3 col-form-label fw-bold" for="question-correct">Vị trí đáp án đúng <span class="text-danger">*</span></label>
                <input type="number" class="form-control col-lg-3" id="question-correct" name="val-question" placeholder="Bắt đầu từ vị trí 1">
            </div>
        `;
      
        newQuestionWrapper.innerHTML = questionHtml;
        var wrapper = document.getElementById("wrapper");
        wrapper.appendChild(newQuestionWrapper);

 }
      

    // Thêm/xóa đáp án
    var numberAnswer=1;
    document.addEventListener('click', function(event) {
        
        if (event.target && event.target.classList.contains('add-answer')) {
            var answerWrapper = event.target.closest('.answer').querySelector('.answer-input');
            var newAnswerInput = document.createElement('div');
            newAnswerInput.classList.add('d-flex', 'align-items-center');
            newAnswerInput.innerHTML = `
                <input type="text" class="form-control my-1 w-50" name="question" id="answer-${numberAnswer}" placeholder="Đáp án mới">
                <button class="remove-answer btn btn-sm btn-danger mx-2" type="button">-</button>
            `;
            numberAnswer++;
            answerWrapper.appendChild(newAnswerInput);
        }
    
        if (event.target && event.target.classList.contains('remove-answer')) {
            var questionId = event.target.closest('.wrapper-question').querySelector('input').id.split('-').pop();
            var answerInput = event.target.closest('.d-flex');
            answerInput.remove();
            numberAnswer--;
       
        }
    });
    // end

});



