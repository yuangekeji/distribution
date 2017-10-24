angular.module('admOrder').controller('admOrderCtrl',function ($q, title, $scope, $http, $state, $stateParams, $sessionStorage, Notify, $uibModal, ConfirmModal) {
    title.setTitle('订单管理');
    $scope.myPage = {
        pageNo: 1,
        pageSize: 10,
        totalCount: 0,
        result: [],
        parameterMap: {
            orderNo: "",
            orderCategory: "",
            orderStatus: "",
            startTime: "",
            endTime: ""
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
     * 查询订单列表
     */
    $scope.search = function(){
        $http.post(ctx + '/admOrder/list', $scope.myPage)
            .success(function (resp) {
                if (resp.successful) {
                    $scope.myPage = resp.data;
                    $scope.notData = false;
                    if (!$scope.myPage.result || $scope.myPage.result.length == 0) $scope.notData = true;
                } else {
                    Nodify.error("查询订单列表失败，请重新尝试");
                    console.log(resp.errorMessage);
                }
            }).error(function (error) {
            Nodify.error("查询订单列表失败，请重新尝试");
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
                templateUrl: "admOrderExpress.html",
                controller: "admOrderExpressCtrl",
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
    $scope.adminConfirmOrder = function (id, orderNo, statues) {
        ConfirmModal.show({
            text: '确定已经收到商品了吗？',
            isCancel:true //false alert ,true confirm
        }).then(function (sure) {
            if (!sure) {
                return;
            }
            $scope.startLoading();
            $http.post(ctx + "/admOrder/confirmReceiveOrder", {id: id, orderNo: orderNo, orderStatues: statues}).success(function (resp) {
                if (resp.successful) {
                    Notify.success("确认收货完成。");
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
     * excel download
     */
    $scope.excelDownload = function() {

        window.location.href=ctx + "/admOrder/excelDownload?orderNo="+this.myPage.parameterMap.orderNo+"&orderCategory="+this.myPage.parameterMap.orderCategory+"&orderStatus="+this.myPage.parameterMap.orderStatus+"&startTime="+this.myPage.parameterMap.startTime+"&endTime="+this.myPage.parameterMap.endTime;

        /*var blob = new Blob([document.getElementById('orderList').innerHTML], {
            type: "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet;charset=utf-8"
        });
        saveAs(blob, "订单列表.xls");*/
    };

    $scope.excelDownload1 = function () {
        window.location.href=ctx + "/admOrder/excelDownload1?orderNo="+this.myPage.parameterMap.orderNo+"&orderCategory="+this.myPage.parameterMap.orderCategory+"&orderStatus="+this.myPage.parameterMap.orderStatus+"&startTime="+this.myPage.parameterMap.startTime+"&endTime="+this.myPage.parameterMap.endTime;
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

angular.module('admOrder').controller('admOrderExpressCtrl', function ($q, title, $scope, $http,  $state, $stateParams, $sessionStorage, $uibModalInstance,getDatas, Notify) {

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
        $http.post(ctx + "/admOrder/confirmSendOrder",{id:id, orderNo:orderNo, orderStatues:statues, expressNo:expressNo}).success(function (resp) {
            if(resp.successful){
                Notify.success("确认发货完成");
                $uibModalInstance.close(true);
                $state.go("app.admOrder", {}, {reload: true});
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
angular.module('admOrder').controller('admExpressMessageCtrl', function ($q, title, $scope, $http,  $state, $stateParams, $sessionStorage, $uibModalInstance,getDatas) {

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
angular.module('admOrder').filter("OrderStatuesFilter",function () {
    return function (input) {
        if(input=='1'){return '待支付'};
        if(input=='2'){return '待发货'};
        if(input=='3'){return '待收货'};
        if(input=='4'){return '订单完成'};
    }
});

angular.module('admOrder').filter("OrderCategoryFilter",function () {
    return function (input) {
        if(input=='1'){return '报单'};
        if(input=='2'){return '复投'};
        if(input=='3'){return '折扣单'};
    }
});

// angular.module('admOrder').filter("MemberLevelFilter",function () {
//     return function (input) {
//         if(input=='member_level1'){return '普卡'};
//         if(input=='member_level2'){return '铜卡'};
//         if(input=='member_level3'){return '银卡'};
//         if(input=='member_level4'){return '金卡'};
//         if(input=='member_level5'){return '白金卡'};
//         if(input=='member_level6'){return '黑金卡'};
//     }
// });
angular.module('admOrder').filter("bonusAccountTypeFilter",function () {
    return function (input) {
        if(input=='1'){return '种子币'};
        if(input=='2'){return '奖金币'};
    }
});