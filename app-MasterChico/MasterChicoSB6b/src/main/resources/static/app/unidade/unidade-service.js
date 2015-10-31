'use strict';

angular.module('app')
    .factory('UnidadeService', ['$http', function($http) {

    var urlBase = 'http://localhost:5000/api/unidade';
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
