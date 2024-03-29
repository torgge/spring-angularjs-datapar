'use strict';

angular.module('app')
    .factory('unidadeService', ['$http', function($http) {

    var urlBase = 'api/unidade';
    var dataFactory = {};

    dataFactory.getUnidades = function () {
        return $http.get(urlBase);
    };

    dataFactory.getUnidade = function (id) {
        return $http.get(urlBase + '/' + id);
    };

    dataFactory.insertUnidade= function (merc) {
        return $http.post(urlBase, merc);
    };

    dataFactory.updateUnidade = function (merc) {
        return $http.put(urlBase + '/' + merc.ID, merc)
    };

    dataFactory.deleteUnidade = function (id) {
        return $http.delete(urlBase + '/' + id);
    };

    return dataFactory;
}]);