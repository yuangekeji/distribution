angular.module('admProduct').controller('admProductAddCtrl',function ($q, title, $scope, $http,  $state, Notify, $sessionStorage) {
    title.setTitle('添加商品');
    $scope.goods = {};
    $scope.submitFlag = true;
    $scope.add = function () {
        if($scope.submitFlag) {
            $scope.submitFlag = false;
            if($scope.check()){
                $scope.startLoading();
                $http.post(ctx + "/admGoods/insert", $scope.goods).success(function (resp) {
                    if(resp.successful){
                        $scope.stopLoading();
                        $state.go("app.admProduct");
                    }else{
                        $scope.stopLoading();
                        console.log(resp);
                    }
                }).error(function (resp) {
                    $scope.stopLoading();
                    console.log(resp);
                })
            }
        }
    };

    $scope.check = function () {
          if(!$scope.goods.goodsName||!$scope.goods.goodsName.trim()){
              $scope.submitFlag = true;
              Notify.warning("请输入商品名称。");
          }else if(!$scope.goods.goodsPrice||!$scope.goods.goodsPrice.trim()){
              $scope.submitFlag = true;
              Notify.warning("请输入商品价格。");
          }else if(!/^\+?[1-9][0-9]*$/.test($scope.goods.goodsPrice.trim())){
              $scope.submitFlag = true;
              Notify.warning("商品价格只能为正整数，请重新输入。");
          }else if(!$scope.goods.goodsNum||!$scope.goods.goodsNum.trim()){
              $scope.submitFlag = true;
              Notify.warning("请输入商品库存。");
          }else if(!/^\+?[1-9][0-9]*$/.test($scope.goods.goodsNum.trim())){
              $scope.submitFlag = true;
              Notify.warning("商品库存只能为正整数，请重新输入。");
          }else if(!$scope.goods.goodsType){
              $scope.submitFlag = true;
              Notify.warning("请选择商品类型。");
          }else if(!$scope.goods.info||!$scope.goods.info.trim()){
              $scope.submitFlag = true;
              Notify.warning("请输入商品介绍。");
          }else{
              return true;
          }
    };

    /**loading*/
    var e1 = $('.portlet');
    $scope.startLoading=function () {
        App.blockUI({
            target: e1,
            animate: true,
            overlayColor: 'none'
        });
    };

    $scope.stopLoading=function () {
        App.unblockUI(e1);
    };
});

