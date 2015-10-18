'use strict';

angular.module('app')
    .factory('categoriaService', ['$http', function($http) {

    var urlBase = 'api/categoria';
    var dataFactory = {};

    dataFactory.getCategorias = function () {
        return $http.get(urlBase);
    };

    dataFactory.getCategoria = function (id) {
        return $http.get(urlBase + '/' + id);
    };

    dataFactory.insertCategoria = function (merc) {
        return $http.post(urlBase, merc);
    };

    dataFactory.updateCategoria = function (merc) {
        return $http.put(urlBase + '/' + merc.ID, merc)
    };

    dataFactory.deleteCategoria = function (id) {
        return $http.delete(urlBase + '/' + id);
    };

    return dataFactory;
}]);