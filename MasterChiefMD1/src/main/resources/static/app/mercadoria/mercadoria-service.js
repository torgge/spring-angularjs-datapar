'use strict';

angular.module('app')
    .factory('mercadoriaService', ['$http', function($http) {

    var urlBase = 'api/mercaderia';
    var dataFactory = {};

    dataFactory.getMercaderias = function () {
        return $http.get(urlBase);
    };

    dataFactory.getMercaderia = function (id) {
        return $http.get(urlBase + '/' + id);
    };

    dataFactory.insertMercaderia = function (merc) {
        return $http.post(urlBase, merc);
    };

    dataFactory.updateMercaderia = function (merc) {
        return $http.put(urlBase + '/' + merc.ID, merc)
    };

    dataFactory.deleteMercaderia = function (id) {
        return $http.delete(urlBase + '/' + id);
    };

    return dataFactory;
}]);