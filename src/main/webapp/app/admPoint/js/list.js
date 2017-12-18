angular.module('admPoint').controller('admPointCtrl',function ($q, title, $scope, $http, $state, $stateParams, $sessionStorage, Notify, $uibModal, ConfirmModal) {
    title.setTitle('积分兑换管理');
    $scope.myPage = {
        pageNo: 1,
        pageSize: 10,
        totalCount: 0,
        result: [],
        parameterMap: {
            orderNo: "",
            orderStatus: "",
            startTime: "",
            endTime: "",
            sendByPostYN:""
        }
    };

    $scope.orderExpress = {
        id: '',
        orderNo: '',
        statues: '',
        expressNo: ''
    };
    $scope.expressMessage = {
        receiveName:'',
        recevivePhone:'',
        expressNo: '',
        expressAddress: '',
        sendbypostyn: '',
        orderStatues: ''
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
     * 查询兑换单列表
     */
    $scope.search = function(){
        $http.post(ctx + '/admPoint/list', $scope.myPage)
            .success(function (resp) {
                if (resp.successful) {
                    $scope.myPage = resp.data;
                    $scope.notData = false;
                    if (!$scope.myPage.result || $scope.myPage.result.length == 0) $scope.notData = true;
                } else {
                    Notify.error("查询兑换单列表失败，请重新尝试");
                    console.log(resp.errorMessage);
                }
            }).error(function (error) {
            Notify.error("查询兑换单列表失败，请重新尝试");
            console.error(error);
        });
    }

    /**确认发货*/
    $scope.confirmOrder = function (id, orderNo, statues, expressNo) {
        $scope.orderExpress.id = id;
        $scope.orderExpress.orderNo = orderNo;
        $scope.orderExpress.statues = statues;
        $scope.orderExpress.expressNo = expressNo;
        $scope.open();

    };

    $scope.open = function(opt_attributes)
    {
        var out = $uibModal.open(
            {
                animation: true,
                backdrop: 'static',
                templateUrl: "admPointExpress.html",
                controller: "admPointExpressCtrl",
                size: opt_attributes,
                resolve: {
                    getDatas: function()
                    {
                        return $scope.orderExpress;
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
    };

    /**物流信息查询*/
    $scope.expressMessageSearch = function (receiveName,recevivePhone,expressNo, expressAddress, sendbypostyn, orderStatues) {
        $scope.expressMessage.receiveName =receiveName;
        $scope.expressMessage.recevivePhone =recevivePhone;
        $scope.expressMessage.expressNo = expressNo;
        $scope.expressMessage.expressAddress = expressAddress;
        $scope.expressMessage.sendbypostyn = sendbypostyn;
        $scope.expressMessage.orderStatues = orderStatues;
        $scope.openexpressMessage();

    };

    $scope.openexpressMessage = function(opt_attributes)
    {
        var out = $uibModal.open(
            {
                animation: true,
                backdrop: 'static',
                templateUrl: "admExpressMessage.html",
                controller: "admExpressMessageCtrl",
                size: opt_attributes,
                resolve: {
                    getDatas: function()
                    {
                        return $scope.expressMessage;
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
    };
    /**
     * 管理员确认收货
     * @param id
     * @param orderNo
     * @param statues
     */
    $scope.adminConfirmOrder = function (id, orderNo, statues, flag) {
        var message = '确定已经收到商品了吗？';
        if (flag == 's') {
            message = '确定发货撤销了吗？';
        }else if(flag == 'r') {
            message = '确定收货撤销了吗？';
        }
        ConfirmModal.show({
            text: message,
            isCancel:true //false alert ,true confirm
        }).then(function (sure) {
            if (!sure) {
                return;
            }
            $scope.startLoading();
            $http.post(ctx + "/admPoint/confirmReceiveOrder", {id: id, orderNo: orderNo, orderStatues: statues}).success(function (resp) {
                if (resp.successful) {
                    Notify.success("操作完成。");
                    $scope.search();
                } else {
                    Notify.error(resp.error());
                }
                $scope.stopLoading();
            }).error(function (error) {
                Notify.error(error);
                $scope.stopLoading();
            });
        });
    };

    /**
     * 全部下载
     */
    $scope.excelDownloadAll = function () {
        window.location.href=ctx + "/admPoint/excelDownloadAll?orderNo="+this.myPage.parameterMap.orderNo+"&orderStatus="+this.myPage.parameterMap.orderStatus+"&startTime="+this.myPage.parameterMap.startTime+"&endTime="+this.myPage.parameterMap.endTime;
    };
    /**
     * 初始化
     */
    $scope.onInit = function () {

        $scope.search();
    };

    $scope.onInit();

    /**
     * 查询按钮触发
     */
    $scope.searchByParam =function () {

        $scope.myPage.pageNo = 1;
        $scope.search();

    }

    /**
     * 分页触发
     * @param num
     */
    $scope.pageChangeHandler = function(num) {
        $scope.myPage.pageNo = num;
        $scope.search();
    };

});

angular.module('admPoint').controller('admPointExpressCtrl', function ($q, title, $scope, $http,  $state, $stateParams, $sessionStorage, $uibModalInstance,getDatas, Notify) {

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

    $scope.close = function()
    {
        $uibModalInstance.close(true);
    };
    $scope.cancel = function()
    {
        $uibModalInstance.dismiss('cancel');
    };


    /**确认发货提交*/
    $scope.submit = function (id, orderNo, statues, expressNo) {
        $scope.startLoading();
        $http.post(ctx + "/admPoint/confirmSendOrder",{id:id, orderNo:orderNo, orderStatues:statues, expressNo:expressNo}).success(function (resp) {
            if(resp.successful){
                Notify.success("确认发货完成");
                $uibModalInstance.close(true);
                $state.go("app.admPoint", {}, {reload: true});
            }else{
                Notify.error(resp);
            }
            $scope.stopLoading();
        }).error(function (error) {
            Notify.error(error);
            $scope.stopLoading();
        })
    };
});
angular.module('admPoint').controller('admExpressMessageCtrl', function ($q, title, $scope, $http,  $state, $stateParams, $sessionStorage, $uibModalInstance,getDatas) {

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

    $scope.datasExpressMessage = getDatas;

    $scope.close = function()
    {
        $uibModalInstance.close(true);
    };
    $scope.cancel = function()
    {
        $uibModalInstance.dismiss('cancel');
    };
});
angular.module('admPoint').filter("pointOrderStatuesFilter",function () {
    return function (input) {
        if(input=='1'){return '待支付'};
        if(input=='2'){return '待发货'};
        if(input=='3'){return '待收货'};
        if(input=='4'){return '兑换单完成'};
    }
});