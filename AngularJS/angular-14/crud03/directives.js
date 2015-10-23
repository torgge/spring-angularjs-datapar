'use strict';

/**
 * @ngdoc directives
 * @name navbar
 * @id directives
 * @description This is a dummy directive.
 *
 * @param {string} apText Sample text.
 *
 * ###Additional information
 * You can write something else if you want.
 */
angular.module('plunkerApp')
  .directive('navbar', function () {
    return {
      restrict: 'E',
      templateUrl: 'views/directives/navbar.html',
      controller: 'NavbarCtrl',
    };
  });

angular.module('plunkerApp')
  .directive('qsFocus', function () {
    return {
      restrict: 'A',
      require: 'ngModel',
      link: function(scope, element, controller) {
        controller.$focused = false;
        element.bind('focus', function(){
          scope.$apply(function(){
            controller.$focused = true;
          });
        }).bind('blur', function(){
          scope.$apply(function(){
            controller.$focused = false;
          });
        });

        scope.$on('$destroy', function() {
          element.unbind('blur focus');
        });
      }
    };
  });