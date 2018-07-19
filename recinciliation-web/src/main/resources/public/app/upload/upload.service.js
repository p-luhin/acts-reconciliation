'use strict';

angular.module('acts').factory('uploadService', function ($http) {

  return {
    upload: upload
  };

  function upload(file) {
    let fd = new FormData();
    fd.append('file', file);

    return $http.post('/api/acts/show', fd, {
      headers: {'Content-Type': undefined}
    });
  }
});