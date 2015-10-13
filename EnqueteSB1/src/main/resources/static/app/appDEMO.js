'use strict';

// módulos utilizados
var app = angular.module( 'app',
 [
   'daterangepicker',
   'ngTouch',
   'ui.grid',
   'ui.grid.pagination',
   'ui.grid.cellNav',
   'ngSanitize',
   'ui.select',
   'ur.file',
   'ngResource',
   'nya.bootstrap.select',
   'ui.bootstrap',
   'bm.bsTour',
   'ngColorThis',
   'toastr',
   'ngAnimate' 
 ]);

// Ao iniciar a app
app.run(['$rootScope', function ($r) {
        //console.log('app.run');
        $r.username = '';
        $r.password = '';
}]);


// configuracoes da app
app.config(['TourConfigProvider',
function (TourConfigProvider ) {
	
    TourConfigProvider.set('prefixOptions', false);
    TourConfigProvider.set('prefix', 'bsTour');
 
    
}]);


// Controller da view principal
app.controller( 'MainCtrl', ['$scope', '$http' ,'$interval', 'toastr', 'toastrConfig','uiGridConstants',
 function ($scope, $http, $interval, toastr, toastrConfig , uiGridConstants) {

   $http.defaults.headers.common.Authorization = '@#$%¨&';

   var openedToasts = [];
   $scope.options = {
     autoDismiss: false,
     position: 'toast-top-center',
     type: 'success',
     timeout: '2000',
     extendedTimeout: '1000',
     html: true,
     closeButton: true,
     tapToDismiss: true,
     progressBar: true,
     closeHtml: '<button>&times;</button>',
     newestOnTop: true,
     maxOpened: 0,
     preventDuplicates: false,
     preventOpenDuplicates: false
   };

	 //periodo
     $scope.periodo = {
        startDate: moment().subtract(1,"days"),
        endDate: moment()
    };


	// opções do periodo de datas
	$scope.opts = {
	    format: 'DD/MM/YYYY',
	    locale: {
	        applyClass: 'btn-green',
	        applyLabel: "Aplicar",
	        fromLabel: "De",
	        toLabel: "Hasta",
	        cancelLabel: 'Cancelar',
	        customRangeLabel: 'Especificar',
	        daysOfWeek: ['Do', 'Lu', 'Ma', 'Mi', 'Ju', 'Vi', 'Sá'],
	        firstDay: 1,
	        monthNames: ['Enero', 'Febrero', 'Marzo', 'Abril', 'Mayo', 'Junio', 'Jullo', 'Agosto', 'Septiembre',
	            'Octubre', 'Noviembre', 'Diciembre'
	        ]
	    },
	    ranges: {
	        'Hoy': [moment(), moment()],
	        'Últimos 7 días': [moment().subtract(6,'days'), moment()],
	        'Últimos 30 días': [moment().subtract(29,'days'), moment()]
	    }
	};

   // poppular departamentos
   $http.get('/api/deps')
   .success(function (data) {
     $scope.deptosSelecionados = data;
     $scope.deptos= data;
   })
   .finally(function () {
     //
   });

  // gerar json a partit do financeiro
  $scope.getBlob = function(financeiro,situacao){
	  
	     
	  	  console.log('Situacao: '+situacao);
	  	  
	  	  if ( situacao == 'E' ){
	  	  
				openedToasts.push(
				toastr.warning(
						 'JSON del financeiro '+financeiro+' enviado al sistema tesakã. Generado solamente para verificación.',
						 'AVISO: JSON ENVIADO.' 
				));

		  }
	  	  if ( situacao == 'P' ){
		  	  
				openedToasts.push(
				toastr.warning(
						 'JSON del financeiro '+financeiro+' processado. Generado solamente para verificación.',
						 'AVISO: JON PROCESSADO.' 
				));
			
          }
				

          $http.get('api/retencaoJson/'+financeiro )
          .success( function (data) {
        	    var json = JSON.stringify(data,null,"    ");
        	    console.log(json);
        	  	var blob =  new Blob([json], {type: "application/json"});
        	    var downloadLink = angular.element('<a></a>');
                downloadLink.attr('href',window.URL.createObjectURL(blob));
                downloadLink.attr('download', 'DolphinRTV'+financeiro+'.txt');
                downloadLink[0].click();

          })
          .error( function(data){
        	  		console.log(data);
					openedToasts.push(
					toastr.error(
							 'Problema en el dercargar del archivo JSON. ',
							 'ERRO: 06 - Problema en el servidor.' 
					));
      	  

          });
          

          //var dd = {f: financeiro, a:11, b:444, c:333};
          //var json = JSON.stringify(dd);
          //return new Blob([json], {type: "application/json"});
  };

  // configuracoes do grid de retencoes
  $scope.gridOptions = {
                        
    enableFiltering: true,
    enablePaginationControls: true,
    enableGridMenu: true,
    paginationPageSize: 8,
    paginationPageSizes: [8, 16, 32],
     
    columnDefs: [
                 
      { name: 'codigo'      , field:'id.codigo' , displayName:'Código', width: 60 , enableColumnMenu: false },
      { name: 'item'        , field:'id.item'   , displayName:'Iten', width: 60 , enableColumnMenu: false },
      { name: 'entidade'    , field:'entidade'  , displayName:'Entidad', enableColumnMenu: false, enableSorting: true },
      { name: 'cruc'        , field:'ruc'       , displayName:'RUC', enableColumnMenu: false, enableSorting: true, width: 80 },
      { name: 'venda' 		, field:'comprovanteVenda' ,  displayName:'Comprob.Venta', enableColumnMenu: false,  width: 115 },
      { name: 'comprovante' , field:'comprovanteRetencao' ,  displayName:'Comprob.Retención', enableColumnMenu: false,  width: 139 },
      { name: 'situacion'   , field:'situacao'  , displayName:'Situación', enableColumnMenu: false,
		  		   cellTemplate: 'app/views/situacao.html' , width: 136, 
		  		   filter: { type: uiGridConstants.filter.SELECT, selectOptions: [ 
	                   {value: 'N', label: 'No enviado'},
	                   {value: 'E', label: 'Enviado'},
                       {value: 'P', label: 'Processado'},]},cellFilter: 'mapSituacao'
      },
      { name: 'data'        , field:'dataRetencao' ,  displayName:'Data Retención', enableColumnMenu: false,  width: 130, enableFiltering:false ,  cellFilter: 'date:\'dd/MM/yyyy\''},
      { name: 'acao'        , field:'id.codigo' , displayName:'Bajar',
 	               cellTemplate: 
 	            	   '<div class="ui-grid-cell-contents pull-right" ng-controller="MainCtrl"> ' +
 	            	   //'{{row.entity.situacao}} / {{row.getProperty(col.field)}}'+
 	            	   '<a class="btn  btn-primary btn-xs" ng-click="getBlob( COL_FIELD, row.entity.situacao )" href="#"><span class="glyphicon glyphicon-cloud-download" aria-hidden="true"></span> JSON</a>'+ 	            	   
	                   //'<my-download id="content" get-data="getBlob( COL_FIELD, row.entity.situacao )" />' +
	                   '</div>', width: 70, enableColumnMenu: false, enableSorting: false, enableFiltering:false
      }
    ]
  };

  app.filter('mapSituacao', function() {
	  var situacaoHash = {
	    'N': 'No enviado',
	    'E': 'Enviado',
	    'P':'Processado'
	  };
  
		return function(input) {
		  if (!input){
		    return '';
		  } else {
		    return situacaoHash[input];
		  }
		};
  });
  
  
  
  // retorna situacao do comprovante
  $scope.getSituacao = function (tipo) {
	  //console.log(tipo);
	  if ( tipo == 'N' ){ return 'No enviado'; }
	  else if ( tipo == 'E' ){ return 'Enviado'; }
	  else if ( tipo == 'P' ){ return 'processado'; }
      return tipo;
  };


  // popular gride de retencoes
  $scope.refresh = function() {

    var params =
     '?data1=' + $scope.periodo.startDate.format('MM/DD/YYYY') +
     '&data2=' + $scope.periodo.endDate.add(1,'days').format('MM/DD/YYYY') +
     '&deps='  + $scope.depsToString();
      console.log( params );

    $http.get('/api/listarRetencaoFiltro'+ params )
    .success(function (data) {
          $scope.gridOptions.data = data;
          
              if ( data.length == 0 ){
                  openedToasts.push(
             	         toastr.warning(
             	             	  'Fecha inicio:'+$scope.periodo.startDate.format('DD/MM/YYYY')+
             	        		  '<br> Fecha fim: '+ $scope.periodo.endDate.format('DD/MM/YYYY') +
             	              	  '<br> Deptos: '+$scope.depsToString(),
             	              	  'Ningun registro encontado'
             	      ));
            	  
              }
              else
            	  {
                  openedToasts.push(
             	         toastr.success(
             	             	  'Fecha inicio:'+$scope.periodo.startDate.format('DD/MM/YYYY')+
             	        		  '<br> Fecha fim: '+ $scope.periodo.endDate.format('DD/MM/YYYY') +
             	              	  '<br> Deptos: '+$scope.depsToString() +
             	              	  '<br><kbd>' + data.length+ '</kbd> registros encontados' ,
             	              	  'Filtro'
             	      ));
            	  
            	  }

          
          
          
    })
    .error(function (data) {
    	// $scope.gridOptions.data =[];
    })
    .finally(function () {
    	// $scope.gridOptions.data = data;
    });

  };


  // guarda conteudo do JSON para update
  $scope.showContent = function($fileContent){
      $scope.content = $fileContent;
     
  };


  // update JSON
  $scope.updateJson = function() {

	//console.log($scope.content);  
	  
	// verifica JSON
  	if ( $scope.content === undefined ){
      openedToasts.push(
         toastr.error(
        		 'Selecione uma archivo JSON validado pelo sistema tesaka',
        		 'ERRO: 01 - Ningun arquivo selecionado.' 
      ));

  		return;
  	}

    // parse JSON
  	try {
        var json = angular.fromJson( $scope.content ) ;
        //console.log( json );
  	}
  	catch(err) { // err.message;
            openedToasts.push(
            		  toastr.error(
               		   'Selecione uma archivo JSON validado pelo sistema tesaka',
               		   'ERRO: 02 - Archivo JSON inválido.' 
             ));
      		return;
   	}

  	// verifica comprovante retencao
  	try {
		//$scope.comprovanteRetencao =  json[0].recepcion.numero;
		//console.log($scope.comprovanteRetencao);
  	}
  	catch(err) { // err.message;
        openedToasts.push(
      		  toastr.error(
         		   '',
         		   'ERRO: 03- Ningun comprobante de retencion encontrado.' 
       ));
  		return;
  	}


  	// verifica comprovante venda
  	try {
		//$scope.comprovanteVenda = json[0].datos.transaccion.numeroComprobanteVenta;
		//console.log($scope.comprovanteVenda);
  	}
  	catch(err) {  // err.message;
        openedToasts.push(
        		  toastr.error(
           		   '',
           		   'ERRO: 04- Ningun comprobante venta encontrado.' 
         ));
  		return;
  	}
  	

    // verifica timbrado
  	try {
		//$scope.numeroTimbrado = json[0].datos.transaccion.numeroTimbrado;
		//console.log($scope.numeroTimbrado);
  	}
  	catch(err) {  // err.message;
        openedToasts.push(
        		  toastr.error(
           		   '',
           		   'ERRO: 05- Ningun número de timbrado encontrado.' 
         ));
  		return;
  	}
  	
    var i;
	for (i = 0; i < json.length; i++){
		
		   console.log(json[i].estado);
		   console.log( json[i].estado == 'enviado'  );
  	
			if ( json[i].estado == 'enviado' ) {
				
			
		  	$scope.comprovanteRetencao =  json[i].recepcion.numero;	
			$scope.comprovanteVenda = json[i].datos.transaccion.numeroComprobanteVenta;
			$scope.numeroTimbrado = json[i].datos.transaccion.numeroTimbrado;
			
			console.log('ok');
			console.log($scope.comprovanteRetencao);
			console.log($scope.comprovanteVenda);
			console.log($scope.numeroTimbrado);
	
			// processa JSON
		  	$http.post( '/api/atualizaComprovanteRetencao/' +
		  			    $scope.comprovanteRetencao+ '/' +
		  			    $scope.comprovanteVenda+'/'+
		  			    $scope.numeroTimbrado)
		      .success( function(response){
		          openedToasts.push(toastr.success(
		        		  'Comprovante de venta:<br>'+$scope.comprovanteVenda+
		        		  '<br>Comprovante de Retención:<br>'+$scope.comprovanteRetencao,
		        		  'JSON cargado com sucesso.'));
		      })
		      .error(function (data) {
		    	  $scope.ComprovanteVenda='';
		    	  $scope.ComprovanteRetencao='';
		          openedToasts.push(
		        		  toastr.error(
		           		   'Problema en el processamiento del archivo JSON en el servidor.',
		           		   'ERRO: 05 - Problema en el servidor.' 
		          ));
		      });
		  	
		    }
  	
	}

  };


 // lista de depts em format string
 $scope.depsToString = function(){
	var result = '';
	var i=0;
	for (i=0;i<$scope.deptosSelecionados.length;i++){
	    result += $scope.deptosSelecionados[i].id+',';
	}
	return result;
	console.log( result );
 };


}]);


