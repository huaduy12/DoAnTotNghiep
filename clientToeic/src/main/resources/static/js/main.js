
        document.querySelector('#dropdown-test').addEventListener('click', function(event) {
            event.preventDefault();
            const isDropdownToggled = this.classList.contains('show');
            if (!isDropdownToggled) {
              window.location.href = this.href;
            }
         });

         document.querySelector('#dropdown-review').addEventListener('click', function(event) {
            event.preventDefault();
            const isDropdownToggled = this.classList.contains('show');
            if (!isDropdownToggled) {
              window.location.href = this.href;
            }
         });

         document.querySelector('#dropdown-tuhoc').addEventListener('click', function(event) {
            event.preventDefault();
            const isDropdownToggled = this.classList.contains('show');
            if (!isDropdownToggled) {
              window.location.href = this.href;
            }
         });


(function ($) {
    "use strict";

    // Spinner
    var spinner = function () {
        setTimeout(function () {
            if ($('#spinner').length > 0) {
                $('#spinner').removeClass('show');
            }
        }, 1);
    };
    spinner();
    
    
    // Initiate the wowjs
    new WOW().init();


    // Sticky Navbar
    $(window).scroll(function () {
        if ($(this).scrollTop() > 300) {
            $('.sticky-top').css('top', '0px');
        } else {
            $('.sticky-top').css('top', '-100px');
        }
    });
    
    
    // Dropdown on mouse hover
    const $dropdown = $(".dropdown");
    const $dropdownToggle = $(".dropdown-toggle");
    const $dropdownMenu = $(".dropdown-menu");
    const showClass = "show";
    
    $(window).on("load resize", function() {
        if (this.matchMedia("(min-width: 992px)").matches) {
            $dropdown.hover(
            function() {
                const $this = $(this);
                $this.addClass(showClass);
                $this.find($dropdownToggle).attr("aria-expanded", "true");
                $this.find($dropdownMenu).addClass(showClass);
            },
            function() {
                const $this = $(this);
                $this.removeClass(showClass);
                $this.find($dropdownToggle).attr("aria-expanded", "false");
                $this.find($dropdownMenu).removeClass(showClass);
            }
            );
        } else {
            $dropdown.off("mouseenter mouseleave");
        }
    });
    
    
    // Back to top button
    $(window).scroll(function () {
        if ($(this).scrollTop() > 300) {
            $('.back-to-top').fadeIn('slow');
        } else {
            $('.back-to-top').fadeOut('slow');
        }
    });
    $('.back-to-top').click(function () {
        $('html, body').animate({scrollTop: 0}, 1500, 'easeInOutExpo');
        return false;
    });


    // Header carousel
    $(".header-carousel").owlCarousel({
        autoplay: true,
        smartSpeed: 1500,
        items: 1,
        dots: false,
        loop: true,
        nav : true,
        navText : [
            '<i class="bi bi-chevron-left"></i>',
            '<i class="bi bi-chevron-right"></i>'
        ]
    });


    // Testimonials carousel
    $(".testimonial-carousel").owlCarousel({
        autoplay: true,
        smartSpeed: 1000,
        center: true,
        margin: 24,
        dots: true,
        loop: true,
        nav : false,
        responsive: {
            0:{
                items:1
            },
            768:{
                items:2
            },
            992:{
                items:3
            }
        }
    });

    // Products Slick
	$('.products-slick').each(function() {
		var $this = $(this),
            $nav = $this.attr('data-nav');

		$this.slick({
			slidesToShow: 4,
			slidesToScroll: 1,
			autoplay: true,
			infinite: true,
			speed: 300,
			dots: false,
			arrows: true,
		    // appendArrows: $nav ? $nav : false,
            prevArrow: ".arrow-prev",
            nextArrow: ".arrow-next",
			responsive: [{
	        breakpoint: 991,
	        settings: {
	          slidesToShow: 2,
	          slidesToScroll: 1,
	        }
	      },
	      {
	        breakpoint: 480,
	        settings: {
	          slidesToShow: 1,
	          slidesToScroll: 1,
	        }
	      },
	    ]
		});
	});

    $('.reviews-slick').each(function() {
		var $this = $(this),
				$nav = $this.attr('data-nav');

		$this.slick({
			slidesToShow: 2,
			slidesToScroll: 1,
			autoplay: true,
			infinite: true,
			speed: 700,
			dots: false,
			arrows: true,
		    // appendArrows: $nav ? $nav : false,
            prevArrow: ".arrow-prev",
            nextArrow: ".arrow-next",
			responsive: [{
	        breakpoint: 991,
	        settings: {
	          slidesToShow: 2,
	          slidesToScroll: 1,
	        }
	      },
	      {
	        breakpoint: 480,
	        settings: {
	          slidesToShow: 1,
	          slidesToScroll: 1,
	        }
	      },
	    ]
		});
	});

    $('.self-learning-slick').each(function() {
        var $this = $(this),
            $nav = $this.attr('data-nav');

        $this.slick({
            slidesToShow: 2,
            slidesToScroll: 1,
            autoplay: true,
            infinite: true,
            speed: 700,
            dots: false,
            arrows: true,
            // appendArrows: $nav ? $nav : false,
            prevArrow: "#arrow-prev1",
            nextArrow: "#arrow-next1",
            responsive: [{
                breakpoint: 991,
                settings: {
                    slidesToShow: 2,
                    slidesToScroll: 1,
                }
            },
                {
                    breakpoint: 480,
                    settings: {
                        slidesToShow: 1,
                        slidesToScroll: 1,
                    }
                },
            ]
        });
    });

    $('.document-slick').each(function() {
        var $this = $(this),
            $nav = $this.attr('data-nav');

        $this.slick({
            slidesToShow: 2,
            slidesToScroll: 1,
            autoplay: true,
            infinite: true,
            speed: 700,
            dots: false,
            arrows: true,
            // appendArrows: $nav ? $nav : false,
            prevArrow: "#arrow-prev2",
            nextArrow: "#arrow-next2",
            responsive: [{
                breakpoint: 991,
                settings: {
                    slidesToShow: 2,
                    slidesToScroll: 1,
                }
            },
                {
                    breakpoint: 480,
                    settings: {
                        slidesToShow: 1,
                        slidesToScroll: 1,
                    }
                },
            ]
        });
    });
    
})(jQuery);

