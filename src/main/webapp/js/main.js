
	
	$(".login-modal-list li").click(function() {
		 var thisIndex = $(this).index();
	     $(".login-container").hide();
	     $(".login-container").eq(thisIndex).show();

	     $(".login-modal-list li").removeClass("active");
	     $(".login-modal-list li").eq(thisIndex).addClass("active");
	});
	$(".login-modal-list li").eq(0).click();
	
  $('body').on("click", ".user-alert", function(e) {
		$('.invite-box').slideToggle();	
  });  

  	
  $(".title-nav li").click(function(){
     var thisIndex = $(this).index();
     $(".artice").hide();
     $(".artice").eq(thisIndex).show();
     
     $(".title-nav li a").removeClass("active");
     $(".title-nav li a").eq(thisIndex).addClass("active");  
     return false;
    });
  $(".title-nav li").eq(0).click();  
  
  $(window).load(function() {
	    $(".main-element").each(function(){
	    var $this = $(this);
	    $this.typed({
	    strings: $this.attr('data-elements').split(','),
	    typeSpeed: 100, // typing speed
	    backDelay: 3000 // pause before backspacing
	    });
	   })
	  })
  