// diretiva para download do JSON
app.directive('myDownload', function ($compile) {
    return {
        restrict:'E',
        scope:{ getUrlData:'&getData'},
        link:function (scope, elm, attrs) {
            var url = URL.createObjectURL(scope.getUrlData());
            elm.append($compile(
                '<a class="btn  btn-primary btn-xs" download="rtv.json"' +
                    'href="' + url + '"><span class="glyphicon glyphicon-cloud-download" aria-hidden="true"></span>  ' +
                    'JSON' +
                    '</a>'
            )(scope));
        }
    };
});


// controle do upload do JSON
app.controller('ReadCtrl', function ($scope,$http) {

    // envia JOSN - Obsolete
    $scope.enviarJson = function(){

     /*
		 * var req = { method: 'POST', url: '/und/upload', headers: {
		 * 'Content-Type': 'application/x-www-form-urlencoded' }, data: { json:
		 * $scope.content } };
		 */

      $http.post( '/api/upload','json=' + $scope.content.toString() )
      .then( function(response){
        console.log(response);
      },function(response) {
    	 console.log(response);
      });;

    };

});

// diretiva help leitura de arquivo
app.directive('onReadFile', function ($parse) {
	return {
		restrict: 'A',
		scope: false,
		link: function(scope, element, attrs) {
            var fn = $parse(attrs.onReadFile);

			element.on('change', function(onChangeEvent) {
				var reader = new FileReader();

				reader.onload = function(onLoadEvent) {
					scope.$apply(function() {
						fn(scope, {$fileContent:onLoadEvent.target.result});
					});
				};

				reader.readAsText((onChangeEvent.srcElement || onChangeEvent.target).files[0]);
			});
		}
	};
});



