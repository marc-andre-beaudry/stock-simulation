App.factory('moversService', function($http) {
	return {
		getPriceGainers : function() {
			return $http.get('/api/mktdata/mover/PriceGainer');
		}, 
		getPriceLosers: function() {
			return $http.get('/api/mktdata/mover/PriceLoser');
		}, 
		getMarketCapGainers: function() {
			return $http.get('/api/mktdata/mover/MktCapGainer');
		},
		getMarketCapLosers: function() {
			return $http.get('/api/mktdata/mover/MktCapLoser');
		}
	};
});