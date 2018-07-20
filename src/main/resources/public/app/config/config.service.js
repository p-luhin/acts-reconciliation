'use strict';

angular.module('acts').factory('configService', function ($http) {
  return {
    getAllConfigs: getAllConfigs
  };

  function getAllConfigs() {
    return $http.get('/api/acts/config/');
  }
});