angular.module('app').controller('ModalDemoCtrl', function ($scope, $modal, $log) {

	  $scope.items = ['item1', 'item2', 'item3'];

	  $scope.animationsEnabled = true;

	  $scope.open = function (size) {

	    var modalInstance = $modal.open({
	      animation: $scope.animationsEnabled,
	      templateUrl: 'app/views/modal-login.html',
	      controller: 'ModalInstanceCtrl',
	      size: size,
	      resolve: {
	        items: function () {
	          return $scope.items;
	        }
	      }
	    });

	    modalInstance.result.then(function (selectedItem) {
	      $scope.selected = selectedItem;
	    }, function () {
	      //$log.info('Modal dismissed at: ' + new Date());
	    });
	  };

	  $scope.toggleAnimation = function () {
	    $scope.animationsEnabled = !$scope.animationsEnabled;
	  };

});


// Please note that $modalInstance represents a modal window (instance)
// dependency.
// It is not the same as the $modal service used above.
angular.module('app').controller('ModalInstanceCtrl', function ($scope, $modalInstance, items) {

	  $scope.items = items;
	  $scope.selected = {
	    item: $scope.items[0]
	  };

	  $scope.ok = function () {
	    $modalInstance.close($scope.selected.item);
	  };

	  $scope.cancel = function () {
	    $modalInstance.dismiss('cancel');
	  };

});



	app.controller('ColorCtrl', function ($scope, $interval) {


          $scope.situacoes = ['No processado','Enviado','Processado'];

		  $scope.progressBar = 50;
		  $scope.selectedText = 'angular-color-this';

		  $interval(function () {
		    if ($scope.progressBar >= 100) {
		      $scope.progressBar = 0;
		    }
		    $scope.progressBar += 1;
		  }, 500 );

		});
