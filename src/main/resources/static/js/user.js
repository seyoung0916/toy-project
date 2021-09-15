let index = {
    init: function () {
        $("#btn-save").on("click", () => { // function(){}, ()=>{} this를 바인딩하기 위해서
            // function으로 사용했다면 this가 window 객체가 바인딩됨
            this.save();
        });
    },

    save: function () {
        let data = {
            username: $("#username").val(),
            password: $("#password").val(),
            email: $("#email").val()
        }

        //$.ajax().done().fail(); // ajax 통신을 이용해 세 개의 데이터를 json으로 변경해 insert 요청
    }
}

index.init();