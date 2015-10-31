'use strict';

angular.module('app')
.controller(
  'MercadoriaController',
  [		   '$scope','$http','uiGridConstants','MercadoriaService','CategoriaService','UnidadeService',
  function ($scope	,$http	,uiGridConstants , MercadoriaService , CategoriaService , UnidadeService){

   $scope.clearMerc = function() {
   $scope.model = {
                    id:0,
                    nome:"",
			              quantidade:0,
                    quantidadeMinima:0,
			              categoria:{id:1,descricao:""},
			              unidade:{id:1,descricao:""}
			            };
   };

	 $scope.gridOptions = {
			    enableFiltering: true,
			    enablePaginationControls: true,
			    enableGridMenu: false,
			    paginationPageSize: 10,
			    paginationPageSizes: [10, 20, 30],
			    columnDefs: [
			    { name: 'id'			, field:'id'				 , displayName:'Código'			, width: 80 , enableColumnMenu: false },
			    { name: 'nome'    , field:'nome'  , displayName:'Descrición'	, enableColumnMenu: false },
				  { name: 'unidade'		, field:'unidade.descricao'	 , displayName:'Unidade'   ,  enableColumnMenu: false },
				  { name: 'categoria'	, field:'categoria.descricao'	 , displayName:'Categoria',  enableColumnMenu: false }
			    ]
	};



	$scope.gridOptions.data=[];

	$scope.loadMerc = function(){
		 MercadoriaService.getMercadorias()
			.success(function (data) {
				 $scope.gridOptions.data = data;
         console.info(data);
		 });
	};

	$scope.loadMerc();

	$scope.loadDados = function(){

       $scope.clearMerc();

		 	 CategoriaService.getCategorias()
				.success(function (data) {
					 $scope.categorias = data;
			 });

		 	 UnidadeService.getUnidades()
				.success(function (data) {
					 $scope.unidades = data;
			 });

	};

	$scope.addMerc = function() {
		 MercadoriaService.insertMercadoria( $scope.model )
		 $scope.loadMerc();

	}



}]);
