'use strict';

angular.module('app')
    .factory('MercadoriaService', ['$http', function($http) {

    var urlBase = 'http://localhost:5000/api/mercaderia';
    var dataFactory = {};

    dataFactory.getMercadorias = function () {
        return $http.get(urlBase);
    };

    dataFactory.getMercadoria = function (id) {
        return $http.get(urlBase + '/' + id);
    };

    dataFactory.insertMercadoria = function (merc) {
        return $http.post(urlBase, merc);
    };

    dataFactory.updateMercadoria = function (merc) {
        return $http.put(urlBase + '/' + merc.ID, merc)
    };

    dataFactory.deleteMercadoria = function (id) {
        return $http.delete(urlBase + '/' + id);
    };

    return dataFactory;
}]);
