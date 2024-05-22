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
                    <label class="col-lg-2 col-form-label fw-bold">Mô tả <span class="text-danger">*</span></label>
                    <div class="col-lg-8">
                        <textarea class="form-control" id="description" name="description" placeholder="Nhập mô tả">${questions.description}</textarea>
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

        $(document).ready(function() {
            // Khởi tạo Summernote cho các textarea có sẵn khi trang được tải
            $('textarea[name^="description"]').each(function() {
                $(this).summernote({
                    // Các tùy chọn cấu hình cho Summernote
                });
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



