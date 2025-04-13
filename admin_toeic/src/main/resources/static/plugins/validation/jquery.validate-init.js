jQuery(".form-valide").validate({
    ignore: [],
    errorClass: "invalid-feedback animated fadeInDown",
    errorElement: "div",
    errorPlacement: function (e, a) {
        jQuery(a).parents(".form-group > div").append(e);
    },
    highlight: function (e) {
        jQuery(e)
            .closest(".form-group")
            .removeClass("is-invalid")
            .addClass("is-invalid");
    },
    success: function (e) {
        jQuery(e).closest(".form-group").removeClass("is-invalid"),
            jQuery(e).remove();
    },
    rules: {
        "username": { required: !0, minlength: 5 },
        "fullName": { required: !0, minlength: 5 },
        "email": { required: !0, email: !0 },
        "phoneNumber": { required: !0,
            digits: !0,
            minlength: 10,
            maxlength: 10 },
        // "val-address": { required: !0, minlength:10 },
        // "birthDay": {
        //     required: !0
        // },
    },
    messages: {
        "username": {
            required: "Vui lòng nhập họ và tên",
            minlength: "Nhập tối thiếu 5 ký tự",
        },
        "fullName": {
            required: "Vui lòng nhập họ và tên",
            minlength: "Nhập tối thiếu 5 ký tự",
        },
        "phoneNumber": {
            required: "Vui lòng nhập số điện thoại",
            digits: "Số điện thoại không hợp lệ",
            minlength: "Số điện thoại chỉ có 10 số",
            maxlength: "Số điện thoại chỉ có 10 số",
        },
        "email": "Vui lòng nhập địa chỉ email",
        // "val-address": {
        //     required: "Vui lòng nhập địa chỉ",
        //     minlength: "Nhập tối thiếu 10 ký tự",
        // },
        // "birthDay": {
        //     required: "Vui lòng chọn ngày sinh"
        // },
    },
});
