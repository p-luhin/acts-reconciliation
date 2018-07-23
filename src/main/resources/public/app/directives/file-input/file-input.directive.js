'use strict';

angular.module("acts").directive("fileInput", function () {
  return {
    require: "ngModel",
    link: function postLink(scope, elem, attrs, ngModel) {
      elem.on("change", function (e) {
        let files = elem[0].files;
        ngModel.$setViewValue(files[0]);
      })
    }
  }
});