'use strict';

angular.module('acts').controller('configCtrl',
    function ($scope, configService) {
      activate();

      function activate() {
        configService.getAllConfigs().then(response => {
          $scope.configs = response.data;
        })
      }
    });