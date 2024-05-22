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

  // khởi taọ dữ liệu từ update nếu không thì thêm bình thường

var questionCount = 1;
var selectedAddQuestions = {};
function initData(){
    var nameReview = document.getElementById("name-review");
    nameReview.value = questions.nameReview;
    if(questions != null){
        questions.questions.forEach((question,index) =>{
            
            var newQuestionWrapper = document.createElement('div');
            newQuestionWrapper.classList.add('wrapper-question','my-3');
        
            var questionHtml = `
                   
                <div class="form-group row">
                    <label class="col-lg-2 col-form-label fw-bold" for="image-name">Hình ảnh <span class="text-danger">*</span></label>
                    <div class="col-lg-5">
                        <input type="text" class="form-control" id="image-input" name="image-input" placeholder="Link ảnh" value = "${question.image.linkImage}">
                    </div>
                    <div class="col-lg-4 text-center">
                        <input type="file" value = "${question.image.mutipartFileImage}" class="btn mb-1 btn-rounded btn-success file-input" id="image-file" style="width: inherit;" ><span class="btn-icon-left"></span>
                        <label for="image-file" class="file-label" id="label-image-file"></label>

                    </div>
                </div>

                <div class="form-group row">
                    <label class="col-lg-2 col-form-label fw-bold" for="audio-name">File nghe <span class="text-danger">*</span></label>
                    <div class="col-lg-5">
                        <input type="text" class="form-control" id="audio-input" name="audio-input" placeholder="Link audio" value = "${question.audio.linkAudio}">
                    </div>
                    <div class="col-lg-4 text-center">
                        <input type="file" class="btn mb-1 btn-rounded btn-success file-input" id="audio-file" style="width: inherit;" value = "${question.audio.mutipartFileAudio}"><span class="btn-icon-left"></span>
                        <label for="audio-file" class="file-label" id="label-audio-file"></label>
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
                    <input type="number" class="form-control col-lg-3" id="question-correct" name="val-question" placeholder="Bắt đầu từ vị trí 1" value = "${question.answerCorrect}">
                </div>
            `;
        
            newQuestionWrapper.innerHTML = questionHtml;
            var wrapper = document.getElementById("wrapper");
            wrapper.appendChild(newQuestionWrapper);
         
            var answerInput = document.querySelector(".answer-input");
            question.answers.forEach((answer)=>{
                var newAnswerInput = document.createElement('div');
                newAnswerInput.classList.add('d-flex', 'align-items-center');
                newAnswerInput.innerHTML = `
                    <input type="text" class="form-control my-1 w-50" name="question" placeholder="Đáp án mới" value="${answer}">
                    <button class="remove-answer btn btn-sm btn-danger mx-2" type="button">-</button>
                `;
                answerInput.appendChild(newAnswerInput);
            });


        // Thêm sự kiện change
            const labelImage = document.getElementById("label-image-file");
            if(question.image.fileName){
                labelImage.textContent = question.image.fileName;
            }else {
                labelImage.textContent = 'Chọn file';
            }

            const labelAudio = document.getElementById("label-audio-file");
            if(question.audio.fileName){
                labelAudio.textContent = question.audio.fileName;
            }else {
                labelAudio.textContent = 'Chọn file';
            }

            $(document).ready(function() {
                $("#image-file").change(function() {
                    var input = this;
                    var fileName = input.files[0].name;
                    // var fileName = $(this).val();
                    if (fileName) {
                        $(this).siblings("label").text(fileName);
                        // $("#name-image").val(fileName);
                    } else {
                        $(this).siblings("label").text("Chọn file");
                        // $("#name-image").val('');
                    }
                });

                $("#audio-file").change(function() {
                    var inputAudio = this;
                    var fileNameAudio = inputAudio.files[0].name;
                    // var fileName = $(this).val();
                    if (fileNameAudio) {
                        $(this).siblings("label").text(fileNameAudio);
                        // $("#name-image").val(fileName);
                    } else {
                        $(this).siblings("label").text("Chọn file");
                        // $("#name-image").val('');
                    }
                });
            });


        });
    }
}

if(questions != null){
    initData();
}


   var selectedQuestions = {};
    document.addEventListener("DOMContentLoaded", function() {
        if(questions == null){
            numberAnswer=1;
        var newQuestionWrapper = document.createElement('div');
        newQuestionWrapper.classList.add('wrapper-question','my-3');
       
        var questionHtml = `
            <div class="form-group row">
                <label class="col-lg-2 col-form-label fw-bold" for="image-name">Hình ảnh <span class="text-danger">*</span></label>
                <div class="col-lg-5">
                    <input type="text" class="form-control" id="image-input" name="image-input" placeholder="Link ảnh">
                </div>
                <div class="col-lg-3 text-center">
                    <input type="file" class="btn mb-1 btn-rounded btn-success file-input" id="image-file" style="width: inherit;"><span class="btn-icon-left"></span>
                    <label for="image-file" class="file-label" id="label-image-file">Chọn file</label>
                </div>
            </div>

            <div class="form-group row">
                <label class="col-lg-2 col-form-label fw-bold" for="audio-name">File nghe <span class="text-danger">*</span></label>
                <div class="col-lg-5">
                    <input type="text" class="form-control" id="audio-input" name="audio-input" placeholder="Link audio">
                </div>
                <div class="col-lg-3 text-center">
                    <input type="file" class="btn mb-1 btn-rounded btn-success file-input" id="audio-file" style="width: inherit;"><span class="btn-icon-left"></span>
                     <label for="audio-file" class="file-label" id="label-audio-file">Chọn file</label>
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

            // Thêm sự kiện change
            $(document).ready(function() {
                $("#image-file").change(function() {
                    var input = this;
                    var fileName = input.files[0].name;
                    // var fileName = $(this).val();
                    if (fileName) {
                        $(this).siblings("label").text(fileName);
                        // $("#name-image").val(fileName);
                    } else {
                        $(this).siblings("label").text("Chọn file");
                        // $("#name-image").val('');
                    }
                });

                $("#audio-file").change(function() {
                    var inputAudio = this;
                    var fileNameAudio = inputAudio.files[0].name;
                    // var fileName = $(this).val();
                    if (fileNameAudio) {
                        $(this).siblings("label").text(fileNameAudio);
                        // $("#name-image").val(fileName);
                    } else {
                        $(this).siblings("label").text("Chọn file");
                        // $("#name-image").val('');
                    }
                });
            });
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



