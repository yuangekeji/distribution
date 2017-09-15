angular.module('product').controller('productAddCtrl',function ($q, title, $scope, $http,  $state, $stateParams, $sessionStorage, Notify) {
    title.setTitle('商品详情');

    $scope.notData = false;
    $scope.goods = {};

    $scope.search = function(){
        $http.get(ctx + '/goods/add/'+$stateParams.id).success(function (resp) {
            if(resp.successful){
                $scope.goods = resp.data.goods;
            }else{
                Notify.error("获取商品详细信息错误，请重新尝试");
            }
        }).error(function (error) {
            Notify.error(error);
        });
    }

    $scope.goAdd = function (id) {
        if( !$scope.advanceValidate()) {
            Notify.warning('请填写购买数量。');
            return false;
        }

        if($scope.orderQty > $scope.goods.goodsNum){
            Notify.warning('库存不足，请重新填写购买数量。');
            return false;
        }

        $state.go('app.productAdd', {id: id, orderQty:$scope.orderQty});
    }

    /**
     * 初始化
     */
    $scope.onInit = function () {

        $scope.search();
    };


    $scope.onInit();


    $scope.returnList = function () {
        $state.go('app.product');
    }

    $scope.advanceValidateConditionArray = {
        orderQtyError: function () {
            if (angular.isUndefined($scope.orderQty) || !(/^\+?[1-9][0-9]*$/.test($scope.orderQty))) {
                $scope.advanceValidateErrors.orderQtyError = true;
            }
        }
    }
});

