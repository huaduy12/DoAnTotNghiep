// khi click các nút trong photo detail review level
document.getElementById("reloadButton").addEventListener("click", function() {
    location.reload(); // Reload lại trang
});
// bấm vào span thì input cũng được chon
function selectAnswer(optionId) {
    document.getElementById(optionId).checked = true;
}

// nút reload lại trang



// nút đáp án để hiện thị đáp án và lời văn cho từng đáp án
document.getElementById("answerButton").addEventListener("click", function() {
    var correctAnswer = "B"; // Đáp án chính xác là B, bạn có thể thay đổi giá trị này theo đáp án thực tế
    var selectedAnswer = document.querySelector('input[name="answer"]:checked');
    if(selectedAnswer){
        var parentDiv = selectedAnswer.parentNode;
    }
    if (selectedAnswer) {
        // var question = 'e142. I’m considering signing up for the Society of Computer Engineers'
        // var answers = ["Câu trả lời cho đáp án A", "Câu trả lời cho đáp án B", "Câu trả lời cho đáp án C", "Câu trả lời cho đáp án D"];
        var conversation = `W: Will you help me move my desk by the window? M: Do you want the chair by the door? W: No, let’s move it next to the desk M: Chair by the desk, desk by the window. Anything else to move?`;
        var answerLabels = document.querySelectorAll('.answer-label');
// Chia đoạn văn thành các phần bằng "W:" hoặc "M:"
        var parts = conversation.split(/(?=W:|M:)/);
        var formattedConversation = parts.join("<br>");
        var conversationDiv = document.getElementById('answer-content');
        conversationDiv.innerHTML = formattedConversation;
        document.getElementById('answer-content').style.display = 'block';
        // document.getElementById('question').innerHTML ='Question: '+ question;
        // for (var i = 0; i < answerLabels.length; i++) {
        //     var span = document.createElement("span");
        //     span.id = "answer" + String.fromCharCode(65 + i); // Tạo id theo mã ASCII, ví dụ: answerA, answerB, answerC, answerD
        //     span.classList.add("mx-3");
        //     span.innerText = answers[i];
        //     answerLabels[i].parentNode.appendChild(span);
        // }
        var label = selectedAnswer.nextElementSibling;
        var span = label.nextElementSibling;
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
        var buttonCheckDiv = document.getElementById("buttonCheck");
        buttonCheckDiv.parentNode.removeChild(buttonCheckDiv); // Xóa div chứa nút kiểm tra
        var buttonAnswerDiv = document.getElementById("answerButton");
        buttonAnswerDiv.parentNode.removeChild(buttonAnswerDiv); // Xóa div chứa nút kiểm tra
        disableInputsAndLabels();
    } else {
        alert("Vui lòng chọn một đáp án trước khi kiểm tra đáp án!");
}   
});

function checkButton(){
    var correctAnswer = "B"; // Đáp án chính xác là B, bạn có thể thay đổi giá trị này theo đáp án thực tế
    var selectedAnswer = document.querySelector('input[name="answer"]:checked');
    if(selectedAnswer){
        var parentDiv = selectedAnswer.parentNode;
    }
    if (selectedAnswer) {
        var label = selectedAnswer.nextElementSibling;
        var span = label.nextElementSibling;
        if (selectedAnswer.value === correctAnswer) {
            label.classList.add("correct"); // Thêm class "correct" nếu đáp án đúng
            span.classList.add("span-correct"); // Thêm class "correct"
            var correctSpan = document.createElement('span');
            correctSpan.classList.add('result-icon-correct');
            correctSpan.innerHTML = '<i class="fas fa-check"></i>';
            parentDiv.appendChild(correctSpan);
        } else {
            label.classList.add("incorrect"); // Thêm class "incorrect" nếu đáp án sai1
            span.classList.add("span-incorrect"); // Thêm class "correct"
            var incorrectSpan = document.createElement('span');
            incorrectSpan.classList.add('result-icon-incorrect');
            incorrectSpan.innerHTML = '<i class="fas fa-times"></i>';
            parentDiv.appendChild(incorrectSpan);
        }
        var buttonCheckDiv = document.getElementById("buttonCheck");
        buttonCheckDiv.parentNode.removeChild(buttonCheckDiv); // Xóa div chứa nút kiểm tra
        disableInputsAndLabels();
    } else {
            alert("Vui lòng chọn một đáp án trước khi kiểm tra!");
    }
};

// hàm disble khi nút kiểm tra được gọi và vô hiệu hóa các nút còn lại
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

// nút kiểm tra
document.getElementById("checkButton").addEventListener("click", checkButton)

// nút tiếp theo
var page = 1;
// Sự kiện khi bấm nút phân trang
document.getElementById("paginationButton").addEventListener("click", function() {
    // Tăng biến page lên mỗi lần bấm
    page++;
     // Gọi hàm để gửi yêu cầu API và cập nhật trang
    //  fetchDataAndUpdatePage(page);
    // Cập nhật URL ,nếu có fectch rồi thì ko cần update
    updateURL(page);
    
});

// Hàm cập nhật URL với tham số page
function updateURL(page) {
    // Lấy URL hiện tại
    var currentURL = window.location.href;

    // Kiểm tra nếu URL đã chứa tham số page
    if (currentURL.indexOf("?") !== -1) {
        // Nếu có, thêm/sửa đổi giá trị của tham số page
        var urlParams = new URLSearchParams(window.location.search);
        urlParams.set('page', page);
        var newURL = currentURL.split('?')[0] + "?" + urlParams.toString();
        window.history.pushState({ path: newURL }, '', newURL);
        // window.location.href = newURL;
    } else {
        // Nếu không, thêm tham số page vào URL
        page=2;
        var newURL = currentURL + '?page=' + page;
        window.history.pushState({ path: newURL }, '', newURL);
        // window.location.href = newURL;
    }
    checkLastPage(totalPage);
}
window.addEventListener('popstate', function(event) {
    // Xử lý thay đổi URL tại đây
    // Ví dụ: tải lại trang web với URL mới
    window.location.href = event.state.path;
  })
// Hàm kiểm tra nếu đang ở trang cuối cùng
totalPage =5;
function checkLastPage(totalPage) {
    // Kiểm tra nếu page bằng totalPage
    if (page === totalPage) {
        // Ẩn nút phân trang
        var buttonPaginationDiv = document.getElementById("paginationButton");
        buttonPaginationDiv.parentNode.removeChild(buttonPaginationDiv); // Xóa div chứa nút kiểm tra
    }
}

// end photo details