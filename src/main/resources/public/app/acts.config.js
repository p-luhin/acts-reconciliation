'use strict';

angular
.module('acts')
.config(function config($httpProvider) {
  $httpProvider.interceptors.push('httpInterceptor');
});