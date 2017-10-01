angular.module('product').controller('productDetailCtrl',function ($q, title, $scope, $http,  $state, $stateParams, $sessionStorage, Notify,$uibModal) {
    title.setTitle('商品详情');
    $scope.user = $sessionStorage.currentUser;
    $scope.notData = false;
    $scope.goods = {};
    $scope.goodsOrder = {
        goodsCd     : "",
        goodsName   : "",
        goodsPrice  : "",
        orderQty    : "",
        receiveName : "",
        expressAddress : "",
        recevivePhone  : "",
        remark         : "",
        orderCategory  : "3" //折扣订单
    };
    //个人信息
    $scope.MemberInfo = {
        memberName      : '',
        memberPhone     : '',
        expressAddress  : ''
    };

    var e1 = $('.portlet');
    $scope.startLoading=function () {
        App.blockUI({
            target: e1,
            animate: true,
            overlayColor: 'none'
        });
    }
    $scope.stopLoading=function () {
        App.unblockUI(e1);
    }

    /**
     * 页面初始化查询个人信息
     */
    $scope.getMemberInfo = function () {
        $scope.startLoading();
        $http.get(ctx + '/member/getMemberInfoById/'+$scope.user.id).success(function (resp) {
            if(resp.successful){
                $scope.MemberInfo = resp.data.member;
            }else{
                Notify.error("获取个人信息错误，请重新尝试");
            }
            $scope.stopLoading();
        }).error(function (error) {
            Notify.error(error);
            $scope.stopLoading();
        });
    }

    $scope.search = function(){
        $scope.startLoading();
        $http.get(ctx + '/goods/details/'+$stateParams.id).success(function (resp) {
            if(resp.successful){
                $scope.goods = resp.data.goods;
            }else{
                Notify.error("获取商品详细信息错误，请重新尝试");
            }
            $scope.stopLoading();
        }).error(function (error) {
            Notify.error(error);
            $scope.stopLoading();
        });
    }

    //立即购买跳转订单确认页面
    $scope.goAdd = function (opt_attributes) {
        if( !$scope.goodsValidate()) {
            Notify.warning('请填写购买数量。');
            return false;
        }

        if($scope.orderQty > $scope.goods.goodsNum){
            Notify.warning('库存不足，请重新填写购买数量。');
            return false;
        }

        $scope.goodsOrder.goodsCd        =  $scope.goods.id;
        $scope.goodsOrder.goodsName      =  $scope.goods.goodsName;
        $scope.goodsOrder.goodsPrice     =  $scope.goods.goodsPrice;
        $scope.goodsOrder.orderQty       =  $scope.orderQty;
        $scope.goodsOrder.receiveName    =  $scope.user.consignee;
        $scope.goodsOrder.expressAddress =  $scope.user.expressAddress;
        $scope.goodsOrder.recevivePhone  =  $scope.user.linkmanPhone;

        $scope.open();
    }

    $scope.open = function (opt_attributes) {
        var out = $uibModal.open(
            {
                animation: true,
                backdrop: 'static',
                templateUrl: "goodsOrder.html",
                controller: "goodsOrderCtrl",
                size: opt_attributes,
                resolve: {
                    getDatas: function()
                    {
                        return $scope.goodsOrder;
                    }
                }
            });
        out.result.then(function(value)
        {
            // console.info('确认');
        }, function()
        {
            // console.info('取消');
        });
    }

    /**
     * 初始化
     */
    $scope.onInit = function () {
        $scope.getMemberInfo();
        $scope.search();
    };


    $scope.onInit();


    $scope.returnList = function () {
        $state.go('app.product');
    }

    /**
     * 订单校验
     * @returns {*}
     */
    $scope.goodsValidate = function () {
        $scope.goodsValidateErrors={};

        for (conditionthis in $scope.goodsValidateConditionArray) {
            $scope.goodsValidateConditionArray[conditionthis]();
        }
        return $.isEmptyObject(this.goodsValidateErrors)
    }

    $scope.goodsValidateConditionArray = {
        orderQtyError: function () {
            if (angular.isUndefined($scope.orderQty) || !(/^\+?[1-9][0-9]*$/.test($scope.orderQty))) {
                $scope.goodsValidateErrors.orderQtyError = true;
            }
        }
    }
});

//订单确认
angular.module('product').controller('goodsOrderCtrl', function ($q, title, $scope, $http,  $state, $stateParams, $sessionStorage, $uibModalInstance,getDatas, Notify) {

    var e1 = $('.portlet');
    $scope.startLoading=function () {
        App.blockUI({
            target: e1,
            animate: true,
            overlayColor: 'none'
        });
    }
    $scope.stopLoading=function () {
        App.unblockUI(e1);
    }

    $scope.datas = getDatas;
    $scope.datas.sendbypostyn ='1';

    $scope.close = function()
    {
        $uibModalInstance.close(true);
    };
    $scope.cancel = function()
    {
        $uibModalInstance.dismiss('cancel');
    };


    /**折扣订单提交*/
    $scope.submit = function () {
        if( !$scope.orderValidate()) {
            Notify.warning('请填写完整的订单信息。');
            return false;
        }

        $scope.startLoading();
        $http.post(ctx + "/order/disOrder",
        {
            goodsCd     : $scope.datas.goodsCd,
            goodsNm     : $scope.datas.goodsName,
            goodsPrice  : parseInt($scope.datas.goodsPrice),
            orderQty    : parseInt($scope.datas.orderQty),
            receiveName : $scope.datas.receiveName,
            expressAddress : $scope.datas.expressAddress,
            recevivePhone  : $scope.datas.recevivePhone,
            remark         :  $scope.datas.remark,
            sendbypostyn:    $scope.datas.sendbypostyn,
            orderCategory  : "3" //折扣订单
        }).success(function (resp) {
            if(resp.successful){
                Notify.success("商品订单提交成功。");
                $uibModalInstance.close(true);
                $state.go("app.admOrder", {}, {reload: true});
                $state.go('app.productDetail', {id: $scope.datas.goodsCd}, {reload: true});
            }else{
                Notify.error(resp);
            }
            $scope.stopLoading();
        }).error(function (error) {
            Notify.error(error);
            $scope.stopLoading();
        })
    };

    /**
     * 订单校验
     * @returns {*}
     */
    $scope.orderValidate = function () {
        $scope.orderValidateErrors={};

        for (conditionthis in $scope.orderValidateConditionArray) {
            $scope.orderValidateConditionArray[conditionthis]();
        }
        return $.isEmptyObject(this.orderValidateErrors)
    }

    $scope.orderValidateConditionArray = {

        receiveNameError: function () {
            if ($scope.datas.sendbypostyn =='2' && (!$scope.datas.receiveName || angular.isUndefined($scope.datas.receiveName) ||
                $scope.datas.receiveName.length <= 0)) {
                $scope.orderValidateErrors.receiveNameError = true;
            }
        },
        expressAddressError : function () {
            if ($scope.datas.sendbypostyn =='2' && (!$scope.datas.expressAddress || angular.isUndefined($scope.datas.expressAddress) ||
                $scope.datas.expressAddress.length <= 0)) {
                $scope.orderValidateErrors.expressAddressError = true;
            }
        },
        recevivePhoneError : function () {
            if ($scope.datas.sendbypostyn =='2' && (!$scope.datas.recevivePhone || angular.isUndefined($scope.datas.recevivePhone) ||
                $scope.datas.recevivePhone.length <= 0)) {
                $scope.orderValidateErrors.recevivePhoneError = true;
            }
        }

    }

});