'use strict';

angular.module('acts').controller('uploadCtrl',
    function ($scope, $location, uploadService) {

      $scope.uploadFile = uploadFile;

      function uploadFile(file) {
        console.log("test");
        uploadService.upload(file).then(result => {
          $scope.result = result;
        })
      }

      $('.custom-file-input').on('change', function () {
        let fileName = $(this).val().split('\\').pop();
        $(this).next('.custom-file-label').addClass("selected").html(fileName);
      });
    });