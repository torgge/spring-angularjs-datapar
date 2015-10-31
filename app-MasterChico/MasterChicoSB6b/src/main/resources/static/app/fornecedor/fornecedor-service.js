'use strict';

angular.module('app')
    .factory('FornecedorService', ['$http', function($http) {

    var urlBase = 'http://localhost:5000/api/fornecedor';
    var dataFactory = {};

    dataFactory.getFornecedores = function () {
        return $http.get(urlBase);
    };

    dataFactory.getFornecedor = function (id) {
        return $http.get(urlBase + '/' + id);
    };

    dataFactory.insertFornecedor= function (merc) {
        return $http.post(urlBase, merc);
    };

    dataFactory.updateFornecedor = function (merc) {
        return $http.put(urlBase + '/' + merc.ID, merc)
    };

    dataFactory.deleteFornecedor = function (id) {
        return $http.delete(urlBase + '/' + id);
    };

    return dataFactory;
}]);