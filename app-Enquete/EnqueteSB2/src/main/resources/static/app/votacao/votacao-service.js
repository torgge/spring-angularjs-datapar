'use strict';

angular.module('app')
    .factory('VotacaoService', ['$http', function($http) {

    var urlBase = 'api/';
    var dataFactory = {};

    dataFactory.getVotacao = function () {
        return $http.get(urlBase+'votacao');
    };

    dataFactory.getVotacaoById = function (id) {
        return $http.get(urlBase+'votacao/'+id);
    };

    dataFactory.getVotacaoEncerradas = function () {
        return $http.get(urlBase+'encerradas');
    };

    return dataFactory;
}]);