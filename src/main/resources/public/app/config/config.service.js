'use strict';

angular.module('acts').factory('configService', function ($http) {
  return {
    getAllConfigs: getAllConfigs,
    deleteAll: deleteAll
  };

  function getAllConfigs() {
    return $http.get('/api/acts/config/');
  }

  function deleteAll(ids) {
    return $http.delete('/api/acts/config/', ids);
  }
});