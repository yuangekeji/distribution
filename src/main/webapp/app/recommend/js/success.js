angular.module('recommend').controller('recommendSuccessController', function (title, $scope, $http, $state, $stateParams) {
    title.setTitle('提交成功');
    $scope.recommendId = $stateParams.recommendId ? $stateParams.recommendId : '';
    $scope.rebateAmountMin = $stateParams.rebateAmountMin ? $stateParams.rebateAmountMin : '';
    $scope.rebateAmountMax = $stateParams.rebateAmountMax ? $stateParams.rebateAmountMax : '';
    $scope.goodsType = $stateParams.goodsType ? $stateParams.goodsType : '';
    $scope.pawnId = $stateParams.pawnId ? $stateParams.pawnId : '';
    $scope.gotoListPage = function () {
        $state.go('app.recommend-list');
    }
    $scope.chooseShopPage = function () {
        $state.go('app.chooseShop', {
            goodsType: $scope.goodsType,
            pawnId: $scope.pawnId,
            state: 'recommend',
            recommendId: $scope.recommendId
        });
    }
});