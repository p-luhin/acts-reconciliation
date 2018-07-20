'use strict';

angular.module('acts').config(function ($routeProvider) {
  $routeProvider
  .when('/upload', {
    templateUrl: 'app/upload/upload.tmpl.html',
    controller: 'uploadCtrl',
    controllerAs: 'ctrl'
  })
  .when('/login', {
    templateUrl: 'app/login/login.tmpl.html',
    controller: 'loginCtrl',
    controllerAs: 'ctrl'
  })
});