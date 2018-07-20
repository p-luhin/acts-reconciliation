'use strict';

angular.module('acts').factory('uploadService', function ($http) {

  return {
    upload: upload
  };

  function upload(firstFile, secondFile) {
    let fd = new FormData();
    fd.append('firstFile', firstFile);
    fd.append('secondFile', secondFile);

    return $http.post('/api/acts/show', fd, {
      headers: {'Content-Type': undefined}
    });
  }
});