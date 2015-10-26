'use strict';

angular.module('app')
    .factory('EnqueteService', ['$http', function($http) {

    var urlBase = 'api/enquete/';
    var dataFactory = {};

    dataFactory.getAtiva = function () {
        return $http.get(urlBase+'/ativa');
    };

    dataFactory.getVotacaoById = function (id) {
        return $http.get(urlBase+'enquete/'+id);
    };

    return dataFactory;
}]);