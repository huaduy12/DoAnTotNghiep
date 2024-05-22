// dữ liệu bên update trả ra
var urlParams = new URLSearchParams(window.location.search);
var id = urlParams.get('id');
console.log("id: " + id);

async function fetchData() {
    try {
        const response = await axios.get('/api/question/photo/' + id, {});
        console.log(response);
        return response.data;
    } catch (error) {
        console.log(error);
        return null;
    }
}




async function initData(){
    var questions = await fetchData();
    var nameReview = document.getElementById("name-review");
    nameReview.value = questions.name;
    if(questions != null){
            var newQuestionWrapper = document.createElement('div');
            newQuestionWrapper.classList.add('wrapper-question','my-3');

            var questionHtml = `
                 <input type="hidden" id="id-question" value="${questions.id}">  
              

                <div class="form-group row">
                    <label class="col-lg-2 col-form-label fw-bold" for="audio-name">File nghe <span class="text-danger">*</span></label>
                    <div class="col-lg-5">
                        <input type="text" class="form-control" id="audio-input" name="audio-input" placeholder="Link audio" value = "${questions.audio.link ? questions.audio.link : ''}">
                    </div>
                    <div class="col-lg-4 text-center">
                        <input type="file" class="btn mb-1 btn-rounded btn-success file-input" id="audio-file" style="width: inherit;"><span class="btn-icon-left"></span>
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
                    <input type="number" class="form-control col-lg-3" id="question-correct" name="val-question" placeholder="Bắt đầu từ vị trí 1" value = "${questions.answerCorrect}">
                </div>
            `;

            newQuestionWrapper.innerHTML = questionHtml;
            var wrapper = document.getElementById("wrapper");
            wrapper.appendChild(newQuestionWrapper);

            var answerInput = document.querySelector(".answer-input");
            questions.answers.forEach((answer)=>{
                var newAnswerInput = document.createElement('div');
                newAnswerInput.classList.add('d-flex', 'align-items-center');
                newAnswerInput.innerHTML = `
                    <input type="text" class="form-control my-1 w-50" name="question" placeholder="Đáp án mới" value="${answer}">
                    <button class="remove-answer btn btn-sm btn-danger mx-2" type="button">-</button>
                `;
                answerInput.appendChild(newAnswerInput);
            });


            // Thêm sự kiện change

            const labelAudio = document.getElementById("label-audio-file");
            if(questions.audio.nameBase64){
                labelAudio.textContent = questions.audio.nameBase64;
            }else {
                labelAudio.textContent = 'Chọn file';
            }

            $(document).ready(function() {

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
}


initData();


document.addEventListener("DOMContentLoaded", function() {

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



