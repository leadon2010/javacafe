$(document).ready(function () {

	let page = window.location.pathname;
	// remove carousel on jsp page.
	let jsPage = page.substring(page.lastIndexOf('.') + 1);

	page = page.substring(page.lastIndexOf('/') + 1, page.lastIndexOf('.'));
	console.log(page);
	
	console.log(jsPage);
	if(jsPage == 'jsp') {
		$('#carouselExampleIndicators').css('display', 'none');
		$('#carouselExampleIndicators').after($('<div />').css('height', '20px'));
				
	} else {
		let grepData = $.grep(data, function (a, b) {
			return a.category == page;
		})
	
		$(grepData).each(function (i, o) {
			createNewItem(o);
		});
		
	}


	// nav click event
	$('.nav-item').on('click', function () {
		$('.nav-item').removeClass('active');
		$(this).addClass('active');
	})

});

function createNewItem(pno) {
	let div1 = $('<div />').addClass("col-lg-4 col-md-6 mb-4");
	let div2 = $('<div />').addClass("card h-100");
	let a1 = $('<a />').attr('href', pno.link);
	let img1 = $('<img />').addClass("card-img-top").attr({
		'src': pno.image,
		'alt': pno.alt
	});
	let div3 = $('<div />').addClass("card-body");
	let h_4 = $('<h4 />').addClass("card-title");
	let a2 = $('<a />').attr('href', pno.link + "?item_no=" + pno.item_no).html(pno.item + "(" + pno.category + ")");
	let h_5 = $('<h5 />').html(new Intl.NumberFormat('ko-KR', {
		style: 'currency',
		currency: 'KRW'
	}).format(pno.price));
	//new Intl.NumberFormat('ko-KR', { style: 'currency', currency: 'KRW' }).format(number)
	let p1 = $('<p />').addClass("card-text").html(pno.content);
	let div4 = $('<div />').addClass("card-footer");
	let star = '';
	let cnt = Math.floor(pno.star);
	let half = pno.star - cnt;
	for (let i = 0; i < cnt; i++) {
		star += '&#9733;';
	}
	if (half)
		star += '&#9734;';
	let small_1 = $('<small />').addClass("text-muted").html(star);

	div1.append(div2.append(a1.append(img1), div3.append(h_4.append(a2), h_5, p1), div4.append(small_1)));

	$('div.row .row').append(div1);
}