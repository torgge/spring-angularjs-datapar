'use strict';

angular.module('app',[
	 'ui.materialize',
	 'ui.router',
	 'ui.grid',
	 'ui.grid.pagination',
	 'ui.grid.cellNav',
	 'ngAnimate',
	 'toastr',
]);


angular.module('app').run(['$http','$rootScope', function($http,$rootScope){
	$rootScope.username='';
	$rootScope.token='@#$%¨&';
	$http.defaults.headers.common.Authorization = $rootScope.token;
}]);



