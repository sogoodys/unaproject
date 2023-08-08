$(function() {

 // fixed bookmark hover
    let bkSW = false;
    $('#bookmark_div_wrap').click(function() {
        bkSW = !bkSW;
        if(bkSW) {
            $('#bookmark_div_wrap').animate({
                left : 0
            });
        }else {
            $('#bookmark_div_wrap').animate({
                left: '-233px'
            });
        }
    });
    $(".bookmark_li>a").hover(function(){
        $(this).css({
            transition: "ease 0.4s",
            backgroundColor : "rgb(126, 129, 222)",
            color : "#fff"
        })
    }, function(){
        $(this).css({
            backgroundColor : "#f0f0f0",
            color : "#555"
        })
        $(".bk_li_3>a").css({
            backgroundColor : "rgb(126, 129, 222)",
            color : "#fff"
        })
    })
    // go_up_
    $(".go_up_img").click(function(){
        $('html, body').animate({
            scrollTop : 0
        }, 400);
    })
});