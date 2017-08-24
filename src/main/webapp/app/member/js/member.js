/**
 * Created by lijingx on 8/24/2017.
 */
angular.module('member').controller('memberCtrl',
    function ($q, title, $scope, $http,  $state, $stateParams, $sessionStorage) {
    title.setTitle('个人中心');
});