App
		.filter(
				'formatPrice',
				function() {
					return function(input) {
						var n = input, c = isNaN(c = Math.abs(c)) ? 2 : c, d = d == undefined ? "."
								: d, t = t == undefined ? "," : t, s = n < 0 ? "-"
								: "", i = parseInt(n = Math.abs(+n || 0)
								.toFixed(c))
								+ "", j = (j = i.length) > 3 ? j % 3 : 0;
						return s
								+ (j ? i.substr(0, j) + t : "")
								+ i.substr(j).replace(/(\d{3})(?=\d)/g,
										"$1" + t)
								+ (c ? d + Math.abs(n - i).toFixed(c).slice(2)
										: "");
					};
				});

App.filter('formatPriceKeepNumber', function() {
	return function(input) {
		return Math.round(input * 100) / 100
	};
});

App.filter('formatMoney',
		function() {
			return function(num) {
				isNegative = false
				if (num < 0) {
					isNegative = true
				}
				num = Math.abs(num)
				if (num >= 1000000000) {
					formattedNumber = (num / 1000000000).toFixed(1).replace(
							/\.0$/, '')
							+ 'G';
				} else if (num >= 1000000) {
					formattedNumber = (num / 1000000).toFixed(1).replace(
							/\.0$/, '')
							+ 'M';
				} else if (num >= 1000) {
					formattedNumber = (num / 1000).toFixed(1).replace(/\.0$/,
							'')
							+ 'K';
				} else {
					formattedNumber = num;
				}
				if (isNegative) {
					formattedNumber = '-' + formattedNumber
				}
				return formattedNumber;
			};
		});