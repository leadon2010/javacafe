console.log(JSON.stringify(data));

$(document).ready(function () {
	$(data).each(function (i, o) {
		console.log(i);
		createNewItem(o);
	});
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
	let a2 = $('<a />').attr('href', pno.link).html(pno.item);
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