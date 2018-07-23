'use strict';

angular
.module('acts')
.config(function config($httpProvider, $uibModalProvider) {
  $httpProvider.interceptors.push('httpInterceptor');
  $uibModalProvider.options.windowClass = 'show';
  $uibModalProvider.options.backdropClass = 'show';
});