$(function() {

    //탑메뉴 hover
    $('.menu').hover(function() {
        index=$(this).index();
        pagenum=index+1;
        $(".subMenu"+pagenum).stop(false, false).css({
            backgroundColor : "#fefefe",
            height : "auto"
        }).slideDown(300);
        $(".subMenu"+pagenum).find('a').css(color = '#fff');
    }, function() {
        $(".subMenu"+pagenum).stop(true, true).slideUp(300);
    });

    //
    $('.home').click(function() {
        window.location = 'index.html'
    });

    let loginSW = false;
    $('#login_area .srch_btn').click(function() {
        // $('#header_search').slideToggle("fast");
        loginSW = !loginSW;
        if(loginSW) {
            $('#header_search').animate({
                top: '100px'
            });
        }else {
            $('#header_search').animate({
                top: '0px'
            });
        }
    });
    // logo hover turn
    $(".main_logo_img").hover(function(){
        $(this).stop().animate({
            rotate : "-180deg"
        },800);
    },function(){
        $(this).stop().animate({
            rotate : "0deg"
        },800)
    });

    /* * * * * hover시 color변경 영역 * * * * */
    /* 서브메뉴 hover */
    $('.smenu>a').hover(function() {
        $(this).css({
            color: 'rgb(126, 129, 222)'
        });
    }, function() {
        $(this).css({
            color: '#000'
        });
    });
    /* 탑메뉴 hover */
    $('.menu').hover(function() {
        $(this).children('a').css({
            color: 'rgb(126, 129, 222)'
        });
    }, function() {
        $(this).children('a').css({
            color: '#000'
        });
    });
    /* 로그인 영역 hover */
    $('#login_area li').hover(function() {
        $(this).find('a').css({
            color: 'rgb(126, 129, 222)'
        });
    }, function() {
        $(this).find('a').css({
            color: '#000'
        });
    });
    /* footer link영역 hover */
    $('#footer_link li').hover(function() {
        $(this).find('a').css({
            color: 'rgb(126, 129, 222)'
        });
    }, function() {
        $(this).find('a').css({
            color: '#000'
        });
    });
    /* 로그인 영역 돋보기 hover */
    // $('#login_area .srch_btn').hover(function() {
    //     $('.srch_img').css({
    //         filter: 'invert(100%)'
    //     });
    // }, function() {
    //     $('.srch_img').css({
    //         filter: 'invert(80%)'
    //     });
    // });


});