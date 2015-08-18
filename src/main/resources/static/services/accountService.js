App.factory('accountService', function($http) {
	return {
		getAccounts : function() {
			return $http.get('/api/account');
		},
		getPositions : function(accountId) {
			return $http.get('/api/account/' + accountId + '/positions');
		},
		getOrders : function(accountId) {
			return $http.get('/api/account/' + accountId + '/orders');
		},
		getExecutions : function(accountId) {
			return $http.get('/api/account/' + accountId + '/executions');
		},
		addOrUpdateOrder : function(accountId, order) {
			return $http.post('/api/account/' + accountId + '/orders', order);
		}
	};
});