<div id="footer" style="margin-top: 100px;">
    <div id="part1">
        <div id="companyinfo"> <a id="sitelink" href="#">IDRISCAR</a>
            <p id="title">Cool and Perfect car</p>
            <p id="detail">We bring awesome car that will make you excited. </p>
        </div>
        <div id="explore">
            <p id="txt1">Explore</p> <a class="link" href="#">Home</a> <a class="link" href="#videos">Video</a> <a class="link" href="#brands">Brand</a> <a class="link" href="#types">Type</a>
        </div>
        <div id="visit">
            <p id="txt2">Visit</p>
            <p class="text">Noname house</p>
            <p class="text">97 Man Thien </p>
            <p class="text">Phone: (22) 2363-3611 </p>
            <p class="text">Fax: (22) 2363-0350 </p>
        </div>
        <div id="legal">
            <p id="txt3">Legal</p> <a class="link1" href="https://en.wikipedia.org/wiki/Terms_of_service">Terms and Conditions</a> <a class="link1" href="https://en.wikipedia.org/wiki/Privacy_policy">Private Policy</a>
        </div>
        <div id="subscribe">
            <p id="txt4">Subscribe to US</p>
            <p class="text">IDRISChannel </p>
            <p id="txt5">Contact With US</p> <i class="fab fa-facebook-square social fa-2x"></i> <i class="fab fa-linkedin social fa-2x"></i> <i class="fab fa-twitter-square social fa-2x"></i>
        </div>
    </div>
    <div id="part2">
        <p id="txt6"><i class="material-icons tiny"> </i>copyright 2019 BBbootstrap - All right reserved</p>
    </div>
</div>

<script type="text/javascript">
jQuery(function($) {
    $(window).on('scroll', function() {
		if ($(this).scrollTop() >= 200) {
			$('.navbar').addClass('fixed-top');
		} else if ($(this).scrollTop() == 0) {
			$('.navbar').removeClass('fixed-top');
		}
	});
    $("#getstart").click(function() {
        $('html, body').animate({
            scrollTop: $("#content").offset().top
        }, 1000);
    });
    $("#videotag").click(function() {
        $('html, body').animate({
            scrollTop: $("#content").offset().top
        }, 1000);
    });
    $("#typetag").click(function() {
        $('html, body').animate({
            scrollTop: $("#types").offset().top
        }, 1000);
    });
    $("#brandtag").click(function() {
        $('html, body').animate({
            scrollTop: $("#brands").offset().top
        }, 1000);
    });
    $("#footertag").click(function() {
        $('html, body').animate({
            scrollTop: $("#footer").offset().top
        }, 1000);
    });
    
	function adjustNav() {
		var winWidth = $(window).width(),
			dropdown = $('.dropdown'),
			dropdownMenu = $('.dropdown-menu');
		
		if (winWidth >= 768) {
			dropdown.on('mouseenter', function() {
				$(this).addClass('show')
					.children(dropdownMenu).addClass('show');
			});
			
			dropdown.on('mouseleave', function() {
				$(this).removeClass('show')
					.children(dropdownMenu).removeClass('show');
			});
		} else {
			dropdown.off('mouseenter mouseleave');
		}
	}
	
	$(window).on('resize', adjustNav);
	
	adjustNav();
});

</script>

<script type="text/javascript">
jQuery(function($) {
    $(window).on('scroll', function() {
		if ($(this).scrollTop() >= 200) {
			$('.navbar').addClass('fixed-top');
		} else if ($(this).scrollTop() == 0) {
			$('.navbar').removeClass('fixed-top');
		}
	});
	
	function adjustNav() {
		var winWidth = $(window).width(),
			dropdown = $('.dropdown'),
			dropdownMenu = $('.dropdown-menu');
		
		if (winWidth >= 768) {
			dropdown.on('mouseenter', function() {
				$(this).addClass('show')
					.children(dropdownMenu).addClass('show');
			});
			
			dropdown.on('mouseleave', function() {
				$(this).removeClass('show')
					.children(dropdownMenu).removeClass('show');
			});
		} else {
			dropdown.off('mouseenter mouseleave');
		}
	}
	
	$(window).on('resize', adjustNav);
	
	adjustNav();
});
$(document).ready(function(){
	  $(".fancybox").fancybox({
	        openEffect: "none",
	        closeEffect: "none"
	    });
	    
	    $(".zoom").hover(function(){
			
			$(this).addClass('transition');
		}, function(){
	        
			$(this).removeClass('transition');
		});
	});
function showCar(id, img, name, price, type, brand, amount, disc){
	document.getElementById("modal-car-img").src = img;
	document.getElementById("modal-car-name").innerHTML = name;
	document.getElementById("modal-car-price").innerHTML = price + " $";
	document.getElementById("modal-car-type").innerHTML = "Type: " + type;
	document.getElementById("modal-car-brand").innerHTML = "Brand: " + brand;
	if(amount=='0'){
		document.getElementById("modal-car-amount").innerHTML = "Amount: SOLD OUT!";
		document.getElementById("btnBuyNow").disabled = true;
	}
	else{
		document.getElementById("modal-car-amount").innerHTML = "Amount: " + amount;
		document.getElementById("btnBuyNow").disabled = false;
	}
	document.getElementById("modal-car-disc").innerHTML = disc;
	
	document.getElementById("modal-order-img").src = img;
	document.getElementById("modal-order-car").innerHTML = name;
	document.getElementById("modal-order-total").value = price;
	document.getElementById("modal-order-price").value = price;
	document.getElementById("modal-order-carid").value = id;
	document.getElementById("modal-order-amount").max = amount;
	$("#carmodal").modal("show");
}
function showOrder(){
	var logged = ${logged};
	if(logged == "0") window.location.href = "http://localhost:8080/CarShop/login.htm";
	else{
		$("#carmodal").modal("hide");
		$('#ordermodal').modal({backdrop: 'static', keyboard: false});
		$("#ordermodal").modal("show");
	}
}
function onCheck(checkbox, button){
	if(document.getElementById(checkbox).checked){
		document.getElementById(button).disabled = false;
	}else{
		document.getElementById(button).disabled = true;
	}
}
function calcTotalPrice(amount){
	var a = document.getElementById(amount).value;
	document.getElementById("modal-order-total").value = a * document.getElementById("modal-order-price").value;
}
</script>
<script type="text/javascript">
$('[data-dismiss=modal]').on('click', function (e) {
    var $t = $(this),
        target = $t[0].href || $t.data("target") || $t.parents('.modal') || [];

  $(target)
    .find("input,textarea,select")
       .val('')
       .end()
    .find("input[type=checkbox], input[type=radio]")
       .prop("checked", "")
       .end();
})
</script>
<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
<script src="//cdnjs.cloudflare.com/ajax/libs/fancybox/2.1.5/jquery.fancybox.min.js"></script>

