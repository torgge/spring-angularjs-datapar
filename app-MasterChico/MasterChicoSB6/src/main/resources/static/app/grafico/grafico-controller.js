'use sctrict'

angular.module('app').controller('GraficoValorEstoqueController',['$http','$scope','$timeout',function($http,$scope,$timeout){

//grafico de linhas
	$scope.labels = ["2015"];
	$scope.series = ["",""];
	$scope.data = [0,0];

	$http.get('http://localhost:5000/api/graficoValorEstoque')
	.success(function(data){
      //console.log(data);
			$scope.series = data.series;
			//console.log('series:'+$scope.series);
			$scope.data = data.data;
			//console.log('data:'+$scope.data);
	});


	$scope.onClick = function (points, evt) {
	    console.log(points, evt);
	};

}]);




angular.module('app').controller('GraficoController',['$http','$scope','$timeout',function($http,$scope,$timeout){
	

	//grafico de linhas 
	$scope.labels = ["January", "February", "March", "April", "May", "June", "July"];
	$scope.series = ['Series A', 'Series B'];
	$scope.data = [
	    [65, 59, 80, 81, 56, 55, 40],
	    [28, 48, 40, 19, 86, 27, 90]
	];
	$scope.onClick = function (points, evt) {
	    console.log(points, evt);
	};
	
}]);


angular.module('app').controller('GraficoLine1Controller',['$http','$scope','$timeout',function($http,$scope,$timeout){

	
	//grafico de linhas 
	$scope.labels = ["January", "February", "March", "April", "May", "June", "July"];
	$scope.series = ['Series A', 'Series B'];
	$scope.data = [
	    [65, 59, 80, 81, 56, 55, 40],
	    [28, 48, 40, 19, 86, 27, 90]
	];
	$scope.onClick = function (points, evt) {
	    console.log(points, evt);
	};
	
}]);


angular.module('app').controller('GraficoLine2Controller',['$http','$scope','$timeout',function($http,$scope,$timeout){

	
	//grafico de linhas2 
	  $scope.labels = ['2006', '2007', '2008', '2009', '2010', '2011', '2012'];
	  $scope.series = ['Series A', 'Series B'];

	  $scope.data = [
	    [65, 59, 80, 81, 56, 55, 40],
	    [28, 48, 40, 19, 86, 27, 90]
	  ];
	
}]);



angular.module('app').controller('GraficoDoughnutController',['$http','$scope','$timeout',function($http,$scope,$timeout){

	
	//grafico de linhas Doughnut
	$scope.labels = ["Download Sales", "In-Store Sales", "Mail-Order Sales"];
	  $scope.data = [300, 500, 100];


		$http.get('http://localhost:5000/api/graficoValorEstoque')
		.success(function(data){
	      //console.log(data);
				$scope.labels = data.series;
				//console.log('series:'+$scope.series);
				$scope.data = data.data;
				//console.log('data:'+$scope.data);
		});


		$scope.onClick = function (points, evt) {
		    console.log(points, evt);
		};
	  
	  
}]);
