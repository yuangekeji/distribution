angular.module('admOrder').controller('admOrderCtrl',function ($q, title, $scope, $http, $state, $stateParams, $sessionStorage, Notify, $uibModal) {
    title.setTitle('订单管理');
    $scope.myPage = {
        pageNo: 1,
        pageSize: 10,
        totalCount: 0,
        result: [],
        parameterMap: {}
    };

    $scope.orderExpress = {
        id: '',
        statues: '',
        expressAddress: ''
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
    $scope.confirmOrder = function (id, statues, expressAddress) {
        $scope.orderExpress.id = id;
        $scope.orderExpress.statues = statues;
        $scope.orderExpress.expressAddress = expressAddress;
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
            console.info('确认');
        }, function()
        {
            console.info('取消');
        });
    };

    /**
     * excel download
     */
    $scope.excelDownload = function() {
       // window.open(ctx + "/admOrder/excelDownload" );
       //  window.location.href=ctx + "/admOrder/excelDownload?name="+this.page.parameterMap.name+"&phone="+this.page.parameterMap.phone+
       //                                          "&goodsType="+this.page.parameterMap.goodsType+"&status="+this.page.parameterMap.status;
        window.location.href=ctx + "/admOrder/excelDownload";
      /*  $scope.startLoading();
        $http.post(ctx + "/admOrder/excelDownload", $scope.myPage).success(function (resp) {
            if(resp.successful){
                Notify.success("excel下载完成");
               // $state.go("app.admOrder", {}, {reload: true});
            }else{
                console.log(resp.error);
                Notify.error(resp);
            }
            $scope.stopLoading();
        }).error(function (error) {
            console.log(error);
            //Notify.error(error);
            $scope.stopLoading();
        })*/

        /*var blob = new Blob([document.getElementById('orderList').innerHTML], {
            type: "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet;charset=utf-8"
        });
        saveAs(blob, "订单列表.xls");*/

        /*$scope.datetime.startTime=$scope.year+"-"+ $scope.lastmounth+"-"+$scope.lastday;
        $scope.datetime.endTime=$scope.yearD+"-"+ $scope.mounthD+"-"+"25";
        $scope.datetime.type= $scope.selectway;*/
  /*      $http({
            url: ctx + '/admOrder/excelDownload',
            method: "POST",
            headers: {
                'Content-type': 'application/json'
            },
            params: $scope.myPage.parameterMap,
            responseType: 'arraybuffer'
        }).success(function (data) {
            var blob = new Blob([data], {type: "application/vnd.ms-excel"});
            var objectUrl = URL.createObjectURL(blob);
            var filename="报表.xls";
            if (window.navigator.msSaveOrOpenBlob) {// For IE:
                navigator.msSaveBlob(blob, filename);
            }else{ // For other browsers:
                URL.revokeObjectURL(objectUrl);
            }
        }).error(function(data){
            alert(data.message);
        });*/
    }

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
    $scope.submit = function (id, statues, expressAddress) {
        $scope.startLoading();
        $http.post(ctx + "/admOrder/confirmSendOrder",{id:id, orderStatues:statues, expressAddress:expressAddress}).success(function (resp) {
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

angular.module('admOrder').filter("MemberLevelFilter",function () {
    return function (input) {
        if(input=='member_level1'){return '普卡'};
        if(input=='member_level2'){return '铜卡'};
        if(input=='member_level3'){return '银卡'};
        if(input=='member_level4'){return '金卡'};
        if(input=='member_level5'){return '白金卡'};
        if(input=='member_level6'){return '黑金卡'};
    }
});