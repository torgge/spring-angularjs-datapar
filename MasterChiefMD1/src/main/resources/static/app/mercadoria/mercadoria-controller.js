'use strict';

angular.module('app')
.controller(
  'MercaderiaController',
  [		   '$scope','$http','uiGridConstants','mercadoriaService','categoriaService','unidadeService', 
  function ($scope	,$http	,uiGridConstants , mercadoriaService , categoriaService , unidadeService){

	 $scope.loadMerc = function(){ 
		 mercadoriaService.getMercaderias()  
			.success(function (data) {
				 $scope.gridOptions.data = data;
		 });
	 };
	 $scope.loadMerc();

	 $scope.loadDados = function(){
	 
			 categoriaService.getCategorias()  
				.success(function (data) {
					 $scope.categorias = data;
			 });
			 
			 unidadeService.getUnidades()  
				.success(function (data) {
					 $scope.unidades = data;
			 });

	 };
	 
	 $scope.model = {descricao:"",quantidade:777,quantidadeMinima:777,Categoria:{id:1,descricao:""},Unidade:{id:1,descricao:""}};
	 

	 $scope.addMerc = function() {
		 
		 mercadoriaService.insertMercaderia( $scope.model )
		 $scope.loadMerc();
		 
	 }
	 
	 

	 $scope.gridOptions = {
			    enableFiltering: true,
			    enablePaginationControls: true,
			    enableGridMenu: false,
			    paginationPageSize: 10,
			    paginationPageSizes: [10, 20, 30],
			    columnDefs: [
			      { name: 'id'				, field:'id'				 , displayName:'Código'			, width: 80 , enableColumnMenu: false },
			      { name: 'descricao' , field:'descricao'  , displayName:'Descrición'	, enableColumnMenu: false },
				  { name: 'unidade'		, field:'unidade.descricao'	 , displayName:'Unidade'   ,  enableColumnMenu: false },
				  { name: 'categoria'	, field:'categoria.descricao'	 , displayName:'Categoria',  enableColumnMenu: false }
			    ]
	};

}]);
