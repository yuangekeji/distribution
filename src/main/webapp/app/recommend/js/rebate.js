angular.module('recommend').controller('recommendRebateController',
    function (title, $scope, $http,$state, $stateParams, $sessionStorage) {
        title.setTitle('返费信息');
        $scope.evaluteId = $stateParams.evaluteId ? $stateParams.evaluteId : '';
        $scope.edit = $stateParams.edit ? $stateParams.edit : '';
        $scope.del = $stateParams.del ? $stateParams.del : '';
        $scope.pawnId = $stateParams.pawnId ? $stateParams.pawnId : '';
        $scope.goodsType = $stateParams.goodsType ? $stateParams.goodsType : 3;
        $scope.status = '';
        $scope.goodsList = [];

        $scope.onInit = function () {
            if ($scope.evaluteId) {
                $http.get(ctx + '/recommend/getGoods?evaluteId=' + $scope.evaluteId).success(function (resp) {
                    $scope.goodsList = resp.data;
                    $sessionStorage.goodsCache = $scope.goodsList;
                    $scope.goodsType = $scope.param.goodsType;
                });
            }
            if ($stateParams.edit) {
                if ($sessionStorage.goodsCache && $sessionStorage.goodsCache.length > 0)
                    $scope.goodsList = $sessionStorage.goodsCache;
            } else {
                $sessionStorage.goodsCache = [];
            }
            if ($stateParams.del == 1) {
                $sessionStorage.goodsCache = [];
            }
        }
        $scope.onInit();
        $scope.changeGoodsType = function (idx) {
            if ($scope.goodsList.length > 0)return;
            $scope.goodsType = idx
        }
        $scope.gotoAddPage2 = function () {
            if ($scope.goodsType == 1) {
                //1:房产
                $state.go('app.goods-house', {
                    goodsType: $scope.goodsType,
                    state: 'app.recommend-add',
                    pawnId: $scope.pawnId
                });
            } else if ($scope.goodsType == 2) {
                //2:汽车
                $state.go('app.goods-car', {
                    goodsType: $scope.goodsType,
                    state: 'app.recommend-add',
                    pawnId: $scope.pawnId
                });
            } else if ($scope.goodsType == 3) {
                //3:民品
                $state.go('app.goods-public', {
                    goodsType: $scope.goodsType,
                    state: 'app.recommend-add',
                    pawnId: $scope.pawnId
                });
            }
        }
        $scope.gotoListPage = function () {
            $state.go('app.recommend-list');
        }
        $scope.ok = function () {
            $scope.param = {
                goodsList: $scope.goodsList,
                goodsType: $scope.goodsType,
                pawnId: $scope.pawnId,
                pawnType: $scope.pawnType,
                evaluteId: $scope.evaluteId,
                edit: $scope.edit,
            };
            $http.post(ctx + '/recommend/goodsSubmit', $scope.param).success(function (resp) {
                if (resp.successful) {
                    $state.go('app.recommend-success', {
                        recommendId: resp.data.id,
                        rebateAmountMin: resp.data.rebateAmountMin,
                        rebateAmountMax: resp.data.rebateAmountMax,
                        goodsType: $scope.goodsType,
                        pawnId: resp.data.id,
                        pawnType: $scope.pawnType,
                        evaluteId: $scope.evaluteId,
                    });
                } else {
                    $.alert(resp.errorMessage);
                }
            });
        }
        $scope.removeGoods = function (index) {
            $.modal({
                title: '是否确定删除当前当物信息？',
                buttons: [
                    {
                        text: '确定',
                        onClick: function () {
                            $scope.goodsList.splice(index, 1);
                            $sessionStorage.goodsCache.splice(index, 1);
                            $scope.$apply();
                        }
                    },
                    {
                        text: '取消',
                        onClick: function () {
                        }
                    },
                ]
            });
        }
        $scope.editGoods = function (index) {
            if ($scope.goodsType == 1) {
                //1:房产
                $state.go('app.goods-house', {
                    pawnType: $scope.pawnType,
                    pawnId: $scope.pawnId,
                    goodsType: $scope.goodsType,
                    index: index,
                    state: 'app.recommend-add',
                });
            } else if ($scope.goodsType == 2) {
                //2:汽车
                $state.go('app.goods-car', {
                    pawnType: $scope.pawnType,
                    pawnId: $scope.pawnId,
                    goodsType: $scope.goodsType,
                    index: index,
                    state: 'app.recommend-add',
                });
            } else if ($scope.goodsType == 3) {
                //3:民品
                $state.go('app.goods-public', {
                    pawnType: $scope.pawnType,
                    pawnId: $scope.pawnId,
                    goodsType: $scope.goodsType,
                    index: index,
                    state: 'app.recommend-add',
                });
            }
        }
        $scope.gotoPawn = function () {
            $state.go('app.chooseShop', {goodsType: $scope.goodsType, pawnId: $scope.pawnId, state: 'recommend'});
        }
        $scope.cancel = function () {
            $.modal({
                title: '确定要取消预约吗?',
                buttons: [
                    {
                        text: '确定',
                        onClick: function () {
                            $http.get(ctx + '/recommend/cancel?pawnId=' + $scope.pawnId).success(function (resp) {
                                if (resp.successful) {
                                    $scope.gotoListPage();
                                } else {
                                    $.alert(resp.errorMessage);
                                }
                            });
                        }
                    },
                    {
                        text: '取消',
                        onClick: function () {
                        }
                    },
                ]
            });
        };






        /*************************************************/
        /**
         * 初始化返利页面
         * */
        $scope.rebateParam = {
            goodsType: 3,
            loanCount: '',
            loanTerm: '',
            interestRate: '',
            rebateMin:'',
            rebateMax:''
        };

        /**
         * 计算返利
         * （用户填写费率-系统维护费率）* 贷款金额。如果用户填写费率小于系统费率，给出提示。
         * */
        $scope.rebate = function () {
            if($scope.rebateParam.loanCount!=undefined && $scope.rebateParam.loanCount.toString()==""){$.alert("请输入贷款金额");return;}
            if($scope.rebateParam.loanCount==undefined || $scope.rebateParam.loanCount==0){$.alert("请输入正确的贷款金额");return;}

            if(!$scope.rebateParam.loanTerm){$.alert("请选择贷款期限");return;}

            if($scope.rebateParam.interestRate!=undefined && $scope.rebateParam.interestRate.toString()==""){$.alert("请输入承担利率");return;}
            if($scope.rebateParam.interestRate==undefined || $scope.rebateParam.interestRate==0){$.alert("请输入正确的承担利率");return;}

            $http.get(ctx + '/recommend/getSysRebate?type='+$scope.rebateParam.goodsType).success(function (resp) {
                console.log(resp);
                $scope.sysRebate = resp.data?resp.data:0;
                if($scope.rebateParam.interestRate + 0 <$scope.sysRebate.value1) {
                    $.alert("系统维护利率最小为"+$scope.sysRebate.value1+"%，承担利率不得小于系统维护利率最小值");
                    $scope.rebateParam.rebate="";
                    return;
                }else if($scope.rebateParam.interestRate>100) {
                    $.alert("承担利率不得大于100%");
                    $scope.rebateParam.rebate="";
                    return;
                }else{
                    if($scope.rebateParam.interestRate + 0 > $scope.sysRebate.value2 + 0){
                        $scope.rebateParam.rebateMin = (($scope.rebateParam.interestRate - $scope.sysRebate.value2)/100*$scope.rebateParam.loanCount).toFixed(2);
                        $scope.rebateParam.rebateMax = (($scope.rebateParam.interestRate - $scope.sysRebate.value1)/100*$scope.rebateParam.loanCount).toFixed(2);
                    }else{
                        $scope.rebateParam.rebateMin = 0;
                        $scope.rebateParam.rebateMax = (($scope.rebateParam.interestRate - $scope.sysRebate.value1)/100*$scope.rebateParam.loanCount).toFixed(2);
                    }
                }
            }).error(function (error) {
                console.log(error);
            });
        };

        $scope.$watch('rebateParam.loanCount', function (newValue, oldValue) {
            if (!newValue)return;
            var reg = /^(([1-9])([0-9]{0,8})((\.[0-9]{0,2})?))$/;
            var flag = true;
            if (newValue.toString().split('.')[1] && newValue.toString().split('.')[1].length > 2) {
                flag = false;
            }
            if (!flag || !reg.test(newValue)) {
                $scope.rebateParam.loanCount = oldValue;
            }
        });

        $scope.$watch('rebateParam.interestRate', function (newValue, oldValue) {
            if (!newValue)return;
            var reg = /^(([1-9])([0-9]{0,2})((\.[0-9]{0,2})?))$/;
            if (!reg.test(newValue)) {
                $scope.rebateParam.interestRate = oldValue;
            }
        });


        /**
         * 跳转添加页面
         * */
        $scope.gotoAddPage2 = function () {
            /*if(!$scope.rebateParam.rebate){
                $.alert("请进行返费评估");
                return false;
            }*/
            $sessionStorage.borrowAmount = $scope.rebateParam.loanCount;
            $sessionStorage.interestRate = $scope.rebateParam.interestRate;
            $sessionStorage.rebateMin = $scope.rebateParam.rebateMin;
            $sessionStorage.rebateMax = $scope.rebateParam.rebateMax;
            if ($scope.rebateParam.goodsType == 1) {
                //1:房产
                $state.go('app.goods-house', {
                    pawnType: '0',
                    pawnId: '',
                    goodsType: $scope.rebateParam.goodsType,
                    state: 'app.recommend-add'
                });
            } else if ($scope.rebateParam.goodsType == 2) {
                //2:汽车
                $state.go('app.goods-car', {
                    pawnType: '0',
                    pawnId: '',
                    goodsType: $scope.rebateParam.goodsType,
                    state: 'app.recommend-add'
                });
            } else if ($scope.rebateParam.goodsType == 3) {
                //3:民品
                $state.go('app.goods-public', {
                    pawnType: '0',
                    pawnId: '',
                    goodsType: $scope.rebateParam.goodsType,
                    state: 'app.recommend-add'
                });
            }
        }

        $scope.gotoListPage = function () {
            $.modal({
                title: '你确定要放弃当前编辑内容吗？',
                buttons: [
                    {
                        text: '确定',
                        onClick: function () {
                            $state.go('app.recommend-list');
                        }
                    },
                    {
                        text: '取消',
                        onClick: function () {
                        }
                    },
                ]
            });
        };

        /**切换物品*/
        $scope.changeInfo = function (type) {
            $scope.rebateParam.goodsType = type;
            $scope.rebateParam.rebate = '';
        }
    });