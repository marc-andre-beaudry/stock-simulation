App.factory('userService', function($http) {
	return {
		getCurrentUser : function() {
			return $http.get('/api/user');
		}
	};
});