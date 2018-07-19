'use strict';

angular.module('acts').controller('uploadCtrl',
    function ($scope, $location, uploadService) {

      $scope.uploadFile = function (file) {
        uploadService.upload(file).then(response => {
          console.log(response.data);
          $scope.result = response.data;
        })
      };

      $('.custom-file-input').on('change', function () {
        let fileName = $(this).val().split('\\').pop();
        $(this).next('.custom-file-label').addClass("selected").html(fileName);
      });
    });