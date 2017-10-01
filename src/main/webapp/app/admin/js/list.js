angular.module('admin').controller('adminCtrl',function ($q, title, $scope, $http,  $state, Notify, $stateParams, $sessionStorage, ConfirmModal) {
    title.setTitle('管理员列表');
    $scope.notData = false;
    $scope.currentUser = $sessionStorage.currentUser;

    $scope.myPage = {
        pageNo: 1,
        pageSize: 10,
        totalCount: 0,
        result: [],
        parameterMap: {
            name:'',
            roleId:''
        }
    };
    $scope.search = function(){

        $http.post(ctx + '/admin/list', $scope.myPage)
            .success(function (resp) {
                if (resp.successful) {
                    $scope.myPage = resp.data;
                    $scope.notData = false;
                    if (!$scope.myPage.result || $scope.myPage.result.length == 0) $scope.notData = true;

                } else {
                    console.log(resp.errorMessage);
                }

            }).error(function (error) {
            console.error(error);
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

    /**
     * 管理员明细查询
     * @param memberId
     * @param
     */
    $scope.searchAdminDetails = function (id) {
        $state.go('app.admin-detail', {id: id});
    }

    /**跳转到添加页面*/
    $scope.gotoAddPage = function () {
        $state.go("app.adminAdd");
    };

    /**管理员禁用/启用功能操作*/
    $scope.updateAdminDeleteFlag = function (id, name, rowName, deleteFlag) {
        $scope.deleteFlagName = "";
        if (deleteFlag == "Y") {
            $scope.deleteFlagName = "启用";
        }else {
            $scope.deleteFlagName = "禁用";
        }
        ConfirmModal.show({
            text: '确定要' + $scope.deleteFlagName + rowName + name +'吗？',
            isCancel:true //false alert ,true confirm
        }).then(function (sure) {
            if (!sure) {
                return;
            }
            $http.post(ctx + "/admin/updateAdminDeleteFlag",{id: id, deleteFlag: deleteFlag}).success(function (resp) {
                if(resp.successful){
                    Notify.success("操作成功。");
                    $scope.search();
                }else{
                    Notify.warning("操作失败。");
                }
            })
        });
    }
});

angular.module('admin').filter("RoleFilter",function () {
    return function (input) {
        if(input=='2'){return '系统管理员'};
        if(input=='3'){return '财务'};
    }
});

angular.module('admin').filter("DeleteFlagFilter",function () {
    return function (input) {
        if(input=='Y'){return '启用'};
        if(input=='N'){return '禁用'};
    }
});
