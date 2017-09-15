angular.module('admBasicSetting').controller('admBasicSettingCtrl', function ($q, title, $scope, $http,  $state, $stateParams, $sessionStorage, $log, ConfirmModal, Notify) {
    title.setTitle('基本配置');
    $scope.myPage = {
        pageNo: 1,
        pageSize: 10,
        totalCount: 0,
        result: [],
        parameterMap: {}
    };
    $scope.advance ={
        advanceMinAmt:0,
        advanceMaxPercent:0
    }

    $scope.advanceMinAmt = {
     typeCode: '',
     detailCode: '',
     minAmt: 0,
     maxAmt:0,
     maxPercent:0
     };

     $scope.dividendBasic = {
     typeCode: '',
     detailCode: '',
     minAmt: 0,
     maxAmt:0,
     maxPercent:0
     };

     $scope.jdBasic = {
     typeCode: '',
     detailCode: '',
     minAmt: 0,
     maxAmt:0,
     maxPercent:0
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

    $scope.search = function(){
        $http.post(ctx + '/admBasicSetting/list', $scope.myPage)
            .success(function (resp) {
                if (resp.successful) {
                    $scope.myPage = resp.data;
                    $scope.notData = false;
                    if (!$scope.myPage.result || $scope.myPage.result.length == 0) $scope.notData = true;

                    $scope.myPage.filter()

                } else {
                    console.log(resp.errors());
                }

            }).error(function (error) {
            console.error(error);
        });
    }

    $scope.currenttypeCode = '';
    $scope.currentdetailCode = '';
    $scope.filterCodeList = function(array){
        return array.typeCode == $scope.currenttypeCode && array.detailCode ==$scope.currentdetailCode;
    }

    //提现，分红包，见点奖设置修改
    $scope.saveBasic = function (typeCode, detailCode) {

         $scope.currenttypeCode = typeCode;
         $scope.currentdetailCode= detailCode;
         $scope.modifyResult = $scope.myPage.result.filter($scope.filterCodeList)[0];

        $scope.startLoading();
        $http.post(ctx + '/admBasicSetting/updateBasicSetting',
            {
                typeCode    : typeCode,
                detailCode  : detailCode,
                minAmt      : $scope.modifyResult.minAmt,
                maxAmt      : $scope.modifyResult.maxAmt,
                maxPercent  : $scope.modifyResult.maxPercent
            }).success(function (resp) {
            if(resp.successful) {
                //$scope.msg = "";
                if (resp.data.result == 'success') {
                    Notify.success("基本设置修改成功。");
                }else {
                    Notify.error("基本设置修改失败，请重新尝试");
                }
                $scope.stopLoading();
            }else{
                Notify.error("基本设置修改失败，请稍后再试");
                //失败后停止loading，刷新页面
                $scope.stopLoading();
                $window.location.reload();
            }
        });
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
        $scope.myPage.totalCount = 0;

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