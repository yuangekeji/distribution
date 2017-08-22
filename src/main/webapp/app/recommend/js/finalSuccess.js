angular.module('recommend').controller('recommendFinalSuccessController',
    function (title, $scope, $state, $stateParams, $sessionStorage) {
        title.setTitle('预约成功');
        $scope.startTime = $stateParams.startTime ? $stateParams.startTime : '';
        $scope.endTime = $stateParams.endTime ? $stateParams.endTime : '';
        $scope.shopName = $stateParams.shopName;
        $sessionStorage.borrowAmount = null;
        $sessionStorage.state = null;
        $sessionStorage.again = null;
        $sessionStorage.goodsList = null;
        $sessionStorage.goodsCache = null;
        $sessionStorage.searchFlag = undefined;
        $scope.gotoListPage = function () {
            $state.go('app.recommend-list');
        }
    });
