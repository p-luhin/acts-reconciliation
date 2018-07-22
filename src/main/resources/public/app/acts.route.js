'use strict';

angular.module('acts').config(function ($routeProvider) {
  $routeProvider
  .when('/upload', {
    templateUrl: 'app/upload/upload.tmpl.html',
    controller: 'uploadCtrl'
  })
  .when('/login', {
    templateUrl: 'app/login/login.tmpl.html',
    controller: 'loginCtrl'
  })
  .when('/config', {
    templateUrl: 'app/config/config.tmpl.html',
    controller: 'configCtrl',
  })
});