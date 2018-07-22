'use strict';

angular.module('acts').controller('configCtrl',
    function ($scope, $uibModal, configService) {
      activate();

      $scope.openModalAddConfig = openModalAddConfig;

      function activate() {
        configService.getAllConfigs().then(response => {
          $scope.configs = response.data;
        })
      }

      function openModalAddConfig() {
        $uibModal.open({
          animation: true,
          ariaLabelledBy: 'modal-title',
          ariaDescribedBy: 'modal-body',
          templateUrl: 'app/modal/add-config/add-config.modal.tmpl.html',
          controller: 'addConfigModalCtrl',
          size: 'lg'
        }).result.then(function () {
          activate();
        });
      }
    });