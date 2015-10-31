'use strict';

angular.module('app')
    .factory('PedidoService', ['$http', function($http) {

    var urlBase = 'http://localhost:5000/api/pedido';
    var dataFactory = {};

    dataFactory.getPedidos = function () {
        return $http.get(urlBase);
    };

    dataFactory.getPedido = function (id) {
        return $http.get(urlBase + '/' + id);
    };

    dataFactory.insertPedido= function (merc) {
        return $http.post(urlBase, merc);
    };

    dataFactory.updatePedido = function (merc) {
        return $http.put(urlBase + '/' + merc.ID, merc)
    };

    dataFactory.deletePedido = function (id) {
        return $http.delete(urlBase + '/' + id);
    };

    return dataFactory;
}]);